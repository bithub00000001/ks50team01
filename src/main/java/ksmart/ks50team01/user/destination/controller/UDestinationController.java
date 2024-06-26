package ksmart.ks50team01.user.destination.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ksmart.ks50team01.platform.trip.dto.PTourApi;
import ksmart.ks50team01.platform.trip.dto.PTourDetail;
import ksmart.ks50team01.platform.trip.service.PTripPlanService;
import ksmart.ks50team01.user.destination.service.UDestinationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/user")
@RequiredArgsConstructor
@Slf4j
public class UDestinationController {
	
	private final PTripPlanService pTripPlanService;
	private final UDestinationService uDestinationService;
	
	
	@GetMapping("/destination/lodgingCheck")
	public String lodgingCheck(Model model) {
		
		model.addAttribute("title", "숙소 조회");
		
		return "/user/destination/lodgingCheck";
	}
	
	@GetMapping("/destination/lodgingCheckDetails")
	public String lodgingCheckDetails(Model model) {
		
		model.addAttribute("title", "숙소 세부사항");
		
		return "/user/destination/lodgingCheckDetails";
	}
	
	@GetMapping("/destination/restaurantCheck")
	public String restaurantCheck(Model model) {
		
		model.addAttribute("title", "음식점 조회");
		
		return "/user/destination/restaurantCheck";
	}
	
	@GetMapping("/destination/restaurantCheckDetails")
	public String restaurantCheckDetails(Model model) {
		
		model.addAttribute("title", "음식점 세부사항");
		
		return "/user/destination/restaurantCheckDetails";
	}
	/**
	 * 관광지 조회
	 * @param model
	 * @return
	 */
	@GetMapping("/destination/tourCheck")
	public String tourCheck(@RequestParam(value="currentPage", required = false, defaultValue = "1") int currentPage,
			Model model) {
		List<?> areaCodeList = pTripPlanService.getAreaCodeList();
		
		Map<String, Object> resultMap = uDestinationService.getTourList(currentPage);
		
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> tourList = (List<Map<String, Object>>) resultMap.get("tourList");
		int lastPage = (int) resultMap.get("lastPage");
		int startPageNum = (int) resultMap.get("startPageNum");
		int lastPageNum = (int) resultMap.get("lastPageNum");
		
		
		
		model.addAttribute("title", "관광지 조회");
		model.addAttribute("areaCodeList", areaCodeList);
		
		model.addAttribute("tourList", tourList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("lastPageNum", lastPageNum);
		
		return "/user/destination/tourCheck";
	}
	
	@GetMapping("/destination/tourCheckDetails")
	public String tourCheckDetails(@RequestParam(name = "contentId")String contentId,Model model) {
		log.info("contentId: {}", contentId);
		PTourDetail tourDetail = uDestinationService.getDestinationDetail(contentId);
		
		
		
		model.addAttribute("title", "관광지 세부사항");
		model.addAttribute("tourDetail", tourDetail);
		
		
		return "/user/destination/tourCheckDetails";
	}
	

	
	@GetMapping("/sigungu-codes")
	@ResponseBody
	public List<PTourApi> tourCheckApi(@RequestParam(name = "areaCode") String areaCode) {
		return pTripPlanService.getSigunguCodesByAreaCode(areaCode);
	}

	
	
	/*
	 * @GetMapping("/destination/regionalCheck") public String regionalCheck(Model
	 * model) {
	 * 
	 * model.addAttribute("title", "지역 조회");
	 * 
	 * return "/user/destination/regionalCheck"; }
	 * 
	 * @GetMapping("/destination/regionalCheckDetails") public String
	 * regionalCheckDetails(Model model) {
	 * 
	 * model.addAttribute("title", "지역 세부 조회");
	 * 
	 * return "/user/destination/regionalCheckDetails"; }
	 */

}
