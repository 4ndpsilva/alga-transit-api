package com.algaworks.algatransit.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDTO {
    @JsonProperty(access = Access.READ_ONLY)
    private Long id;

    @Size(min = 3, max = 60)
    @NotBlank
    private String name;

    @Size(min = 10, max = 255)
    @Email
    @NotBlank
    private String email;

    @Size(max = 20)
    @NotBlank
    private String phone;
}