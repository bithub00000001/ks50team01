package ksmart.ks50team01.platform.ranking.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@PostMapping("/modifyUserRanking")
	public String modify(@ModelAttribute UserRanking userRanking) {
		userRankingService.modifyUserRanking(userRanking);
		return "redirect:/platform/ranking/userRankingList";
	}
	
	@GetMapping("/modifyUserRanking")
	public String modifyUserRanking(@RequestParam(name="userRankingId") String userRankingId
							  ,Model model) {
		UserRanking userRanking = userRankingService.getUserRankingInfoById(userRankingId);
		List<UserRanking> userRankingList = userRankingService.getUserRankingList();
		
		model.addAttribute("title", "회원추천리스트 수정");
		model.addAttribute("UserRanking", userRanking);
		model.addAttribute("UserRankingList", userRankingList);
		
		return "platform/ranking/modifyUserRanking";
	}
	
	@PostMapping("/addUserRanking")
	public String addUserRanking(UserRanking userRanking) {
		userRankingService.addUserRanking(userRanking);
		return "redirect:/platform/ranking/userRankingList";
	}
	
	@GetMapping("/addUserRanking")
	public String addUserRanking(Model model) {
		List<UserRanking> userRankingList = userRankingService.getUserRankingList();
		System.out.println("userRankingList: " + userRankingList);
		model.addAttribute("title", "회원 추천리스트 등록");
		model.addAttribute("userRankingList", userRankingList);
		return "platform/ranking/addUserRanking";
	}
	
	
	@PostMapping("/userRankingListCheck")
	@ResponseBody
	public boolean rankingListCheck(@RequestParam(value="userRankingId") String userRankingId) {
		boolean isuserRankingNum = userRankingService.userRankingListCheck(userRankingId);
		return  isuserRankingNum;
	}
	
	@GetMapping("/userRankingList")
	public String getUserRankingList(Model model) {
		
		List<UserRanking> userRankingList = userRankingService.getUserRankingList();
		log.info("UserRankingController getUserRankingList: {}", userRankingList);
		
		model.addAttribute("title", "회원 추천 관리");
		model.addAttribute("userRankingList", userRankingList);

		return "platform/ranking/userRankingList";
	}
}
