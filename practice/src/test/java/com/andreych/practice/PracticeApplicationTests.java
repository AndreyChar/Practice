package com.andreych.practice;

import com.andreych.practice.application.SharedRidesApi;
import com.andreych.practice.dao.SharedRidesDao;
import com.andreych.practice.domain.Location;
import com.andreych.practice.domain.Order;
import com.andreych.practice.domain.Ride;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Date;

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

}
