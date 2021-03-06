package com.andreych.practice;

public class DistanceCalculatorImpl implements DistanceCalculator
{
    @Override
    public double getDistance(double lat1, double lat2, double lon1, double lon2)
    {
        double p = Math.PI/180;
        double a = 0.5 - Math.cos((lat2-lat1)*p)/2 + Math.cos(lat1*p)*Math.cos(lat2*p)*(1-Math.cos((lon2-lon1)*p))/2;
        return 2 * 6371 * Math.asin(Math.sqrt(a));
    }

    @Override
    public double getPrice(double distance)
    {
        return 30 + distance * 10;
    }
}
