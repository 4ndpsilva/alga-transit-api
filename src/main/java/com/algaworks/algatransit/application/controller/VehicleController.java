package com.algaworks.algatransit.application.controller;

import com.algaworks.algatransit.application.mapper.VehicleMapper;
import com.algaworks.algatransit.domain.model.dto.VehicleRequestDTO;
import com.algaworks.algatransit.domain.model.dto.VehicleResponseDTO;
import com.algaworks.algatransit.domain.model.entity.Vehicle;
import com.algaworks.algatransit.domain.service.VehicleQueriesService;
import com.algaworks.algatransit.domain.service.VehicleService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/vehicles")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService service;
    private final VehicleQueriesService queriesService;
    private final VehicleMapper mapper;

    @PostMapping
    public ResponseEntity<VehicleResponseDTO> save(@RequestBody @Valid VehicleRequestDTO requestDTO){
        Vehicle vehicle = mapper.toEntity(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(service.save(vehicle)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleResponseDTO> update(@PathVariable Long id, @RequestBody @Valid VehicleRequestDTO requestDTO){
        Vehicle vehicle = mapper.toEntity(requestDTO);
        return ResponseEntity.ok(mapper.toDTO(service.update(id, vehicle)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponseDTO> findById(@PathVariable Long id){
        VehicleResponseDTO responseDTO = mapper.toDTO(queriesService.findById(id));
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/plate/{plate}")
    public ResponseEntity<VehicleResponseDTO> findByPlate(@PathVariable String plate){
        return ResponseEntity.ok(mapper.toDTO(queriesService.findByPlate(plate)));
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<VehicleResponseDTO>> findByOwner(@PathVariable Long ownerId){
        return ResponseEntity.ok(mapper.toListDTO(queriesService.findByOwner(ownerId)));
    }

    @GetMapping
    public ResponseEntity<List<VehicleResponseDTO>> findAll(){
        return ResponseEntity.ok(mapper.toListDTO(queriesService.findAll()));
    }
}