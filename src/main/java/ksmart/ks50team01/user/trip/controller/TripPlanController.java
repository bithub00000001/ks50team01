package ksmart.ks50team01.user.trip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/trip")
@RequiredArgsConstructor
public class TripPlanController {

    @GetMapping("/plan")
    public String tripPlanPage(Model model){
        model.addAttribute("title", "여행 계획 작성");
        return "user/trip/tripPlanner";
    }

    @GetMapping("/board")
    public String boardPage(){
        return "user/trip/boardTest";
    }
}
