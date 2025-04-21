package com.algaworks.algatransit.domain.model.entity;

import com.algaworks.algatransit.domain.exception.AlreadyExistsException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_VEHICLE")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "OWNER_ID")
    private Owner owner;

    @Column(name = "BRAND")
    private String brand;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "PLATE")
    private String plate;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private StatusVehicle status;

    @Column(name = "REGISTRATION_DATE")
    private LocalDateTime registrationDate;

    @Column(name = "DATE_OF_SEIZURE")
    private LocalDateTime dateOfSeizure;

    public void validateExistingPLate(Vehicle vehicle){
        if(existingPlate(vehicle)){
            throw new AlreadyExistsException(VehicleMsg.VEHICLE_002, vehicle.getPlate());
        }
    }

    private boolean existingPlate(Vehicle vehicle){
        return this.plate.equals(vehicle.getPlate()) && !this.equals(vehicle);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Vehicle vehicle) && this.id.equals(vehicle.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public interface VehicleMsg{
        String VEHICLE_001 = "VEHICLE-001";
        String VEHICLE_002 = "VEHICLE-002";
        String VEHICLE_003 = "VEHICLE-003";
    }
}