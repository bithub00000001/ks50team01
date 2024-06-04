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

import ksmart.ks50team01.platform.ranking.dto.PlanRanking;
import ksmart.ks50team01.platform.ranking.service.PlanRankingService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value="/platform/ranking")
@RequiredArgsConstructor
public class PlanRankingController {

	/**
	 * 의존성 주입
	 */
	private final PlanRankingService planRankingService;
	
	/**
	 * 여행계획리스트 삭제
	 * @param userTripPlanId
	 * @param model
	 * @return
	 */
	@PostMapping("/removePlanRanking")
	public String remove(@RequestParam(name = "userTripPlanId") String userTripPlanId
						,Model model) {
		model.addAttribute("title", "여행계획추천리스트 삭제");
		model.addAttribute("userTripPlanId", userTripPlanId);
		return "platform/ranking/removePlanRanking";
	}
	
	/**
	 * 여행계획 리스트 삭제 정보
	 * @param userTripPlanId
	 * @param model
	 * @return
	 */
	@GetMapping("/removePlanRanking")
	public String removePlanRanking(@RequestParam(name="userTripPlanId") String userTripPlanId
			,Model model) {
		List<PlanRanking> planRankingList = planRankingService.getPlanRankingList();
		planRankingService.removePlanRanking(userTripPlanId);
		model.addAttribute("removePlanRanking",userTripPlanId);
		model.addAttribute("planRankingList", planRankingList);
		return "redirect:/platform/ranking/planRankingList";
	}
	/**
	 * 여행계획 리스트 수정
	 * @param planRanking
	 * @return
	 */
	@PostMapping("/modifyPlanRanking")
	public String modify(@ModelAttribute PlanRanking planRanking) {
		planRankingService.modifyPlanRanking(planRanking);
		return "redirect:/platform/ranking/planRankingList";
	}
	/**
	 * 여행계획 리스트 수정정보
	 * @param userTripPlanId
	 * @param model
	 * @return
	 */
	@GetMapping("/modifyPlanRanking")
	public String modifyPlanRanking(@RequestParam(name="userTripPlanId") String userTripPlanId
							  ,Model model) {
		PlanRanking planRanking = planRankingService.getPlanRankingInfoById(userTripPlanId);
		List<PlanRanking> planRankingList = planRankingService.getPlanRankingList();
		
		model.addAttribute("title", "여행계획 추천리스트 수정");
		model.addAttribute("planRanking", planRanking);
		model.addAttribute("planRankingList", planRankingList);
		
		return "platform/ranking/modifyPlanRanking";
	}
	/**
	 * 여행계획 리스트 등록
	 * @param planRanking
	 * @return
	 */
	@PostMapping("/addPlanUserRanking")
	public String addPlanUserRanking(PlanRanking planRanking) {
		planRankingService.addPlanUserRanking(planRanking);
		return "redirect:/platform/ranking/planRankingList";
	}
	/**
	 * 여행계획 리스트 등록정보
	 * @param model
	 * @return
	 */
	@GetMapping("/addPlanUserRanking")
	public String addPlanUserRanking(Model model) {
		List<PlanRanking> planRankingList = planRankingService.getPlanRankingList();
		System.out.println("planRankingList: " + planRankingList);
		model.addAttribute("title", "여행계획 추천천리스트 등록");
		model.addAttribute("planRankingList", planRankingList);
		return "platform/ranking/addPlanUserRanking";
	}
	/**
	 * 여행계획 리스트 중복체크
	 * @param userTripPlanId
	 * @return
	 */
	@PostMapping("/planRankingListCheck")
	@ResponseBody
	public boolean planRankingListCheck(@RequestParam(value="tripPlanRank") int tripPlanRank) {
		boolean isTripPlanRank = planRankingService.planRankingListCheck(tripPlanRank);
		return  isTripPlanRank;
	}
	/**
	 * 여행계획 리스트 등록
	 * @param model
	 * @return
	 */
	@GetMapping("/planRankingList")
	public String getPlanRankingList(Model model) {
		
		List<PlanRanking> planRankingList = planRankingService.getPlanRankingList();
		model.addAttribute("title", "여행계획 추천");
		model.addAttribute("planRankingList", planRankingList);
		
		return "platform/ranking/planRankingList";
		
	}
}
