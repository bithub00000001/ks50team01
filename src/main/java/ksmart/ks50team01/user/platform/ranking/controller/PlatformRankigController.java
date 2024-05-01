package ksmart.ks50team01.user.platform.ranking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/user")
public class PlatformRankigController {

	@GetMapping("/platformranking/platformranking")
	public String Partnershipranking(Model model) {
		
		model.addAttribute("title", "추천페이지");
		return "user/platformranking/platformranking";
	}
	
	@GetMapping("/platformranking/platformRankingList")
	public String PlatformRankingList(Model model) {
		
		model.addAttribute("title", "플랫폼 추천 인기순위 페이지");
		return "user/platformranking/platformRankingList";
	}
	@GetMapping("/platformranking/userRankingList")
	public String UserRankingList(Model model) {
		
		model.addAttribute("title", "회원추천 인기순위 페이지");
		return "user/platformranking/userRankingList";
	}
	@GetMapping("/platformranking/planRankingList")
	public String PlanRankingList(Model model) {
		
		model.addAttribute("title", "다른회원의 여행계획 추천페이지");
		return "user/platformranking/planRankingList";
	}
}

