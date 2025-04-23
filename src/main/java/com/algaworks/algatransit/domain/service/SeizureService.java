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
        Vehicle vehicle = vehicleQueriesService.findById(vehicleId);
        return vehicle.addSeizure(seizure);
    }
}