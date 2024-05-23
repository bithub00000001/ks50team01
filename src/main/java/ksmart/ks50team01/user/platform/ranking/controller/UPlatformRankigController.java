package ksmart.ks50team01.user.platform.ranking.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ksmart.ks50team01.platform.ranking.dto.Ranking;
import ksmart.ks50team01.platform.ranking.dto.RankingApi;
import ksmart.ks50team01.platform.ranking.service.RankingService;
import ksmart.ks50team01.platform.trip.dto.PTourApi;
import ksmart.ks50team01.platform.trip.service.PTripPlanService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value="/allRanking")
@RequiredArgsConstructor
public class UPlatformRankigController {

	private final RankingService rankingServeice;
	private final PTripPlanService pTripPlanService;

	@GetMapping
	public String platformRanking(Model model) {
		
		model.addAttribute("title", "여행 추천");
		return "user/platformranking/allRanking";
	}
	
	@GetMapping("/rankingList")
	public String platformRankingList(Model model) {
		
		List<Ranking> rankingList = rankingServeice.getRankingList();
		List<RankingApi> rankingApiList = rankingServeice.getRankingInfoList();
		model.addAttribute("title", "플랫폼 추천 순위");
		model.addAttribute("rankingList", rankingList);
		model.addAttribute("rankingApiList", rankingApiList);
		return "user/platformranking/rankingList";
	}
	@GetMapping("/userRankingList")
	public String userRankingList(Model model) {
		
		model.addAttribute("title", "회원추천 인기순위");
		return "user/platformranking/userRankingList";
	}
	@GetMapping("/planRankingList")
	public String planRankingList(Model model) {
		
		model.addAttribute("title", "다른회원의 여행계획 추천");
		return "user/platformranking/planRankingList";
	}

}

