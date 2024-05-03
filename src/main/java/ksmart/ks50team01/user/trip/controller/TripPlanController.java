package ksmart.ks50team01.user.trip.controller;

import java.time.LocalDate;

import ksmart.ks50team01.user.trip.dto.TripOption;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/trip")
@RequiredArgsConstructor
@Slf4j
public class TripPlanController {

    @GetMapping("/plan")
    public String tripPlanPage(Model model){
        model.addAttribute("title", "여행 계획 작성");
        return "user/trip/tripItem";
    }

    @GetMapping("/board")
    public String boardPage(Model model){
        model.addAttribute("title", "여행 계획 공유");
        return "user/trip/sharePlan";
    }

    @GetMapping("/create")
    public String tripCreatePage(Model model){
        model.addAttribute("title", "여행 계획 작성");
        model.addAttribute("tripOption", new TripOption());
        return "user/trip/tripPlan";
    }

    @PostMapping("/create")
    public String processTripDetails(@ModelAttribute("tripOption") TripOption tripOption, Model model) {
        String dateRange = tripOption.getDateRange();
        if (dateRange != null && !dateRange.isEmpty()) {
            String[] dates = dateRange.split(" - ");
            LocalDate startDate = LocalDate.parse(dates[0]);
            LocalDate endDate = LocalDate.parse(dates[1]);

            log.info("startDate: {}", startDate);
            log.info("endDate: {}", endDate);
            tripOption.setStartDate(startDate);
            tripOption.setEndDate(endDate);
        }
        log.info("tripOption: {}", tripOption);
        log.info("dateRange: {}", dateRange);

        model.addAttribute("tripOption", tripOption);
        return "redirect:/trip/create";
    }
}
