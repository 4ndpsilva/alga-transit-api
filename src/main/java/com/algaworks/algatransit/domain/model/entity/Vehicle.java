package com.algaworks.algatransit.domain.model.entity;

import com.algaworks.algatransit.domain.exception.AlreadyExistsException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
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
    @EqualsAndHashCode.Include
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
    private OffsetDateTime registrationDate;

    @Column(name = "DATE_OF_SEIZURE")
    private OffsetDateTime dateOfSeizure;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private Set<Seizure> seizures = new LinkedHashSet<>();

    public void validateExistingPLate(Vehicle vehicle){
        if(existingPlate(vehicle)){
            throw new AlreadyExistsException(VehicleMsg.VEHICLE_002, vehicle.getPlate());
        }
    }

    private boolean existingPlate(Vehicle vehicle){
        return this.plate.equals(vehicle.getPlate()) && !this.equals(vehicle);
    }

    public interface VehicleMsg{
        String VEHICLE_001 = "VEHICLE-001";
        String VEHICLE_002 = "VEHICLE-002";
        String VEHICLE_003 = "VEHICLE-003";
    }

    public Seizure addSeizure(Seizure seizure){
        status = StatusVehicle.SEIZED;
        dateOfSeizure = OffsetDateTime.now();
        seizure.setOccurrenceDate(OffsetDateTime.now());
        seizure.setVehicle(this);
        seizures.add(seizure);
        return seizure;
    }
}