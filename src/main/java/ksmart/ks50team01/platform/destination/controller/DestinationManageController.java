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
	

	@PostMapping("/destination/tourGoodsModify")
	public String tourGoodsModifyProcess(Destination destination) {
		destinationService.updateTourGoods(destination);
		return "redirect:/platform/destination/tourGoodsManage";
	}
	@GetMapping("/destination/tourGoodsModify")
	public String tourGoodsModify(@RequestParam(value = "tourGoodsOptionCd") String tourGoodsOptionCd, Model model) {
		Destination tourGoodsInfo = destinationService.getTourGoodsInfoById(tourGoodsOptionCd);
		List<Destination> tourGoodsList = destinationService.getTourGoodsList();
		log.info("tourGoodsInfo :{}",tourGoodsInfo);
		model.addAttribute("tourGoodsInfo", tourGoodsInfo);
		model.addAttribute("tourGoodsList", tourGoodsList);
		model.addAttribute("title", "관광지 세부사항 수정");
		return "/platform/destination/tourGoodsModify";
	}
	
	
	@PostMapping("/destination/tourModify")
	public String tourModifyProcess(Destination destination) {
		
		log.info("controller destination:{}", destination);
		destinationService.updateTour(destination);		
		return "redirect:/platform/destination/tourManage";
	}
	
	@GetMapping("/destination/tourModify")
	public String tourModify(@RequestParam(value = "tourInfoCode") String tourInfoCode, Model model) {
		Destination tourInfo = destinationService.getTourInfoByName(tourInfoCode);
		List<Destination> tourList = destinationService.getTourInfoList();
		
		model.addAttribute("tourList", tourList);
		model.addAttribute("tourInfo", tourInfo);
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
	

	@PostMapping("/destination/lodgingModify")
	public String lodgingModifyProcess(Destination destination) {
		
		destinationService.updateLodging(destination);
		return "redirect:/platform/destination/lodgingManage";
	}
	
	@GetMapping("/destination/lodgingModify")
	public String lodgingModify(@RequestParam(value = "lodgingInfoCode") String lodgingInfoCode, Model model) {
		Destination lodgingInfo = destinationService.getLodgingInfoById(lodgingInfoCode);
		List<Destination> lodgingList = destinationService.getLodgingInfoList();
		
		model.addAttribute("lodgingList", lodgingList);
		model.addAttribute("lodgingInfo", lodgingInfo);
		model.addAttribute("title", "숙소 수정");
		
		return "/platform/destination/lodgingModify";
	}
	
	@PostMapping("/destination/lodgingGoodsModify")
	public String lodgingGoodsModifyProcess(Destination destination) {
		
		destinationService.updateLodgingGoods(destination);
		return "redirect:/platform/destination/lodgingGoodsManage";
	}
	
	@GetMapping("/destination/lodgingGoodsModify")
	public String lodgingGoodsModify(@RequestParam(value = "lodgingMenuCode") String lodgingMenuCode, Model model) {
		Destination lodgingGoodsInfo = destinationService.getLodgingGoodsInfoById(lodgingMenuCode);
		List<Destination> lodgingGoodsList = destinationService.getLodgingGoodsList();
		
		model.addAttribute("lodgingGoodsInfo", lodgingGoodsInfo);
		model.addAttribute("lodgingGoodsList", lodgingGoodsList);
		model.addAttribute("title", "관광지 세부사항 수정");
	
		return "/platform/destination/lodgingGoodsModify";
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
	@PostMapping("/destination/restaurantModify")
	public String restaurantModifyProcess(Destination destination) {
		
		destinationService.updateRestaurant(destination);
		
		return "redirect:/platform/destination/restaurantManage";
	}
	@GetMapping("/destination/restaurantModify")
	public String restaurantModify(@RequestParam(value = "restaurantInfoCode") String restaurantInfoCode, Model model) {
		Destination restaurantInfo = destinationService.getRestaurantInfoById(restaurantInfoCode);
		List<Destination> restaurantList = destinationService.getRestaurantInfoList();
		
		model.addAttribute("restaurantInfo", restaurantInfo);
		model.addAttribute("restaurantList", restaurantList);
		model.addAttribute("title", "식당 수정");
		
		return "/platform/destination/restaurantModify";
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
