package com.algaworks.algatransit.application.mapper;

import com.algaworks.algatransit.domain.model.dto.SeizureRequestDTO;
import com.algaworks.algatransit.domain.model.dto.SeizureResponseDTO;
import com.algaworks.algatransit.domain.model.entity.Seizure;
import java.util.Set;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SeizureMapper {
    Seizure toEntity(SeizureRequestDTO dto);

    SeizureResponseDTO toDTO(Seizure entity);

    Set<SeizureResponseDTO> toListDTO(Set<Seizure> entities);
}