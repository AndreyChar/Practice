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
        order1.setPickup(locationFrom1);
        order1.setDrop(locationTo1);
        order1.setStartDateTime(new Date());
        Set<Ride> setRide = sharedRidesApi.getSupposedRides(order1);
        assertEquals(1,setRide.size());
    }

    @Test
    void distanceTest()
    {
        Order order = new Order();
        Location locationFrom = new Location(54.699060, 37.661554, "Moscow");
        Location locationTo = new Location(55.692060, 37.663554, "Moscow");
        order.setPickup(locationFrom);
        order.setDrop(locationTo);
        order.setStartDateTime(new Date());
        Order order1 = new Order();
        Location locationFrom1 = new Location(54.699060, 37.661554, "Moscow");
        Location locationTo1 = new Location(55.692060, 37.664554, "Moscow");
        order1.setPickup(locationFrom1);
        order1.setDrop(locationTo1);
        order1.setStartDateTime(new Date());
        Ride ride = sharedRidesApi.findDistance(order, order1);
        List<Order> orders = ride.getOrderList();
        double[] orders2 = new double[3];
        double[] orders3 = new double[3];
        try
        {
            for(int i = 0; i < 3; i++)
            {
                orders2[i] = orders.get(i).getPickup().getLatitude();
                orders3[i] = orders.get(i).getDrop().getLatitude();
            }
        }
        catch (NullPointerException npe)
        {
            orders2[0] = -1;
            orders2[1] = -1;
            orders2[2] = -1;
        }
        double[] orders1 = new double[] {37.661554,37.664554,37.664554};
        double[] orders4 = new double[] {37.664554,37.664554,37.663554};
        assertArrayEquals(orders1, orders2);
        assertArrayEquals(orders4, orders3);
    }
}
