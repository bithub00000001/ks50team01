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
import ksmart.ks50team01.platform.ranking.service.RankingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value="/platform/ranking")
@RequiredArgsConstructor
@Slf4j
public class RankingController {

	private final RankingService rankingService;
	
	@PostMapping("/modifyRanking")
	public String modify(@RequestParam String pRankingId) {
		rankingService.getRankingInfoById(pRankingId);
		return "redirect:/platform/ranking/rankingList";
	}
	
	@GetMapping("/modifyRanking")
	public String modifyRanking(@RequestParam(value="pRankingId") String pRankingId
							  ,Model model) {
		log.info("수정화면 pRankingNum : {}", pRankingId);
		Ranking rankingInfo = rankingService.getRankingInfoById(pRankingId);
		List<Ranking> rankingList = rankingService.getRankingList();
		
		model.addAttribute("title", "플랫폼리스트 수정");
		model.addAttribute("ranking", rankingInfo);
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
	
	/**
	 * 플랫폼 추천 리스트 등록
	 * @param ranking
	 * @return
	 */
	@PostMapping("/addRanking")
	public String addRanking(Ranking ranking) {
		rankingService.addRanking(ranking);
		System.out.println("회원가입 화면에서 입력받은 data: " + ranking);
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
	
	@GetMapping("/rankingList")
	public String getRankingList(Model model) {
		
		List<Ranking> rankingList = rankingService.getRankingList();
		
		model.addAttribute("title", "플랫폼 추천 관리");
		model.addAttribute("rankingList", rankingList);
		return "platform/ranking/rankingList";
	}
	
}
