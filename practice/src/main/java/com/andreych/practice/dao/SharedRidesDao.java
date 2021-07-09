package com.andreych.practice.dao;

import com.andreych.practice.domain.Ride;

public interface SharedRidesDao {
    void saveRide(Ride ride);

    Ride getRideById(String id);
}
