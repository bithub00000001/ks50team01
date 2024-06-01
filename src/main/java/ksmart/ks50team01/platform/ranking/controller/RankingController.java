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

@Controller
@RequestMapping(value="/platform/ranking")
@RequiredArgsConstructor
public class RankingController {
	
	/**
	 * 의존성 주입
	 */
	private final RankingService rankingService;
	private final PTripPlanService pTripPlanService;
	
	/**
	 * 관계리스트 삭제
	 * @param pfRankInfoId
	 * @param model
	 * @return
	 */
	@PostMapping("/removeRankingInfo")
	public String removeInfo(@RequestParam(name = "pfRankInfoId") String pfRankInfoId
						,Model model) {
		model.addAttribute("title", "플랫폼추천 관계리스트 삭제");
		model.addAttribute("pfRankInfoId", pfRankInfoId);
		return "platform/ranking/removeRankingInfo";
	}
	/**
	 * 관계리스트 삭제 정보
	 * @param pfRankInfoId
	 * @param model
	 * @return
	 */
	@GetMapping("/removeRankingInfo")
	public String removeRankingInfo(@RequestParam(value="pfRankInfoId") String pfRankInfoId, Model model) {
		
		List<RankingApi> rankingApiList = rankingService.getRankingInfoList();
		rankingService.removeRankingInfo(pfRankInfoId);
		model.addAttribute("removeRankingInfo",pfRankInfoId);
		model.addAttribute("rankingApiList", rankingApiList);
		
		return "redirect:/platform/ranking/rankingInfoList";
	}
	/**
	 * 플랫폼추천리스트 삭제
	 * @param pRankingId
	 * @param model
	 * @return
	 */
	@PostMapping("/removeRanking")
	public String remove(@RequestParam(name = "pRankingId") String pRankingId
						,Model model) {
		model.addAttribute("title", "플랫폼추천리스트 삭제");
		model.addAttribute("pRankingId", pRankingId);
		return "platform/ranking/removeRanking";
	}
	/**
	 * 플랫폼추천리스트 삭제를 위한 정보
	 * @param pRankingId
	 * @param model
	 * @return
	 */
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
	 * 플랫폼추천리스트 수정 정보 가져오기
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
	 * 관계리스트 수정
	 * @param rankingApi
	 * @return
	 */
	@PostMapping("/modifyApiRanking")
	public String modifyRankingInfo(@ModelAttribute RankingApi rankingApi) {
		rankingService.modifyRankingInfo(rankingApi);
		return "redirect:/platform/ranking/rankingInfoList";
	}
	/**
	 * 관계리스트 수정 정보 가져오기
	 * @param pfRankInfoId
	 * @param model
	 * @return
	 */
	@GetMapping("/modifyApiRanking")
	public String getModifyRankingInfo(@RequestParam(name="pfRankInfoId") String pfRankInfoId, Model model) {
		List<Ranking> rankingList = rankingService.getRankingList();
		List<RankingApi> rankingApiList = rankingService.getRankingInfoList();
		RankingApi rankingApi = rankingService.getModifyRankingInfo(pfRankInfoId);
		model.addAttribute("rankingList", rankingList);
		model.addAttribute("rankingApiList", rankingApiList);
		model.addAttribute("rankingApi", rankingApi);
		model.addAttribute("title","관계리스트 수정");
		return "platform/ranking/modifyApiRanking";
	}
	/**
	 * 플랫폼추천 중복체크
	 * @param artclRankigNum
	 * @return
	 */
	@PostMapping("/rankingListCheck")
	@ResponseBody
	public boolean rankingListCheck(@RequestParam(value="artclRankigNum") int artclRankigNum) {
		boolean isArtclRankigNum = rankingService.rankingListCheck(artclRankigNum);
		return  isArtclRankigNum;
	}	
	/**
	 * 플랫폼추천 리스트 등록
	 * @param ranking
	 * @return
	 */
	@PostMapping("/addRanking")
	public String addRanking(Ranking ranking) {
		rankingService.addRanking(ranking);
		return "redirect:/platform/ranking/rankingList";
	}
	/**
	 * 플랫폼추천 리스트 등록을 위한 정보
	 * @param model
	 * @return
	 */
	@GetMapping("/addRanking")
	public String addRanking(Model model) {
		List<Ranking> rankingList = rankingService.getRankingList();
		System.out.println("rankingList: " + rankingList);
		model.addAttribute("title", "플랫폼 추천리스트 등록");
		model.addAttribute("rankingList", rankingList);
		return "platform/ranking/addRanking";
	}
	/**
	 * 플랫폼추천 관계리스트 등록
	 * @param rankingApi
	 * @return
	 */
	@PostMapping("/addApiRanking")
	public String addApiRanking(RankingApi rankingApi) {
		rankingService.addApiRanking(rankingApi);
		return "redirect:/platform/ranking/rankingInfoList";
	}
	/**
	 * 플랫폼추천 관계리스트 등록을 위한 정보
	 * @param contentId
	 * @param model
	 * @param destinationTitle
	 * @return
	 */
	@GetMapping("/addApiRanking")
		public String addApiRanking(@RequestParam(value="contentId") String contentId, Model model, String destinationTitle) {
		List<Ranking> rankingList = rankingService.getRankingList();
		List<RankingApi> rankingApiList = rankingService.getRankingInfoList();
		RankingApi rankingApi = rankingService.getDestinationContentId(contentId, destinationTitle);
		model.addAttribute("title", "플랫폼추천 관계리스트 등록");
		model.addAttribute("rankingApiList", rankingApiList);
		model.addAttribute("rankingList", rankingList);
		model.addAttribute("rankingApi", rankingApi);
		return "platform/ranking/addApiRanking";
	}
	/**
	 * 플랫폼추천관리 조회
	 * @param model
	 * @return
	 */
	@GetMapping("/rankingList")
	public String getRankingList(Model model) {
		
		List<Ranking> rankingList = rankingService.getRankingList();
		
		model.addAttribute("title", "플랫폼 추천 관리");
		model.addAttribute("rankingList", rankingList);
		return "platform/ranking/rankingList";
	}
	/**
	 * api리스트 조회
	 * @param model
	 * @return
	 */
	@GetMapping("/rankingApiList")
	public String rankingListApi(Model model) {
		
		List<PTourApi> destinationList = pTripPlanService.getDestinationList();
		model.addAttribute("title", "API리스트");
		model.addAttribute("destinationList", destinationList);
		return "platform/ranking/rankingApiList";
	}
	/**
	 * 관계리스트 조회
	 * @param model
	 * @return
	 */
	@GetMapping("/rankingInfoList")
	public String getRankingInfoList(Model model) {
		List<PTourApi> pTourApiList = pTripPlanService.getDestinationList();
		List<RankingApi> rankingApiList = rankingService.getRankingInfoList();
		model.addAttribute("title", "플랫폼추천 관계리스트");
		model.addAttribute("rankingApiList", rankingApiList);
		model.addAttribute("pTourApiList", pTourApiList);
		return "platform/ranking/rankingInfoList";
	}
	
}
