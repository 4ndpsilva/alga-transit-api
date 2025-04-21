package com.algaworks.algatransit.domain.repository;

import com.algaworks.algatransit.domain.model.entity.Vehicle;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findByPlate(String plate);

    @Query("SELECT v FROM Vehicle v WHERE v.owner.id = :ownerId")
    List<Vehicle> findByOwner(Long ownerId);
}