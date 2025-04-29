package com.algaworks.algatransit.domain.service;

import com.algaworks.algatransit.domain.model.entity.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ImpoundService {
    private final VehicleQueriesService vehicleQueriesService;

    @Transactional
    public void impound(Long vehicleId){
        Vehicle vehicle = getVehicle(vehicleId);
        vehicle.impound();
    }

    @Transactional
    public void removeImpound(Long vehicleId){
        Vehicle vehicle = getVehicle(vehicleId);
        vehicle.removeImpound();
    }

    private Vehicle getVehicle(Long vehicleId){
        return vehicleQueriesService.findById(vehicleId);
    }
}