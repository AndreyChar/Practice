package com.andreych.practice;

import com.andreych.practice.application.SharedRidesApi;
import com.andreych.practice.dao.SharedRidesDao;
import com.andreych.practice.domain.Location;
import com.andreych.practice.domain.Order;
import com.andreych.practice.domain.Ride;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//
// import java.util
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PracticeApplicationTests {
    @Autowired
    SharedRidesApi sharedRidesApi;

    @Autowired
    SharedRidesDao sharedRidesDao;

    @Test
    void saveRide() {
        Order order = new Order();

        Location locationFrom = new Location(55.699060, 37.661554, "Moscow");
        Location locationTo = new Location(55.692060, 37.663554, "Moscow");
        order.setPickup(locationFrom);
        order.setDrop(locationTo);
        order.setStartDateTime(new Date());
        String rideId = new Object().toString();
        Ride ride = new Ride(rideId, Collections.singletonList(order));
        sharedRidesApi.newRide(ride);

        Ride savedRide = sharedRidesDao.getRideById(rideId);
        assertEquals(ride, savedRide);
    }

    @Test
    void supposedRides()
    {
        Order order = new Order();

        Location locationFrom = new Location(55.699060, 37.661554, "Moscow");
        Location locationTo = new Location(55.692060, 37.663554, "Moscow");
        order.setPickup(locationFrom);
        order.setDrop(locationTo);
        order.setStartDateTime(new Date());
        String rideId = new Object().toString();
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(order);
        Ride ride = new Ride(rideId, orderList);
        //Ride ride = new Ride(rideId, Collections.singletonList(order));
        sharedRidesApi.newRide(ride);

        Order order1 = new Order();
        Location locationFrom1 = new Location(54.699060, 37.661554, "Moscow");
        Location locationTo1 = new Location(55.692060, 37.663554, "Moscow");
        order1.setPickup(locationFrom);
        order1.setDrop(locationTo);
        order1.setStartDateTime(new Date());
        Set<Ride> setRide = sharedRidesApi.getSupposedRides(order1);
        assertEquals(1,setRide.size());
    }
}
