package ksmart.ks50team01.platform.ranking.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ksmart.ks50team01.platform.ranking.dto.UserRanking;
import ksmart.ks50team01.platform.ranking.service.UserRankingService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value="/platform/ranking")
@RequiredArgsConstructor
public class UserRankingController {

	@GetMapping("/UserRankingList")
	public String getUserRankingList(Model model) {
		
		
		
		model.addAttribute("title", "회원 추천 관리");
//		model.addAttribute("UserRankingList", UserRankingList);
		return "platform/ranking/UserRankingList";
	}
}
