package com.algaworks.algatransit.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
}