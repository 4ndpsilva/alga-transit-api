package com.algaworks.algatransit.infrastructure.exception;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    OWNER_001("OWNER-001"),
    OWNER_002("OWNER-002"),

    VEHICLE_001("VEHICLE-001"),
    VEHICLE_002("VEHICLE-002"),
    VEHICLE_003("VEHICLE-003");

    private final String code;

    public static ErrorCode fromValue(String value) {
        return Arrays.stream(ErrorCode.values())
            .filter(c -> c.code.equals(value))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Unexpected value '" + value + "'"));
    }
}