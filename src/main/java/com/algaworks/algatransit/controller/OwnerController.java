package com.algaworks.algatransit.controller;

import com.algaworks.algatransit.domain.model.entity.Owner;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/owners")
public class OwnerController {

    @GetMapping
    public ResponseEntity<List<Owner>> find(){
        List<Owner> owners = List.of(
          Owner.builder()
              .id(1L)
              .name("João")
              .phone("123456789")
              .email("email@provider.com")
              .build(),
            Owner.builder()
                .id(2L)
                .name("Helena")
                .phone("987654321")
                .email("email@dominio.com")
                .build()
        );

        return ResponseEntity.ok(owners);
    }
}