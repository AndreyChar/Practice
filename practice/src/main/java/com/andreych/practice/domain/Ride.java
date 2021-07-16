package com.andreych.practice.domain;


import java.util.List;
import java.util.Objects;

public class Ride {
    public Ride(String rideId, List<Order> orderList) {
        this.rideId = rideId;
        this.orderList = orderList;
    }

    String rideId;
    List<Order> orderList;
    double price;
    double distance;

    public void setDistance(double...distances)
    {
        distance = 0;
        for(double d : distances)
        {
            distance += d;
        }
    }

    public double getDistance()
    {
        return distance;
    }

    public void setPrice(double price){ this.price = price; }

    public String getRideId() {
        return rideId;
    }

    public void setRideId(String rideId) {
        this.rideId = rideId;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ride ride = (Ride) o;
        return Objects.equals(rideId, ride.rideId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rideId);
    }
}
