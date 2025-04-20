package com.algaworks.algatransit.application.controller;

import com.algaworks.algatransit.domain.exception.BusinessException;
import com.algaworks.algatransit.domain.model.dto.OwnerDTO;
import com.algaworks.algatransit.domain.service.OwnerService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

    @PostMapping
    public ResponseEntity<OwnerDTO> save(@RequestBody @Valid OwnerDTO requestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerDTO> update(@PathVariable Long id, @RequestBody @Valid OwnerDTO requestDTO){
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
    public ResponseEntity<OwnerDTO> findById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(service.findById(id));
        }
        catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<OwnerDTO>> findByName(@PathVariable String name){
        return ResponseEntity.ok(service.findByName(name));
    }

    @GetMapping
    public ResponseEntity<List<OwnerDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> catchError(BusinessException ex){
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(ex.getMessage());
    }
}