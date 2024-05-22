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

import ksmart.ks50team01.platform.ranking.dto.Ranking;
import ksmart.ks50team01.platform.ranking.dto.RankingApi;
import ksmart.ks50team01.platform.ranking.service.RankingService;
import ksmart.ks50team01.platform.trip.dto.PTourApi;
import ksmart.ks50team01.platform.trip.service.PTripPlanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value="/platform/ranking")
@RequiredArgsConstructor
@Slf4j
public class RankingController {

	private final RankingService rankingService;
	private final PTripPlanService pTripPlanService;
	
	@PostMapping("/removeRanking")
	public String remove(@RequestParam(name = "pRankingId") String pRankingId
						,Model model) {
		model.addAttribute("title", "플랫폼추천리스트 삭제");
		model.addAttribute("pRankingId", pRankingId);
		return "platform/ranking/removeRanking";
	}
	
	@GetMapping("/removeRanking")
	public String removeRanking(@RequestParam(name="pRankingId") String pRankingId
								,Model model) {
		List<Ranking> rankingList = rankingService.getRankingList();
		rankingService.removeRanking(pRankingId);
		model.addAttribute("removeRanking",pRankingId);
		model.addAttribute("rankingList", rankingList);
		return "redirect:/platform/ranking/rankingList";
	}
	/**
	 * 플랫폼추천리스트 수정
	 * @param ranking
	 * @return
	 */
	@PostMapping("/modifyRanking")
	public String modify(@ModelAttribute Ranking ranking) {
		rankingService.modifyRanking(ranking);
		return "redirect:/platform/ranking/rankingList";
	}
	/**
	 * 플랫폼추천리스트 정보 가져오기
	 * @param pRankingId
	 * @param model
	 * @return
	 */
	@GetMapping("/modifyRanking")
	public String modifyRanking(@RequestParam(name="pRankingId") String pRankingId
							  ,Model model) {
		Ranking ranking = rankingService.getRankingInfoById(pRankingId);
		List<Ranking> rankingList = rankingService.getRankingList();
		
		model.addAttribute("title", "플랫폼리스트 수정");
		model.addAttribute("ranking", ranking);
		model.addAttribute("rankingList", rankingList);
		
		return "platform/ranking/modifyRanking";
	}
	/**
	 * 플랫폼 추천 넘버 중복체크
	 * @param pRankingNum
	 * @return
	 */
	@PostMapping("/rankingListCheck")
	@ResponseBody
	public boolean rankingListCheck(@RequestParam(value="pRankingId") String pRankingId) {
		boolean isPRankingNum = rankingService.rankingListCheck(pRankingId);
		return  isPRankingNum;
	}
	@PostMapping("/rankingApiListCheck")
	@ResponseBody
	public boolean rankingApiListCheck(@RequestParam(value="pfRankInfoId") String pfRankInfoId) {
		boolean isPfRankInfoId = rankingService.rankingApiListCheck(pfRankInfoId);
		return isPfRankInfoId;
	}
	
	/**
	 * 플랫폼 추천 리스트 등록
	 * @param ranking
	 * @return
	 */
	@PostMapping("/addRanking")
	public String addRanking(Ranking ranking) {
		rankingService.addRanking(ranking);
		return "redirect:/platform/ranking/rankingList";
	}
	@GetMapping("/addRanking")
	public String addRanking(Model model) {
		List<Ranking> rankingList = rankingService.getRankingList();
		System.out.println("rankingList: " + rankingList);
		model.addAttribute("title", "플랫폼 추천리스트 등록");
		model.addAttribute("rankingList", rankingList);
		return "platform/ranking/addRanking";
	}
	
	@PostMapping("/addApiRanking")
	public String addApiRanking(RankingApi rankingApi) {
		rankingService.addApiRanking(rankingApi);
		return "redirect:/platform/ranking/rankingInfoList";
	}
	
	@GetMapping("/addApiRanking")
		public String addApiRanking(@RequestParam(value="destinationCId") String destinationCId, Model model) {
		List<Ranking> rankingList = rankingService.getRankingList();
		List<RankingApi> rankingApiList = rankingService.getRankingInfoList();
		RankingApi rankingApi = rankingService.getDestinationContentId(destinationCId);
		model.addAttribute("title", "플랫폼추천 관계리스트 등록");
		model.addAttribute("rankingApiList", rankingApiList);
		model.addAttribute("rankingList", rankingList);
		model.addAttribute("rankingApi", rankingApi);
		return "platform/ranking/addApiRanking";
	}
	
	@GetMapping("/rankingList")
	public String getRankingList(Model model) {
		
		List<Ranking> rankingList = rankingService.getRankingList();
		
		model.addAttribute("title", "플랫폼 추천 관리");
		model.addAttribute("rankingList", rankingList);
		return "platform/ranking/rankingList";
	}
	@GetMapping("/rankingApiList")
	public String rankingListApi(Model model) {
		
		List<PTourApi> destinationList = pTripPlanService.getDestinationList();
		model.addAttribute("title", "API리스트");
		model.addAttribute("destinationList", destinationList);
		return "platform/ranking/rankingApiList";
	}
	@GetMapping("/rankingInfoList")
	public String getRankingInfoList(Model model) {
		List<RankingApi> rankingApiList = rankingService.getRankingInfoList();
		model.addAttribute("title", "플랫폼추천 관계리스트");
		model.addAttribute("rankingApiList", rankingApiList);
		return "platform/ranking/rankingInfoList";
	}
	
}
