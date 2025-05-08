package com.algaworks.algatransit.application.controller;

import com.algaworks.algatransit.domain.service.ImpoundService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/vehicles/{vehicleId}/impound")
@RequiredArgsConstructor
public class ImpoundController {
    private final ImpoundService impoundService;

    @PutMapping
    public ResponseEntity<Void> impound(@PathVariable Long vehicleId){
        impoundService.impound(vehicleId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> removeImpound(@PathVariable Long vehicleId){
        impoundService.removeImpound(vehicleId);
        return ResponseEntity.noContent().build();
    }
}