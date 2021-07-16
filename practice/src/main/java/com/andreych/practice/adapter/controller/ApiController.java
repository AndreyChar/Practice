package com.andreych.practice.adapter.controller;

import com.andreych.practice.DistanceCalculator;
import com.andreych.practice.DistanceCalculatorImpl;
import com.andreych.practice.adapter.dto.NewOrderDtoRq;
import com.andreych.practice.adapter.dto.NewRideDtoRq;
import com.andreych.practice.application.SharedRidesApi;
import com.andreych.practice.application.SharedRidesApiImpl;
import com.andreych.practice.dao.SharedRidesDao;
import com.andreych.practice.dao.impl.SharedRidesDaoImpl;
import com.andreych.practice.domain.Location;
import com.andreych.practice.domain.Order;
import com.andreych.practice.domain.Ride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;

@RestController
public class ApiController {
    @Autowired
    SharedRidesDao sharedRidesDao = new SharedRidesDaoImpl();

    @Autowired
    SharedRidesApi sharedRidesApi = new SharedRidesApiImpl(sharedRidesDao);

    @PostMapping("/newRide")
    public int newRide(@RequestBody NewRideDtoRq newRideDtoRq) {
        Order order = new Order();
        Location locationFrom = new Location(newRideDtoRq.getStartLongitude(), newRideDtoRq.getStartLatitude(),
                newRideDtoRq.getStartAddress());
        Location locationTo = new Location(newRideDtoRq.getEndLongitude(), newRideDtoRq.getEndLatitude(),
                newRideDtoRq.getEndAddress());
        order.setPickup(locationFrom);
        order.setDrop(locationTo);
        order.setStartDateTime(newRideDtoRq.getStartTime());
        Ride ride = new Ride(UUID.randomUUID().toString(), Collections.singletonList(order));
        DistanceCalculator dc = new DistanceCalculatorImpl();
        ride.setDistance(dc.getDistance(newRideDtoRq.getStartLatitude(),newRideDtoRq.getEndLatitude(),
                newRideDtoRq.getStartLongitude(),newRideDtoRq.getEndLongitude()));
        sharedRidesDao.saveRide(ride);
        return sharedRidesDao.getAllRides().size();
    }

    @GetMapping("/newRide")
    public Set<Ride> newOrder(@RequestBody NewOrderDtoRq newOrderDtoRq)
    {
        Order order = new Order();
        Location locationFrom = new Location(newOrderDtoRq.getStartLongitude(), newOrderDtoRq.getStartLatitude(),
                newOrderDtoRq.getStartAddress());
        Location locationTo = new Location(newOrderDtoRq.getEndLongitude(), newOrderDtoRq.getEndLatitude(),
                newOrderDtoRq.getEndAddress());
        order.setPickup(locationFrom);
        order.setDrop(locationTo);
        order.setStartDateTime(newOrderDtoRq.getStartTime());
        Set<Ride> rides = sharedRidesApi.getSupposedRides(order);
        DistanceCalculator dc = new DistanceCalculatorImpl();
        return rides;
    }

    //@GetMapping("/getDistanceAndPrice")
    //public Set<Double> getDistanceAndPrice()
    //{

    //}
}
