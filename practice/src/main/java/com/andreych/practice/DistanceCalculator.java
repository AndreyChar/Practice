package com.andreych.practice;

public interface DistanceCalculator
{
    double getDistance(double lat1, double lat2, double lon1, double lon2);
    double getPrice(double distance);
}
