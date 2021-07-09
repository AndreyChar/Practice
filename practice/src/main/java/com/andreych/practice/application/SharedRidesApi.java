package com.andreych.practice.application;

import com.andreych.practice.domain.Order;
import com.andreych.practice.domain.Ride;

import java.util.Set;

public interface SharedRidesApi {
    void newRide(Ride ride);

    Set<Ride> getSupposedRides(Order order);

    void setOrderToRide(Order order, Ride ride);
}
