package ksmart.ks50team01.platform.statistic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/platform/statistic")
@RequiredArgsConstructor
public class PStatisticController {
	
	/**
	 * 회원 가입 및 탈퇴 조회 
	 * @param model
	 * @return
	 */
	@GetMapping("/member")
	public String meberTotal(Model model) {

		
		model.addAttribute("title", "회원 가입 및 탈퇴 조회");
		
		return "platform/statistic/member";
	}
	
	
	/**
	 * 지역별 관광지 조회
	 * @param param
	 * @return
	 */
	@GetMapping("/map")
	public String mapTotal(Model model) {
		
		model.addAttribute("title", "지역별 관광지 조회");
		
		return "platform/statistic/map";
	}
	
	/**
	 * 로그인 정보 조회 .. 무엇을 조회할까
	 * @param param
	 * @return
	 */
	@GetMapping("/login")
	public String loginTotal(Model model) {
		
		model.addAttribute("title", "로그인 통계");
		
		return "platform/statistic/login";
	}
	
	/**
	 * 플랫폼 운영 분석,, 무엇을 넣을지 고민중
	 * @param param
	 * @return
	 */
	@GetMapping("/manage")
	public String manageTotal(Model model) {
		
		model.addAttribute("title", "플랫폼 운영 분석");
		
		return "platform/statistic/manage";
	}
	
	/**
	 * 검색어 통계
	 * @param param
	 * @return
	 */
	@GetMapping("/search")
	public String searchTotal(Model model) {
		
		model.addAttribute("title", "검색어 통계");
		
		return "platform/statistic/search";
	}
}