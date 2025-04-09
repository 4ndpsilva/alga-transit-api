package com.algaworks.algatransit.application.controller;

import com.algaworks.algatransit.domain.model.entity.Owner;
import com.algaworks.algatransit.domain.repository.OwnerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/owners")
public class OwnerController {

    @PersistenceContext
    private EntityManager entityManager;

    private final OwnerRepository repository;

    @GetMapping
    public ResponseEntity<List<Owner>> find(){
        /*String jpql = "from Owner";
        TypedQuery<Owner> q = entityManager.createQuery(jpql, Owner.class);
        return ResponseEntity.ok(q.getResultList());*/

        return ResponseEntity.ok(repository.findAll());
    }

    public ResponseEntity<Owner> save(@RequestBody Owner owner){
        return ResponseEntity.ok().build();
    }
}