package ksmart.ks50team01.platform.ranking.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ksmart.ks50team01.platform.ranking.dto.Ranking;
import ksmart.ks50team01.platform.ranking.service.RankingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value="/platform/ranking")
@RequiredArgsConstructor
@Slf4j
public class RankingController {

	private final RankingService rankingService;
	
	@GetMapping("/rankingList")
	public String getRankingList(Model model) {
		
		List<Ranking> rankingList = rankingService.getRankingList();
		
		model.addAttribute("title", "플랫폼 추천 관리");
		model.addAttribute("rankingList", rankingList);
		return "platform/ranking/rankingList";
	}
	
}
