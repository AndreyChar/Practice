package com.andreych.practice.dao;

import com.andreych.practice.domain.Ride;
import java.util.Set;

public interface SharedRidesDao {
    void saveRide(Ride ride);

    Ride getRideById(String id);

    Set<Ride> getAllRides();
}
