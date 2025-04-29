package com.algaworks.algatransit.domain.service;

import com.algaworks.algatransit.domain.exception.AlreadyExistsException;
import com.algaworks.algatransit.domain.model.entity.Owner;
import com.algaworks.algatransit.domain.model.entity.StatusVehicle;
import com.algaworks.algatransit.domain.model.entity.Vehicle;
import com.algaworks.algatransit.domain.repository.VehicleRepository;
import com.algaworks.algatransit.infrastructure.exception.ErrorCode;
import com.algaworks.algatransit.infrastructure.exception.ResourceNotFoundException;
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
            throw new AlreadyExistsException(ErrorCode.VEHICLE_002.getCode(), entity.getPlate());
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
            throw new ResourceNotFoundException(ErrorCode.VEHICLE_001.getCode());
        }

        repository.deleteById(id);
    }

    private Owner getOwner(Long ownerId){
        return ownerService.findById(ownerId);
    }
}