package com.algaworks.algatransit.domain.service;

import com.algaworks.algatransit.domain.exception.BusinessException;
import com.algaworks.algatransit.domain.mapper.VehicleMapper;
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
    private final VehicleRepository repository;
    private final VehicleMapper mapper;

    public List<VehicleResponseDTO> findByOwner(Long ownerId){
        return mapper.toListDTO(repository.findByOwner(ownerId));
    }

    public VehicleResponseDTO findByPlate(String plate){
        Optional<Vehicle> opVehicle = repository.findByPlate(plate);

        return opVehicle.map(mapper::toDTO)
            .orElseThrow(() -> new BusinessException(String.format("Veículo com a placa %s não encontrado", plate)));
    }

    public VehicleResponseDTO findById(Long id){
        Optional<Vehicle> opVehicle = repository.findById(id);

        return opVehicle.map(mapper::toDTO)
            .orElseThrow(() -> new BusinessException("Veículo não encontrado"));
    }

    public List<VehicleResponseDTO> findAll(){
        return mapper.toListDTO(repository.findAll());
    }
}