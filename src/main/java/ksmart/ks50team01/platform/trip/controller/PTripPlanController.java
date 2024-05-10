package ksmart.ks50team01.platform.trip.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ksmart.ks50team01.platform.trip.dto.PTripPlan;
import ksmart.ks50team01.platform.trip.service.PTripPlanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/platform/plan")
@Slf4j
@RequiredArgsConstructor
public class PTripPlanController {

	private final PTripPlanService pTripPlanService;

	// 여행 계획 목록 수정 post 요청
	@PostMapping("/modify")
	public String modify(PTripPlan pTripPlan, Model model) {
		model.addAttribute("title", "여행 계획 수정 페이지");
		return "redirect:/platform/plan/list";
	}

	// 여행 계획 목록 수정 페이지
	@GetMapping("/modify")
	public String planModify(@RequestParam(name = "planId") String planId,
		Model model) {
		PTripPlan pTripPlan = pTripPlanService.getPTripPlanById(planId);
		model.addAttribute("title", "여행 계획 수정");
		model.addAttribute("planInfo", pTripPlan);

		return "/platform/trip/planModify";
	}


	// 여행 계획 목록 조회 페이지
	@GetMapping("/list")
	public String planList(Model model) {
		List<PTripPlan> tripPlanList = pTripPlanService.getAllPTripPlan();
		log.info("tripPlanList: {}", tripPlanList);
		model.addAttribute("title", "여행 계획 리스트 조회");
		model.addAttribute("tripPlanList", tripPlanList);
		return "platform/trip/planList";
	}
}
