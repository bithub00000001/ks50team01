package ksmart.ks50team01.platform.destination.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ksmart.ks50team01.platform.destination.dto.Destination;
import ksmart.ks50team01.platform.destination.service.DestinationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping(value = "/platform")
@RequiredArgsConstructor
@Slf4j
public class DestinationManageController {
	
	private final DestinationService destinationService;
	
	@PostMapping("/destination/tourModify")
	public String tourModifyProcess(@ModelAttribute Destination destination) {
		
		return "redirect:/platform/destination/tourGoodsManage";
	}
	
	@GetMapping("/destination/tourModify")
	public String tourModify(@RequestParam("tourInfo") String tourInfo, Model model) {
		
		model.addAttribute("title", "관광지 수정");
		
		return "/platform/destination/tourModify";
	}
	
	
	@GetMapping("/destination/tourManage")
	public String tourManage(Model model) {
		List<Destination> tourInfoList = destinationService.getTourInfoList();
		
		model.addAttribute("title","관광지 관리");
		model.addAttribute("tourInfoList", tourInfoList);
		
		return "/platform/destination/tourManage";
	}
	
	@GetMapping("/destination/tourGoodsManage")
	public String tourGoodsManage(Model model) {
		List<Destination> tourGoodsList = destinationService.getTourGoodsList();
		
		model.addAttribute("tourGoodsList", tourGoodsList);
		model.addAttribute("title", "관광상품 관리");
		
		return "/platform/destination/tourGoodsManage";
	}
	
	
	@GetMapping("/destination/lodgingManage")
	public String lodgingManage(Model model) {
		List<Destination> lodgingInfoList = destinationService.getLodgingInfoList();
		
		model.addAttribute("lodgingInfoList", lodgingInfoList);
		model.addAttribute("title", "숙소 관리");
		
		return "/platform/destination/lodgingManage";
	
	}
	
	@GetMapping("/destination/lodgingGoodsManage")
	public String lodgingGoodsManage(Model model) {
		List<Destination> lodgingGoodsList = destinationService.getLodgingGoodsList();
		
		model.addAttribute("lodgingGoodsList", lodgingGoodsList);
		model.addAttribute("title", "숙소옵션 관리");
		
		return "/platform/destination/lodgingGoodsManage";
	}
	

	
	@GetMapping("/destination/restaurantManage")
	public String restaurantManage(Model model) {
		List<Destination> restaurantInfoList = destinationService.getRestaurantInfoList();
		
		model.addAttribute("restaurantInfoList", restaurantInfoList);
		model.addAttribute("title", "식당 관리");
		
		return "/platform/destination/restaurantManage";
	}
	
	@GetMapping("/destination/restaurantMenuManage")
	public String restaurantMenuManage(Model model) {
		List<Destination> restaurantMenuList = destinationService.getRestaurantMenuList();
		log.info("DestinationManageController restaurantMenuList:{}", restaurantMenuList);
		
		model.addAttribute("restaurantMenuList", restaurantMenuList);
		model.addAttribute("title", "식당메뉴 관리");
		
		return "/platform/destination/restaurantMenuManage";
	}
	
	
	@GetMapping("/destination/destinationRegister")
	public String destinationRegister(Model model) {
		
		model.addAttribute("title", "여행지 일괄등록");
		
		return "/platform/destination/destinationRegister";
	}
	
	@GetMapping("/destination/categoryManage")
	public String categoryManage(Model model) {
		
		model.addAttribute("title", "카테고리 관리");
		
		return "/platform/destination/categoryManage";
		
	}

}
