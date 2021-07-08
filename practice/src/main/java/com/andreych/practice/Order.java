package com.andreych.practice;

public class Order
{
    Order next;
    Order prev;
    double xstart;
    double ystart;
    double xfinish;
    double yfinish;
    double startPrice;
    double perKMPrice;
    double resPrice;
    double km;

    public Order(double xstart, double ystart, double xfinish, double yfinish, double startPrice, Order next, Order prev)
    {
        this.xstart = xstart;
        this.ystart = ystart;
        this.xfinish = xfinish;
        this.yfinish = yfinish;
        this.startPrice = startPrice;
        perKMPrice = 5.0;
        setPrice();
        this.next = next;
        this.prev = prev;
    }

    public Order(double xfinish, double yfinish, double startPrice, Order next, Order prev)
    {
        this.xfinish = xfinish;
        this.yfinish = yfinish;
        this.startPrice = startPrice;
        perKMPrice = 5.0;
        this.next = next;
        this.prev = prev;
    }

    public Order(double xstart, double ystart, double xfinish, double yfinish, double startPrice)
    {
        this.xstart = xstart;
        this.ystart = ystart;
        this.xfinish = xfinish;
        this.yfinish = yfinish;
        this.startPrice = startPrice;
        perKMPrice = 5.0;
        setPrice();
    }

    public Order(double xfinish, double yfinish, double startPrice)
    {
        this.xfinish = xfinish;
        this.yfinish = yfinish;
        this.startPrice = startPrice;
        perKMPrice = 5.0;
    }

    public Order(){}

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
