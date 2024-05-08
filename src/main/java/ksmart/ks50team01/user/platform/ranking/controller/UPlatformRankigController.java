package ksmart.ks50team01.user.platform.ranking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value="/user")
@RequiredArgsConstructor
public class UPlatformRankigController {

	@GetMapping("/ranking")
	public String platformRanking(Model model) {
		
		model.addAttribute("title", "추천페이지");
		return "user/platformranking/ranking";
	}
	
	@GetMapping("/ranking/rankingList")
	public String platformRankingList(Model model) {
		
		model.addAttribute("title", "플랫폼 추천 인기순위");
		return "user/platformranking/rankingList";
	}
	@GetMapping("/ranking/userRankingList")
	public String userRankingList(Model model) {
		
		model.addAttribute("title", "회원추천 인기순위");
		return "user/platformranking/userRankingList";
	}
	@GetMapping("/ranking/planRankingList")
	public String planRankingList(Model model) {
		
		model.addAttribute("title", "다른회원의 여행계획 추천");
		return "user/platformranking/planRankingList";
	}
}

