package com.algaworks.algatransit.domain.repository;

import com.algaworks.algatransit.domain.model.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> { }