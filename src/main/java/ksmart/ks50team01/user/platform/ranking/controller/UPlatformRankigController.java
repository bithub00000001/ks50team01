package ksmart.ks50team01.user.platform.ranking.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ksmart.ks50team01.platform.ranking.dto.Ranking;
import ksmart.ks50team01.platform.ranking.dto.RankingApi;
import ksmart.ks50team01.platform.ranking.dto.UserRanking;
import ksmart.ks50team01.platform.ranking.service.RankingService;
import ksmart.ks50team01.platform.ranking.service.UserRankingService;
import ksmart.ks50team01.platform.trip.service.PTripPlanService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value="/allRanking")
@RequiredArgsConstructor
public class UPlatformRankigController {

	private final RankingService rankingServeice;
	private final PTripPlanService pTripPlanService;
	private final UserRankingService userRankingService;

	@GetMapping
	public String platformRanking(Model model) {
		
		model.addAttribute("title", "여행 추천");
		return "user/platformranking/allRanking";
	}
	
	@GetMapping("/rankingList")
	public String platformRankingList(Model model) {
		
		List<Ranking> rankingList = rankingServeice.getRankingList();
		List<RankingApi> rankingApiList = rankingServeice.getRankingInfoList();
		model.addAttribute("title", "플랫폼 추천");
		model.addAttribute("rankingList", rankingList);
		model.addAttribute("rankingApiList", rankingApiList);
		return "user/platformranking/rankingList";
	}
	@GetMapping("/userRankingList")
	public String userRankingList(Model model) {
		List<UserRanking> userRankingList = userRankingService.getUserRankingList();
		
		model.addAttribute("userRankingList", userRankingList);
		model.addAttribute("title", "회원추천");
		return "user/platformranking/userRankingList";
	}
	@GetMapping("/planRankingList")
	public String planRankingList(Model model) {
		
		model.addAttribute("title", "여행계획 추천");
		return "user/platformranking/planRankingList";
	}

}

