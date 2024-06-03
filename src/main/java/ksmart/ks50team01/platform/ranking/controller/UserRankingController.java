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
import ksmart.ks50team01.platform.trip.dto.PTourApi;
import ksmart.ks50team01.platform.trip.service.PTripPlanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value="/platform/ranking")
@RequiredArgsConstructor
@Slf4j
public class UserRankingController {
	
	/**
	 * 의존성 주입
	 */
	private final UserRankingService userRankingService;
	private final PTripPlanService pTripPlanService;
	
	/**
	 * 회원추천리스트 삭제
	 * @param userRankingId
	 * @param model
	 * @return
	 */
	@PostMapping("/removeUserRanking")
	public String remove(@RequestParam(name = "userRankingId") String userRankingId
						,Model model) {
		model.addAttribute("title", "회원추천리스트 삭제");
		model.addAttribute("userRankingId", userRankingId);
		return "platform/ranking/removeUserRanking";
	}
	/**
	 * 회원추천리스트 삭제정보
	 * @param userRankingId
	 * @param model
	 * @return
	 */
	@GetMapping("/removeUserRanking")
	public String removeUserRanking(@RequestParam(name="userRankingId") String userRankingId
			,Model model) {
		List<UserRanking> userRankingList = userRankingService.getUserRankingList();
		userRankingService.removeUserRanking(userRankingId);
		model.addAttribute("removeUserRanking",userRankingId);
		model.addAttribute("userRankingList", userRankingList);
		return "redirect:/platform/ranking/userRankingList";
	}
	/**
	 * 회원추천리스트 수정
	 * @param userRanking
	 * @return
	 */
	@PostMapping("/modifyUserRanking")
	public String modify(@ModelAttribute UserRanking userRanking) {
		userRankingService.modifyUserRanking(userRanking);
		return "redirect:/platform/ranking/userRankingList";
	}
	/**
	 * 회원추천리스트 수정 정보
	 * @param userRankingId
	 * @param model
	 * @return
	 */
	@GetMapping("/modifyUserRanking")
	public String modifyUserRanking(@RequestParam(name="userRankingId") String userRankingId
							  ,Model model) {
		UserRanking userRanking = userRankingService.getUserRankingInfoById(userRankingId);
		List<UserRanking> userRankingList = userRankingService.getUserRankingList();
		
		model.addAttribute("title", "회원추천리스트 수정");
		model.addAttribute("userRanking", userRanking);
		model.addAttribute("userRankingList", userRankingList);
		
		return "platform/ranking/modifyUserRanking";
	}
	/**
	 * 회원추천리스트 등록
	 * @param userRanking
	 * @return
	 */
	@PostMapping("/addUserRanking")
	public String addUserRanking(UserRanking userRanking) {
		userRankingService.addUserRanking(userRanking);
		return "redirect:/platform/ranking/userRankingList";
	}
	/**
	 * 회원추천리스트 등록정보
	 * @param model
	 * @return
	 */
	@GetMapping("/addUserRanking")
	public String addUserRanking(Model model) {
		List<UserRanking> userRankingList = userRankingService.getUserRankingList();
	
		model.addAttribute("title", "회원 추천리스트 등록");
		model.addAttribute("userRankingList", userRankingList);
		return "platform/ranking/addUserRanking";
	}
	
	/**
	 * 회원추천관리 리스트 중복체크
	 * @param userRankingId
	 * @return
	 */
	@PostMapping("/userRankingListCheck")
	@ResponseBody
	public boolean rankingListCheck(@RequestParam(value="userRank") int userRank) {
		boolean isUserRank = userRankingService.userRankingListCheck(userRank);
		return  isUserRank;
	}
	/**
	 * 회원추천관리 리스트 조회
	 * @param model
	 * @return
	 */
	@GetMapping("/userRankingList")
	public String getUserRankingList(Model model) {
		List<PTourApi> pTourApiList = pTripPlanService.getDestinationList();
		List<UserRanking> userRankingList = userRankingService.getUserRankingList();
		log.info("UserRankingController getUserRankingList: {}", userRankingList);
		
		model.addAttribute("title", "회원 추천 관리");
		model.addAttribute("userRankingList", userRankingList);
		model.addAttribute("pTourApiList", pTourApiList);
		return "platform/ranking/userRankingList";
	}
}
