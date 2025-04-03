package com.algaworks.algatransit.domain.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Owner {
    private Long id;
    private String name;
    private String email;
    private String phone;
}