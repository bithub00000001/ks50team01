package ksmart.ks50team01.user.trip.controller;

import java.time.format.DateTimeParseException;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ksmart.ks50team01.user.trip.dto.UTripOption;
import ksmart.ks50team01.user.trip.service.UTripPlanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/trip")
@RequiredArgsConstructor
@Slf4j
public class UTripPlanController {

    private final UTripPlanService uTripPlanService;

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
    public String tripCreatePage(@ModelAttribute("uTripOption") Optional<UTripOption> uTripOption,Model model){
        model.addAttribute("title", "여행 계획 작성");
        uTripOption.ifPresentOrElse(
            option -> model.addAttribute("uTripOption", option),
            () -> model.addAttribute("uTripOption", new UTripOption())
        );
        return "user/trip/tripPlan";
    }

    @PostMapping("/create")
    public String processTripDetails(@ModelAttribute(name = "tripOption") UTripOption uTripOption,
        RedirectAttributes redirectAttributes) {
        try {
            uTripOption = uTripPlanService.parseDateRange(uTripOption);
        } catch (DateTimeParseException e) {
            // 오류 처리 로직 사용자에게 오류 메시지 전송
            redirectAttributes.addFlashAttribute("errorMessage", "날짜 형식이 올바르지 않습니다.");
            return "redirect:/";
        }
        log.info("uTripOption: {}", uTripOption);
        redirectAttributes.addFlashAttribute("uTripOption", uTripOption);
        return "redirect:/trip/create";
    }
}
