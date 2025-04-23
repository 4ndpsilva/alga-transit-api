package com.algaworks.algatransit.domain.model.dto;

import static com.algaworks.algatransit.infrastructure.util.Constant.REGEX_PLATE;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRequestDTO {

    @NotNull
    private Long ownerId;

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @NotBlank
    @Pattern(regexp = REGEX_PLATE)
    private String plate;
}