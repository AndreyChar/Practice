package com.andreych.practice;

import com.andreych.practice.dao.SharedRidesDao;
import com.andreych.practice.domain.Location;
import com.andreych.practice.domain.Order;
import com.andreych.practice.domain.Ride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.andreych.practice.application.SharedRidesApi;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class InputClass
{
    @Autowired
    SharedRidesApi sharedRidesApi;

    @Autowired
    SharedRidesDao sharedRidesDao;

    @GetMapping( "/NewPage")
    public ArrayList<ArrayList<double[]>> DistanceShow(Model model, HttpServletRequest request)
    {
        Order order = new Order();
        Location locationFrom = new Location(55.699060, 37.661554, "Moscow");
        Location locationTo = new Location(55.692060, 37.663554, "Moscow");
        order.setPickup(locationFrom);
        order.setDrop(locationTo);
        order.setStartDateTime(new Date());
        Order order1 = new Order();
        Location locationFrom1 = new Location(54.699060, 67.661554, "Moscow");
        Location locationTo1 = new Location(55.692060, 37.664554, "Moscow");
        order1.setPickup(locationFrom1);
        order1.setDrop(locationTo1);
        order1.setStartDateTime(new Date());
        Ride ride = sharedRidesApi.findDistance(order, order1);
        List<Order> orders = ride.getOrderList();
        double[] orders1 = new double[3];
        double[] orders2 = new double[3];
        double[] orders3 = new double[3];
        double[] orders4 = new double[3];
        try
        {
            for(int i = 0; i < 3; i++)
            {
                orders1[i] = orders.get(i).getPickup().getLatitude();
                orders2[i] = orders.get(i).getPickup().getLongitude();
                orders3[i] = orders.get(i).getDrop().getLatitude();
                orders4[i] = orders.get(i).getDrop().getLongitude();
            }
        }
        catch (NullPointerException npe)
        {
            orders2[0] = -1;
            orders2[1] = -1;
            orders2[2] = -1;
        }
        ArrayList<ArrayList<double[]>> somethingtoreturn = new ArrayList<>();
        for(int i = 0; i < 3; i++)
        {
            ArrayList<double[]> somethingtoreturn2 = new ArrayList<double[]>();
            double[] temp1 = {orders1[i], orders2[i]};
            double[] temp2 = {orders3[i], orders4[i]};
            somethingtoreturn2.add(temp1);
            somethingtoreturn2.add(temp2);
            somethingtoreturn.add(somethingtoreturn2);
        }
        model.addAttribute("variable1", orders2[0]);
        model.addAttribute("variable2", orders2[1]);
        model.addAttribute("variable3", orders2[2]);
        model.addAttribute("variable4", orders3[0]);
        model.addAttribute("variable5", orders3[1]);
        model.addAttribute("variable6", orders3[2]);
        return somethingtoreturn;
    }
}
