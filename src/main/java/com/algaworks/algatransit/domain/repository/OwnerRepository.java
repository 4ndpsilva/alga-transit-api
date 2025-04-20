package com.algaworks.algatransit.domain.repository;

import com.algaworks.algatransit.domain.model.entity.Owner;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    List<Owner> findByNameContaining(String name);

    Optional<Owner> findByEmail(String email);
}