package com.andreych.practice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.lang.Math;
public class Ride
{
    double xstart;
    double ystart;
    double xfinish;
    double yfinish;
    double startPrice;
    double perKMPrice;
    double resPrice;
    double km;

    public Ride(double xstart, double ystart, double xfinish, double yfinish, double startPrice)
    {
        this.xstart = xstart;
        this.ystart = ystart;
        this.xfinish = xfinish;
        this.yfinish = yfinish;
        this.startPrice = startPrice;
        perKMPrice = 5.0;
        setPrice();
    }

    public Ride(double xfinish, double yfinish, double startPrice)
    {
        this.xfinish = xfinish;
        this.yfinish = yfinish;
        this.startPrice = startPrice;
        perKMPrice = 5.0;
    }

    public void setPrice()
    {
        km = Math.sqrt(Math.pow(xfinish - xstart, 2) + Math.pow(yfinish - ystart, 2));
        resPrice = perKMPrice*km + startPrice;
    }

    public void changeFinish(double xfinish, double yfinish)
    {
        this.xfinish = xfinish;
        this.yfinish = yfinish;
        setPrice();
    }

    public void changeStart(double xstart, double ystart)
    {
        this.xstart = xstart;
        this.ystart = ystart;
        setPrice();
    }
}