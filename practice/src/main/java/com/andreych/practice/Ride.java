package com.andreych.practice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.lang.Math;
public class Ride
{
    Order zero;
    public Ride()
    {
        zero = new Order(); //пустой заказ, служит головой списка
        zero.next = zero;
        zero.prev = zero;
    }

    public void addOrder(Order order)
    {
        order.next = zero;
        order.prev = zero.prev;
        zero.prev.prev = order;
        zero.prev = order;
    }

    public void removeOrder(Order order)
    {
        order.prev.next = order.next;
        order.next.prev = order.prev;
    }
}