package com.andreych.practice.application;

import com.andreych.practice.DistanceCalculator;
import com.andreych.practice.DistanceCalculatorImpl;
import com.andreych.practice.dao.SharedRidesDao;
import com.andreych.practice.domain.Location;
import com.andreych.practice.domain.Order;
import com.andreych.practice.domain.Ride;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class SharedRidesApiImpl implements SharedRidesApi {

    public SharedRidesApiImpl(SharedRidesDao sharedRidesDao) {
        this.sharedRidesDao = sharedRidesDao;
    }

    int min_id;
    private SharedRidesDao sharedRidesDao;

    @Override
    public void newRide(Ride ride) {

        sharedRidesDao.saveRide(ride);
    }

    @Override
    public Set<Ride> getSupposedRides(Order order) {
        Set<Ride> rides = new HashSet<Ride>();
        Set<Ride> srd = sharedRidesDao.getAllRides();
        for(Ride r : srd)
        {
            List<Order> orders = r.getOrderList();
            int amount = orders.size();
            Ride ride = null;
            if(amount == 1)
            {
                ride = findDistance(orders.get(0), order);
            }
            if(ride != null)
            {
                //sharedRidesDao.saveRide(ride);
                rides.add(ride);
            }
            orders = r.getOrderList();
            int amount2 = orders.size();
            if(amount != amount2) rides.add(r);
            System.out.println(r.toString());
        }
        return rides;
        //todo implement
    }

    @Override
    public void setOrderToRide(Order order, Ride ride) {
        ride.getOrderList().add(order);
        sharedRidesDao.saveRide(ride);
    }

    public Ride findDistance(Order one, Order two)
    {
        DistanceCalculator dc = new DistanceCalculatorImpl();
        double onelat1 = one.getPickup().getLatitude();
        double onelon1 = one.getPickup().getLongitude();
        double onelat2 = one.getDrop().getLatitude();
        double onelon2 = one.getDrop().getLongitude();
        double twolat1 = two.getPickup().getLatitude();
        double twolon1 = two.getPickup().getLongitude();
        double twolat2 = two.getDrop().getLatitude();
        double twolon2 = two.getDrop().getLongitude();
        double res = -1;
        double[] results = new double[10];
        double[] prices = new double[10];
        var1s1d2s2d:
        results[0] = dc.getDistance(onelat1, onelat2, onelon1, onelon2)
                + dc.getDistance(onelat2, twolat1, onelon2, twolon1)
                + dc.getDistance(twolat1, twolat2, twolon1, twolon2);
        var1s2s1d2d:
        results[1] = dc.getDistance(onelat1, twolat1, onelon1, twolon1)
                + dc.getDistance(twolat1, onelat2, twolon1, onelon2)
                + dc.getDistance(onelat2, twolat2, onelon2, twolon2);
        var1s2s2d1d:
        results[2] = dc.getDistance(onelat1, twolat1, onelon1, twolon1)
                + dc.getDistance(twolat1, twolat2, twolon1, twolon2)
                + dc.getDistance(twolat2, onelat2, twolon2, onelon2);
        var2s2d1s1d:
        results[3] = dc.getDistance(twolat1, twolat2, twolon1, twolon2)
                + dc.getDistance(twolat2, onelat1, twolon2, onelon1)
                + dc.getDistance(onelat1, onelat2, onelon1, onelon2);
        var2s1s1d2d:
        results[4] = dc.getDistance(twolat1, onelat1, twolon1, onelon1)
                + dc.getDistance(onelat1, onelat2, onelon1, onelon2)
                + dc.getDistance(onelat2, twolat2, onelon2, twolon2);
        var2s1s1d2d:
        results[5] = dc.getDistance(twolat1, onelat1, twolon1, onelon1)
                + dc.getDistance(onelat1, onelat2, onelon1, onelon2)
                + dc.getDistance(onelat2, twolat2, onelon2, twolon2);
        results[8] = dc.getDistance(onelat1,onelat2,onelon1,onelon2);
        results[9] = dc.getDistance(twolat1,twolat2,twolon1,twolon2);
        results[6] = Double.MAX_VALUE;
        results[7] = Double.MAX_VALUE;
        if(onelat1 == twolat1 && onelon1 == twolon2)
        {
            results[6] = dc.getDistance(onelat1, onelat2, onelon1, onelon2)
                    + dc.getDistance(onelat2, twolat2, onelon2, twolon2);
            results[7] = dc.getDistance(twolat1, twolat2, twolon1, twolon2)
                    + dc.getDistance(twolat2, onelat2, twolon1, onelon2);
        }
        prices[7] = dc.getPrice(results[7]);
        prices[0] = dc.getPrice(results[0]);
        prices[1] = dc.getPrice(results[1]);
        prices[2] = dc.getPrice(results[2]);
        prices[3] = dc.getPrice(results[3]);
        prices[4] = dc.getPrice(results[4]);
        prices[5] = dc.getPrice(results[5]);
        min_id = 0;
        double min = results[0];
        for(int i = 0; i < 8; i++)
        {
            if(results[i] < min)
            {
                min = results[i];
                min_id = i;
            }
        }
        Ride ride = new Ride(new Object().toString(), null);
        Order o1, o2, o3;
        if(min_id == 0)
        {
            o1 = new Order(new Location(onelon1, onelat1, ""),
                    new Location(onelon2, onelat2,""), one.getStartDateTime());
            o2 = new Order(new Location(onelon2, onelat2, ""),
                    new Location(twolon1, twolat1,""), one.getStartDateTime());
            o3 = new Order(new Location(twolon1, twolat1, ""),
                    new Location(twolon2, twolat2,""), two.getStartDateTime());
            ArrayList<Order> orders = new ArrayList<Order>();
            orders.add(o1);
            orders.add(o2);
            orders.add(o3);
            ride.setDistance(results[min_id]);
            ride.setOrderList(orders);
        }
        else if(min_id == 1)
        {
            o1 = new Order(new Location(onelon1, onelat1, ""),
                    new Location(twolon1, twolat1,""), one.getStartDateTime());
            o2 = new Order(new Location(twolon1, twolat1, ""),
                    new Location(onelon2, onelat2,""), one.getStartDateTime());
            o3 = new Order(new Location(onelon2, onelat2, ""),
                    new Location(twolon2, twolat2,""), two.getStartDateTime());
            ArrayList<Order> orders = new ArrayList<Order>();
            orders.add(o1);
            orders.add(o2);
            orders.add(o3);
            ride.setDistance(results[min_id]);
            ride.setOrderList(orders);
        }
        else if(min_id == 2)
        {
            o1 = new Order(new Location(onelon1, onelat1, ""),
                    new Location(twolon1, twolat1,""), one.getStartDateTime());
            o2 = new Order(new Location(twolon1, twolat1, ""),
                    new Location(twolon2, twolat2,""), one.getStartDateTime());
            o3 = new Order(new Location(twolon2, twolat2, ""),
                    new Location(onelon2, onelat2,""), two.getStartDateTime());
            ArrayList<Order> orders = new ArrayList<Order>();
            orders.add(o1);
            orders.add(o2);
            orders.add(o3);
            ride.setDistance(results[min_id]);
            double price = dc.getPrice(results[min_id]);
            ride.setPrice(price);
            ride.setOrderList(orders);
        }
        else if(min_id == 3)
        {
            o1 = new Order(new Location(twolon1, twolat1, ""),
                    new Location(twolon2, twolat2,""), one.getStartDateTime());
            o2 = new Order(new Location(twolon2, twolat2, ""),
                    new Location(onelon1, onelat1,""), one.getStartDateTime());
            o3 = new Order(new Location(onelon1, onelat1, ""),
                    new Location(onelon2, onelat2,""), two.getStartDateTime());
            ArrayList<Order> orders = new ArrayList<Order>();
            orders.add(o1);
            orders.add(o2);
            orders.add(o3);
            ride.setDistance(results[min_id]);
            double price = dc.getPrice(results[min_id]);
            ride.setPrice(price);
            ride.setOrderList(orders);
        }
        else if(min_id == 4)
        {
            o1 = new Order(new Location(twolon1, twolat1, ""),
                    new Location(onelon1, onelat1,""), one.getStartDateTime());
            o2 = new Order(new Location(onelon1, onelat1, ""),
                    new Location(twolon2, twolat2,""), one.getStartDateTime());
            o3 = new Order(new Location(twolon2, twolat2, ""),
                    new Location(onelon2, onelat2,""), two.getStartDateTime());
            ArrayList<Order> orders = new ArrayList<Order>();
            orders.add(o1);
            orders.add(o2);
            orders.add(o3);
            ride.setDistance(results[min_id]);
            double price = dc.getPrice(results[min_id]);
            ride.setPrice(price);
            ride.setOrderList(orders);
        }
        else if(min_id == 5)
        {
            o1 = new Order(new Location(twolon1, twolat1, ""),
                    new Location(onelon1, onelat1,""), one.getStartDateTime());
            o2 = new Order(new Location(onelon1, onelat1, ""),
                    new Location(onelon2, onelat2,""), one.getStartDateTime());
            o3 = new Order(new Location(onelon2, onelat2, ""),
                    new Location(twolon2, twolat2,""), two.getStartDateTime());
            ArrayList<Order> orders = new ArrayList<Order>();
            orders.add(o1);
            orders.add(o2);
            orders.add(o3);
            ride.setDistance(results[min_id]);
            double price = dc.getPrice(results[min_id]);
            ride.setPrice(price);
            ride.setOrderList(orders);
        }
        else if(min_id == 6)
        {
            o1 = new Order(new Location(onelon1, onelat1, ""),
                    new Location(onelon2, onelat2,""), one.getStartDateTime());
            o2 = new Order(new Location(onelon2, onelat2, ""),
                    new Location(onelon2, onelat2,""), one.getStartDateTime());
            o3 = new Order(new Location(onelon2, onelat2, ""),
                    new Location(twolon2, twolat2,""), two.getStartDateTime());
            ArrayList<Order> orders = new ArrayList<Order>();
            orders.add(o1);
            orders.add(o2);
            orders.add(o3);
            ride.setDistance(results[min_id]);
            double price = dc.getPrice(results[min_id]);
            ride.setPrice(price);
            ride.setOrderList(orders);
        }
        else if(min_id == 7)
        {
            o1 = new Order(new Location(twolon1, twolat1, ""),
                    new Location(twolon2, twolat2,""), one.getStartDateTime());
            o2 = new Order(new Location(twolon2, twolat2, ""),
                    new Location(twolon2, twolat2,""), one.getStartDateTime());
            o3 = new Order(new Location(twolon2, twolat2, ""),
                    new Location(onelon2, onelat2,""), two.getStartDateTime());
            ArrayList<Order> orders = new ArrayList<Order>();
            orders.add(o1);
            orders.add(o2);
            orders.add(o3);
            ride.setDistance(results[min_id]);
            double price = dc.getPrice(results[min_id]);
            ride.setPrice(price);
            ride.setOrderList(orders);
        }
        return ride;
        //double orderPrice = dc.getPrice(dc.getDistance(one.getPickup().getLatitude(),
                //one.getDrop().getLatitude(), one.getPickup().getLongitude(), one.getDrop().getLongitude()));
    }
}
