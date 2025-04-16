package com.algaworks.algatransit.application.controller;

import com.algaworks.algatransit.domain.model.entity.Owner;
import com.algaworks.algatransit.domain.repository.OwnerRepository;
import java.util.List;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.ResponseStatus;
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
    public ResponseEntity<List<Owner>> findByName(@PathVariable String name){
        return ResponseEntity.ok(repository.findByNameContaining(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> findById(@PathVariable Long id){
        Optional<Owner> opOwner = repository.findById(id);
        return opOwner.map(ResponseEntity::ok)
            .orElseGet(ResponseEntity.notFound()::build);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Owner save(@RequestBody Owner owner){
        return repository.save(owner);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Owner> update(@PathVariable Long id, @RequestBody Owner owner){
        if(repository.existsById(id)){
            owner.setId(id);
            return ResponseEntity.ok(repository.save(owner));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}