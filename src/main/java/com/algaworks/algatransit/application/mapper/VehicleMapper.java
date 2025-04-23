package com.algaworks.algatransit.application.mapper;

import com.algaworks.algatransit.domain.model.dto.VehicleRequestDTO;
import com.algaworks.algatransit.domain.model.dto.VehicleResponseDTO;
import com.algaworks.algatransit.domain.model.entity.Vehicle;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

    @Mapping(source = "ownerId", target = "owner.id")
    Vehicle toEntity(VehicleRequestDTO dto);

    VehicleResponseDTO toDTO(Vehicle entity);

    List<VehicleResponseDTO> toListDTO(List<Vehicle> entities);
}