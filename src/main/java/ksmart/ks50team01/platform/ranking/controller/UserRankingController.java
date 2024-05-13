package ksmart.ks50team01.platform.ranking.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ksmart.ks50team01.platform.ranking.dto.UserRanking;
import ksmart.ks50team01.platform.ranking.service.UserRankingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value="/platform/ranking")
@RequiredArgsConstructor
@Slf4j
public class UserRankingController {

	private final UserRankingService userRankingService;
	
	@GetMapping("/userRankingList")
	public String getUserRankingList(Model model) {
		
		List<UserRanking> userRankingList = userRankingService.getUserRankingList();
		log.info("UserRankingController getUserRankingList: {}", userRankingList);
		
		model.addAttribute("title", "회원 추천 관리");
		model.addAttribute("userRankingList", userRankingList);

		return "platform/ranking/userRankingList";
	}
}
