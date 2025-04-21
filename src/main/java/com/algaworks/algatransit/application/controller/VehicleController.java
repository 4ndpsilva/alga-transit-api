package com.algaworks.algatransit.application.controller;

import com.algaworks.algatransit.domain.model.dto.VehicleDTO;
import com.algaworks.algatransit.domain.model.dto.VehicleResponseDTO;
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

    @PostMapping
    public ResponseEntity<VehicleResponseDTO> save(@RequestBody @Valid VehicleDTO requestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleResponseDTO> update(@PathVariable Long id, @RequestBody @Valid VehicleDTO requestDTO){
        return ResponseEntity.ok(service.update(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try{
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponseDTO> findById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(queriesService.findById(id));
        }
        catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/plate/{plate}")
    public ResponseEntity<VehicleResponseDTO> findByPlate(@PathVariable String plate){
        return ResponseEntity.ok(queriesService.findByPlate(plate));
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<VehicleResponseDTO>> findByPlate(@PathVariable Long ownerId){
        return ResponseEntity.ok(queriesService.findByOwner(ownerId));
    }

    @GetMapping
    public ResponseEntity<List<VehicleResponseDTO>> findAll(){
        return ResponseEntity.ok(queriesService.findAll());
    }
}