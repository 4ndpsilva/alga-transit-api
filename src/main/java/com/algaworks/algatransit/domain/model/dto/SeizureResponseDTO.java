package com.algaworks.algatransit.domain.model.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeizureResponseDTO {
    private Long id;
    private String description;
    private BigDecimal fineAmount;
    private OffsetDateTime occurrenceDate;
}