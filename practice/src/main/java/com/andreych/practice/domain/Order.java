package com.andreych.practice.domain;

import java.util.Date;

public class Order {
    private Location pickup;
    private Location drop;
    private Date startDateTime;
    private double distance;

    public Order(){};
    public Order(Location pickup, Location drop, Date startDateTime)
    {
        this.pickup = pickup;
        this.drop = drop;
        this.startDateTime = startDateTime;
    }

    public Location getPickup() {
        return pickup;
    }

    public void setPickup(Location pickup) {
        this.pickup = pickup;
    }

    public Location getDrop() {
        return drop;
    }

    public void setDrop(Location drop) {
        this.drop = drop;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public void setDistance(double distance)
    {
        this.distance = distance;
    }

    public double getDistance()
    {
        return distance;
    }
}
