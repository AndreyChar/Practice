package com.andreych.practice.application;

import com.andreych.practice.dao.SharedRidesDao;
import com.andreych.practice.domain.Order;
import com.andreych.practice.domain.Ride;

import java.util.*;

public class SharedRidesApiImpl implements SharedRidesApi {

    private SharedRidesDao sharedRidesDao;

    @Override
    public void newRide(Ride ride) {

        sharedRidesDao.saveRide(ride);
    }

    @Override
    public Set<Ride> getSupposedRides(Order order) {
        Set<Ride> rides = null;
        Set<Ride> srd = sharedRidesDao.getAllRides();
        for(Ride r : srd)
        {
            Ride r1 = r;
            List<Order> orders = r.getOrderList();
            for(int i = 0; i < orders.size(); i++)
            {
                if(orders.get(i).getStartDateTime() == order.getStartDateTime())
                {
                    setOrderToRide(order, r);
                }
                else if(orders.get(i).getDrop().getAddress() == order.getDrop().getAddress())
                {
                    setOrderToRide(order,r);
                }
                else if(orders.get(i).getPickup().getAddress() == order.getPickup().getAddress())
                {
                    setOrderToRide(order,r);
                }
            }
            if(!r.equals(r1)) rides.add(r);
        }
        return rides;
        //todo implement
    }

    @Override
    public void setOrderToRide(Order order, Ride ride) {
        ride.getOrderList().add(order);
        sharedRidesDao.saveRide(ride);
    }
}
