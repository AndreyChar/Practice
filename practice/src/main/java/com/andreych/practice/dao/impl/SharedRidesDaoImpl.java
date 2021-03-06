package com.andreych.practice.dao.impl;

import com.andreych.practice.dao.SharedRidesDao;
import com.andreych.practice.domain.Ride;

import java.util.HashSet;
import java.util.Set;

public class SharedRidesDaoImpl implements SharedRidesDao {
    Set<Ride> rides = new HashSet<>();

    @Override
    public void saveRide(Ride ride) {
        rides.add(ride);
    }

    @Override
    public Ride getRideById(String id) {
        return rides.stream().filter(ride -> ride.getRideId().equals(id)).findFirst().
                orElseThrow(() -> new RuntimeException("ride not found"));
    }

    @Override
    public Set<Ride> getAllRides() {
        return rides;
    }
}
