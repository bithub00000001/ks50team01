package ksmart.ks50team01.trip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/trip")
@RequiredArgsConstructor
public class TripPlanController {

    @GetMapping("/plan")
    public String tripPlanPage(){
        return "user/trip/tripPlanner";
    }
}
