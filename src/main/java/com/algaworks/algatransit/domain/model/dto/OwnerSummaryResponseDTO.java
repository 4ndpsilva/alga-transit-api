package com.algaworks.algatransit.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OwnerSummaryResponseDTO {
    private Long id;
    private String name;
}