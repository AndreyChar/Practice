package com.andreych.practice.application;

import com.andreych.practice.dao.SharedRidesDao;
import com.andreych.practice.domain.Order;
import com.andreych.practice.domain.Ride;

import java.util.Set;

public class SharedRidesApiImpl implements SharedRidesApi {

    private SharedRidesDao sharedRidesDao;

    @Override
    public void newRide(Ride ride) {

        sharedRidesDao.saveRide(ride);
    }

    @Override
    public Set<Ride> getSupposedRides(Order order) {
        return null;
        //todo implement
    }

    @Override
    public void setOrderToRide(Order order, Ride ride) {
        ride.getOrderList().add(order);
        sharedRidesDao.saveRide(ride);
    }
}
