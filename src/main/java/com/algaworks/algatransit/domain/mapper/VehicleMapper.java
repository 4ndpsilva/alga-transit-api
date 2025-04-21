package com.algaworks.algatransit.domain.mapper;

import com.algaworks.algatransit.domain.model.dto.VehicleDTO;
import com.algaworks.algatransit.domain.model.dto.VehicleResponseDTO;
import com.algaworks.algatransit.domain.model.entity.Vehicle;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
    Vehicle toEntity(VehicleDTO dto);

    VehicleResponseDTO toDTO(Vehicle entity);

    List<VehicleResponseDTO> toListDTO(List<Vehicle> entities);
}