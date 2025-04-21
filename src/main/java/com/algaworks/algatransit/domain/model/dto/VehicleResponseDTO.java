package com.algaworks.algatransit.domain.model.dto;

import com.algaworks.algatransit.domain.model.entity.StatusVehicle;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponseDTO {
    @JsonProperty(access = Access.READ_ONLY)
    private Long id;

    private OwnerDTO owner;

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @NotBlank
    private String plate;

    @NotBlank
    private StatusVehicle status;

    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd:HH:mm:ss")
    private LocalDateTime registrationDate;

    @JsonFormat(pattern = "yyyy-MM-dd:HH:mm:ss")
    private LocalDateTime dateOfSeizure;
}