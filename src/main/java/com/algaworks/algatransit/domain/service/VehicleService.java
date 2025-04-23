package com.algaworks.algatransit.domain.service;

import static com.algaworks.algatransit.domain.model.entity.Vehicle.VehicleMsg.VEHICLE_002;

import com.algaworks.algatransit.domain.exception.AlreadyExistsException;
import com.algaworks.algatransit.domain.exception.ResourceNotFoundException;
import com.algaworks.algatransit.domain.model.entity.Owner;
import com.algaworks.algatransit.domain.model.entity.StatusVehicle;
import com.algaworks.algatransit.domain.model.entity.Vehicle;
import com.algaworks.algatransit.domain.model.entity.Vehicle.VehicleMsg;
import com.algaworks.algatransit.domain.repository.VehicleRepository;
import java.time.OffsetDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepository repository;
    private final VehicleQueriesService queriesService;
    private final OwnerService ownerService;

    @Transactional
    public Vehicle save(Vehicle entity){
        boolean existingPlate = repository.findByPlate(entity.getPlate()).isPresent();

        if(existingPlate){
            throw new AlreadyExistsException(VEHICLE_002, entity.getPlate());
        }

        entity.setOwner(getOwner(entity.getOwner().getId()));
        entity.setStatus(StatusVehicle.REGULAR);
        entity.setRegistrationDate(OffsetDateTime.now());

        return repository.save(entity);
    }

    @Transactional
    public Vehicle update(Long id, Vehicle entity){
        Vehicle oldVehicle = queriesService.findById(id);

        Vehicle vehicleByPlate = repository.findByPlate(entity.getPlate()).orElse(Vehicle.builder().build());
        entity.setId(id);
        entity.setOwner(getOwner(entity.getOwner().getId()));
        entity.setStatus(oldVehicle.getStatus());
        entity.setRegistrationDate(oldVehicle.getRegistrationDate());
        entity.validateExistingPLate(vehicleByPlate);

        return repository.save(entity);
    }

    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException(VehicleMsg.VEHICLE_001);
        }

        repository.deleteById(id);
    }

    private Owner getOwner(Long ownerId){
        return ownerService.findById(ownerId);
    }
}