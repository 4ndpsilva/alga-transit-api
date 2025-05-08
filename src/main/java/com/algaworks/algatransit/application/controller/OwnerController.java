package com.algaworks.algatransit.application.controller;

import com.algaworks.algatransit.application.mapper.OwnerMapper;
import com.algaworks.algatransit.domain.model.dto.OwnerDTO;
import com.algaworks.algatransit.domain.model.entity.Owner;
import com.algaworks.algatransit.domain.service.OwnerService;
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
@RequestMapping("/api/v1/owners")
@RequiredArgsConstructor
public class OwnerController {
    private final OwnerService service;
    private final OwnerMapper mapper;

    @PostMapping
    public ResponseEntity<OwnerDTO> save(@RequestBody @Valid OwnerDTO requestDTO){
        Owner owner = mapper.toEntity(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(service.save(owner)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerDTO> update(@PathVariable Long id, @RequestBody @Valid OwnerDTO requestDTO){
        Owner owner = mapper.toEntity(requestDTO);
        return ResponseEntity.ok(mapper.toDTO(service.update(id, owner)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(mapper.toDTO(service.findById(id)));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<OwnerDTO>> findByName(@PathVariable String name){
        return ResponseEntity.ok(mapper.toListDTO(service.findByName(name)));
    }

    @GetMapping
    public ResponseEntity<List<OwnerDTO>> findAll(){
        return ResponseEntity.ok(mapper.toListDTO(service.findAll()));
    }
}