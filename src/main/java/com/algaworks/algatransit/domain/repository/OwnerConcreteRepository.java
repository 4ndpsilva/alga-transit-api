package com.algaworks.algatransit.domain.repository;

import com.algaworks.algatransit.domain.model.entity.Owner;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class OwnerConcreteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Owner> findAll(){
        TypedQuery<Owner> query = entityManager.createQuery("from Owner", Owner.class);
        return query.getResultList();
    }
}