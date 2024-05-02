package ksmart.ks50team01.user.platform.ranking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/user/platformranking")
public class PlatformRankigController {

	@GetMapping("/ranking")
	public String platformRanking(Model model) {
		
		model.addAttribute("title", "추천페이지");
		return "user/platformranking/ranking";
	}
	
	@GetMapping("/rankingList")
	public String platformRankingList(Model model) {
		
		model.addAttribute("title", "플랫폼 추천 인기순위 페이지");
		return "user/platformranking/rankingList";
	}
	@GetMapping("/userRankingList")
	public String userRankingList(Model model) {
		
		model.addAttribute("title", "회원추천 인기순위 페이지");
		return "user/platformranking/userRankingList";
	}
	@GetMapping("/planRankingList")
	public String planRankingList(Model model) {
		
		model.addAttribute("title", "다른회원의 여행계획 추천페이지");
		return "user/platformranking/planRankingList";
	}
}

