package com.algaworks.algatransit.application.controller;

import com.algaworks.algatransit.application.mapper.SeizureMapper;
import com.algaworks.algatransit.domain.model.dto.SeizureRequestDTO;
import com.algaworks.algatransit.domain.model.dto.SeizureResponseDTO;
import com.algaworks.algatransit.domain.model.entity.Seizure;
import com.algaworks.algatransit.domain.model.entity.Vehicle;
import com.algaworks.algatransit.domain.service.SeizureService;
import com.algaworks.algatransit.domain.service.VehicleQueriesService;
import jakarta.validation.Valid;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/vehicles/{vehicleId}/seizures")
@RequiredArgsConstructor
public class SeizureController {
    private final VehicleQueriesService vehicleQueriesService;
    private final SeizureService seizureService;
    private final SeizureMapper mapper;

    @PostMapping
    public ResponseEntity<SeizureResponseDTO> save(@PathVariable Long vehicleId, @RequestBody @Valid SeizureRequestDTO requestDTO){
        Seizure seizure = mapper.toEntity(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(seizureService.save(vehicleId, seizure)));
    }

    @GetMapping
    public ResponseEntity<Set<SeizureResponseDTO>> findByVehicle(@PathVariable Long vehicleId){
        Vehicle vehicle = vehicleQueriesService.findById(vehicleId);
        return ResponseEntity.ok(mapper.toListDTO(vehicle.getSeizures()));
    }
}