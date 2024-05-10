package ksmart.ks50team01.platform.ranking.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ksmart.ks50team01.platform.ranking.dto.PlanRanking;
import ksmart.ks50team01.platform.ranking.service.PlanRankingService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value="/platform/ranking")
@RequiredArgsConstructor
public class PlanRankingController {

	private final PlanRankingService planRankingService;
	
	@GetMapping("/planRankingList")
	public String getPlanRankingList(Model model) {
		
		List<PlanRanking> planRankingList = planRankingService.getPlanRankingList();
		model.addAttribute("title", "여행계획 추천");
		model.addAttribute("planRankingList", planRankingList);
		
		return "platform/ranking/planRankingList";
		
	}
}
