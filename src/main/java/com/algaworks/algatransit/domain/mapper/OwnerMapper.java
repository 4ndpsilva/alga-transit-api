package com.algaworks.algatransit.domain.mapper;

import com.algaworks.algatransit.domain.model.dto.OwnerDTO;
import com.algaworks.algatransit.domain.model.entity.Owner;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OwnerMapper {
    Owner toEntity(OwnerDTO dto);

    OwnerDTO toDTO(Owner entity);

    List<OwnerDTO> toListDTO(List<Owner> entities);
}