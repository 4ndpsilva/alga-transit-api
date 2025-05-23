package com.algaworks.algatransit.domain.service;

import com.algaworks.algatransit.domain.model.entity.Seizure;
import com.algaworks.algatransit.domain.model.entity.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SeizureService {
    private final VehicleQueriesService vehicleQueriesService;

    @Transactional
    public Seizure save(Long vehicleId, Seizure seizure){
        Vehicle vehicle = getVehicle(vehicleId);
        return vehicle.addSeizure(seizure);
    }

    private Vehicle getVehicle(Long vehicleId){
        return vehicleQueriesService.findById(vehicleId);
    }
}