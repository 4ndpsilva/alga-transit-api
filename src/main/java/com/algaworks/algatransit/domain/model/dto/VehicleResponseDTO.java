package com.algaworks.algatransit.domain.model.dto;

import com.algaworks.algatransit.domain.model.entity.StatusVehicle;
import com.algaworks.algatransit.infrastructure.util.Constant;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponseDTO {
    private Long id;
    private OwnerSummaryResponseDTO owner;
    private String brand;
    private String model;
    private String plate;
    private StatusVehicle status;

    @JsonFormat(pattern = Constant.DATE_TIME_PATTERN)
    private OffsetDateTime registrationDate;

    @JsonFormat(pattern = Constant.DATE_TIME_PATTERN)
    private OffsetDateTime dateOfSeizure;
}