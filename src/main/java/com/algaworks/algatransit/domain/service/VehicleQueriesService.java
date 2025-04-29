package com.algaworks.algatransit.domain.service;

import com.algaworks.algatransit.domain.model.entity.Owner;
import com.algaworks.algatransit.domain.model.entity.Vehicle;
import com.algaworks.algatransit.domain.repository.VehicleRepository;
import com.algaworks.algatransit.infrastructure.exception.ErrorCode;
import com.algaworks.algatransit.infrastructure.exception.ResourceNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleQueriesService {
    private final OwnerService ownerService;
    private final VehicleRepository repository;

    public List<Vehicle> findByOwner(Long ownerId){
        Owner owner = ownerService.findById(ownerId);
        return repository.findByOwner(owner.getId());
    }

    public Vehicle findByPlate(String plate){
        Optional<Vehicle> opVehicle = repository.findByPlate(plate);

        if(opVehicle.isPresent()){
            return opVehicle.get();
        }

        throw new ResourceNotFoundException(ErrorCode.VEHICLE_003.getCode(), plate);
    }

    public Vehicle findById(Long id){
        Optional<Vehicle> opVehicle = repository.findById(id);

        if(opVehicle.isPresent()){
            return opVehicle.get();
        }

        throw new ResourceNotFoundException(ErrorCode.VEHICLE_001.getCode());
    }

    public List<Vehicle> findAll(){
        return repository.findAll();
    }
}