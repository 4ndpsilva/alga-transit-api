package com.algaworks.algatransit.application.controller;

import com.algaworks.algatransit.domain.model.entity.Owner;
import com.algaworks.algatransit.domain.repository.OwnerRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerRepository repository;

    @GetMapping
    public ResponseEntity<List<Owner>> find(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Owner>> findById(@PathVariable String name){
        return ResponseEntity.ok(repository.findByNameContaining(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> findById(@PathVariable Long id){
        Optional<Owner> opOwner = repository.findById(id);
        return opOwner.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Owner> save(@RequestBody Owner owner){
        return ResponseEntity.ok(repository.save(owner));
    }
}