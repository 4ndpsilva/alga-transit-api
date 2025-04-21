package com.algaworks.algatransit.domain.service;

import static com.algaworks.algatransit.domain.model.entity.Vehicle.VehicleMsg.VEHICLE_002;

import com.algaworks.algatransit.domain.exception.AlreadyExistsException;
import com.algaworks.algatransit.domain.exception.ResourceNotFoundException;
import com.algaworks.algatransit.domain.mapper.OwnerMapper;
import com.algaworks.algatransit.domain.mapper.VehicleMapper;
import com.algaworks.algatransit.domain.model.dto.OwnerDTO;
import com.algaworks.algatransit.domain.model.dto.VehicleDTO;
import com.algaworks.algatransit.domain.model.dto.VehicleResponseDTO;
import com.algaworks.algatransit.domain.model.entity.Owner;
import com.algaworks.algatransit.domain.model.entity.StatusVehicle;
import com.algaworks.algatransit.domain.model.entity.Vehicle;
import com.algaworks.algatransit.domain.model.entity.Vehicle.VehicleMsg;
import com.algaworks.algatransit.domain.repository.VehicleRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepository repository;
    private final VehicleQueriesService queriesService;
    private final VehicleMapper mapper;
    private final OwnerService ownerService;
    private final OwnerMapper ownerMapper;

    @Transactional
    public VehicleResponseDTO save(VehicleDTO dto){
        boolean existingPlate = repository.findByPlate(dto.getPlate()).isPresent();

        if(existingPlate){
            throw new AlreadyExistsException(VEHICLE_002, dto.getPlate());
        }

        Vehicle newVehicle = mapper.toEntity(dto);
        newVehicle.setOwner(getOwner(dto.getOwnerId()));
        newVehicle.setStatus(StatusVehicle.REGULAR);
        newVehicle.setRegistrationDate(LocalDateTime.now());

        return mapper.toDTO(repository.save(newVehicle));
    }

    @Transactional
    public VehicleResponseDTO update(Long id, VehicleDTO dto){
        VehicleResponseDTO vehicleResponseDTO = queriesService.findById(id);
        Vehicle vehicle = mapper.toEntity(dto);

        Vehicle vehicleByPlate = repository.findByPlate(dto.getPlate()).orElse(Vehicle.builder().build());
        vehicle.setId(id);
        vehicle.setOwner(getOwner(dto.getOwnerId()));
        vehicle.setStatus(vehicleResponseDTO.getStatus());
        vehicle.setRegistrationDate(vehicleResponseDTO.getRegistrationDate());
        vehicle.validateExistingPLate(vehicleByPlate);

        return mapper.toDTO(repository.save(vehicle));
    }

    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException(VehicleMsg.VEHICLE_001);
        }

        repository.deleteById(id);
    }

    private Owner getOwner(Long ownerId){
        OwnerDTO ownerDTO = ownerService.findById(ownerId);
        return ownerMapper.toEntity(ownerDTO);
    }
}