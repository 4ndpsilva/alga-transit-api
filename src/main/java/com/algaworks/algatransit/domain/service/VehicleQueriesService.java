package com.algaworks.algatransit.domain.service;

import static com.algaworks.algatransit.domain.model.entity.Vehicle.VehicleMsg.VEHICLE_001;
import static com.algaworks.algatransit.domain.model.entity.Vehicle.VehicleMsg.VEHICLE_003;

import com.algaworks.algatransit.domain.exception.ResourceNotFoundException;
import com.algaworks.algatransit.domain.mapper.VehicleMapper;
import com.algaworks.algatransit.domain.model.dto.OwnerDTO;
import com.algaworks.algatransit.domain.model.dto.VehicleResponseDTO;
import com.algaworks.algatransit.domain.model.entity.Vehicle;
import com.algaworks.algatransit.domain.repository.VehicleRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleQueriesService {
    private final OwnerService ownerService;
    private final VehicleRepository repository;
    private final VehicleMapper mapper;

    public List<VehicleResponseDTO> findByOwner(Long ownerId){
        OwnerDTO ownerDTO = ownerService.findById(ownerId);
        return mapper.toListDTO(repository.findByOwner(ownerDTO.getId()));
    }

    public VehicleResponseDTO findByPlate(String plate){
        Optional<Vehicle> opVehicle = repository.findByPlate(plate);

        return opVehicle.map(mapper::toDTO)
            .orElseThrow(() -> new ResourceNotFoundException(VEHICLE_003, plate));
    }

    public VehicleResponseDTO findById(Long id){
        Optional<Vehicle> opVehicle = repository.findById(id);

        return opVehicle.map(mapper::toDTO)
            .orElseThrow(() -> new ResourceNotFoundException(VEHICLE_001));
    }

    public List<VehicleResponseDTO> findAll(){
        return mapper.toListDTO(repository.findAll());
    }
}