package ksmart.ks50team01.platform.destination.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ksmart.ks50team01.platform.destination.dto.Destination;
import ksmart.ks50team01.platform.destination.service.DestinationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping(value = "/platform")
@RequiredArgsConstructor
@Slf4j
public class DestinationManageController {
	
	private final DestinationService destinationService;
	/**
	 * 관관지 이름 검색으로 찾기
	 * @param tourName
	 * @return
	 */
	@GetMapping("/destination/search-tour-name")
	@ResponseBody
	public List<Destination> getTourListByName(@RequestParam(value="tourName") String tourName){
		List<Destination> tourList = destinationService.getTourInfoListByName(tourName);
		
		return tourList;
	}
	@GetMapping("/destination/search-lodging-name")
	@ResponseBody
	public List<Destination> getLodgingListByName(@RequestParam(value = "lodgingName") String lodgingName){
		List<Destination> lodgingList = destinationService.getLodgingInfoListByName(lodgingName);
		
		return lodgingList;
	}
	
	/**
	 * 관광지 상세정보 삭제
	 * @param tourGoodsOptionCd
	 * @return
	 */
	@PostMapping("removeTourGoods")
	public String removeTourGoods(@RequestParam(value = "tourGoodsOptionCd") String tourGoodsOptionCd) {
		
		destinationService.removeTourGoods(tourGoodsOptionCd);
		
		return "redirect:/platform/destination/tourGoodsManage";
	}
	@GetMapping("/removeTourGoods")
	public String removeTourGoodsProcess(@RequestParam(value = "tourGoodsOptionCd") String tourGoodsOptionCd, Model model) {
		List<Destination> tourGoodsList =  destinationService.getTourGoodsList();
		destinationService.removeTourGoods(tourGoodsOptionCd);
		
		model.addAttribute("tourGoodsList", tourGoodsList);
		model.addAttribute("tourGoodsOptionCd", tourGoodsOptionCd);
		model.addAttribute("title", "관광지 상세정보 삭제");
		
		return "redirect:/platform/destination/tourGoodsManage";
	}
	
	/**
	 * 관광지 목록 삭제
	 * @param tourInfoCode
	 * @return
	 */
	@PostMapping("/removeTour")
	public String removeTour(@RequestParam(value = "tourInfoCode") String tourInfoCode) {
		destinationService.removeTour(tourInfoCode);
		
		//model.addAttribute("title", "관광지 목록 삭제");
		//model.addAttribute("tourInfoCode", tourInfoCode);
		
		return "redirect:/platform/destination/tourManage";
	}
	@GetMapping("/removeTour")
	public String removeTourProcess(@RequestParam(value = "tourInfoCode") String tourInfoCode, Model model) {
		List<Destination> tourList = destinationService.getTourInfoList();
		destinationService.removeTour(tourInfoCode);
		
		model.addAttribute("tourList", tourList);
		model.addAttribute("tourInfoCode", tourInfoCode);
		model.addAttribute("title", "관광지 목록 삭제");
		
		return "redirect:/platform/destination/tourManage";
	}
	
	/**
	 * 관광지 상세정보 중복체크
	 * @param tourGoodsOptionCd
	 * @return
	 */
	@GetMapping("/destination/addTourGoodsCheckList")
	@ResponseBody
	public boolean addTourGoodsCheckList(@RequestParam(value = "tourGoodsOptionCd") String tourGoodsOptionCd) {
		boolean isTourGoodsOptionCd = destinationService.addTourGoodsCheckList(tourGoodsOptionCd);
		return isTourGoodsOptionCd;
	}
	
	/**
	 * 관광지 중복체크
	 * @param tourName
	 * @return
	 */
	@GetMapping("/destination/addTourCheckList")
	@ResponseBody
	public boolean addTourCheckList(@RequestParam(value = "tourName") String tourName) {
		log.info("tourName: {}",tourName);
		boolean isTourName = destinationService.addTourCheckList(tourName);
		return isTourName;
	}
	
	/**
	 * 관광지 등록
	 * @param destination
	 * @return
	 */
	@PostMapping("/destination/addTour")
	public String addTour(Destination destination) {
		destinationService.addTour(destination);
		return "redirect:/platform/destination/tourManage";
	}
	@GetMapping("/destination/addTour")
	public String addTour(Model model) {
		List<Destination> addTourList = destinationService.getTourInfoList();
		
		model.addAttribute("addTourList", addTourList);
		model.addAttribute("title", "관광지 등록");
		return "platform/destination/addTour";
	}

	/**
	 * 관광지 상품 등록
	 * @param destination
	 * @return
	 */
	@PostMapping("/destination/addTourGoods")
	public String addTourGoods(Destination destination) {
		
		log.info("input destination: {}", destination);
		destinationService.addTourGoods(destination);

		return "redirect:/platform/destination/tourGoodsManage";
	}
	
	@GetMapping("/destination/addTourGoods")
	public String addTourGoods(Model model) {
		List<Destination> addTourGoods = destinationService.getTourGoodsList();
		
		model.addAttribute("addTourGoods", addTourGoods);
		model.addAttribute("title", "관광지 세부항목 등록");
		
		return "platform/destination/addTourGoods";
	}
	
	/**
	 * 관광지 상세 수정
	 * @param destination
	 * @return
	 */
	@PostMapping("/destination/tourGoodsModify")
	public String tourGoodsModifyProcess(Destination destination) {
		destinationService.tourGoodsModify(destination);
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
		return "platform/destination/tourGoodsModify";
	}
	
	/**
	 * 관광지 수정
	 * @param destination
	 * @return
	 */
	@PostMapping("/destination/tourModify")
	public String tourModifyProcess(Destination destination) {
		
		log.info("controller destination:{}", destination);
		destinationService.tourModify(destination);		
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
	
	/**
	 * 관광지 목록 관리
	 * @param model
	 * @return
	 */
	@GetMapping("/destination/tourManage")
	public String tourManage(Model model) {
		List<Destination> tourInfoList = destinationService.getTourInfoList();
		
		model.addAttribute("title","관광지 관리");
		model.addAttribute("tourInfoList", tourInfoList);
		
		return "/platform/destination/tourManage";
	}
	/**
	 * 관광지 상세목록 관리
	 * @param model
	 * @return
	 */
	@GetMapping("/destination/tourGoodsManage")
	public String tourGoodsManage(Model model) {
		List<Destination> tourGoodsList = destinationService.getTourGoodsList();
		
		model.addAttribute("tourGoodsList", tourGoodsList);
		model.addAttribute("title", "관광상품 관리");
		
		return "/platform/destination/tourGoodsManage";
	}
	
	/**
	 * 숙소 등록
	 * @param destination
	 * @return
	 */
	@PostMapping("/destination/addLodging")
	public String addLodging(Destination destination) {
		destinationService.addLodging(destination);
		return "redirect:/platform/destination/lodgingManage";
	}
	@GetMapping("/destination/addLodging")
	public String addLodging(Model model) {
		List<Destination> addLodgingList = destinationService.getLodgingInfoList();
		
		model.addAttribute("addLodgingList", addLodgingList);
		model.addAttribute("title", "숙소 등록");
		
		return "platform/destination/addLodging";
	}
	/**
	 * 숙소 상세정보 등록 240606: 메서드 사용 안함 처리
	 * @param model
	 * @return
	 */
	@PostMapping("/destination/addLodgingGoods")
	public String addLodgingGoods(Destination destination) {
		destinationService.addLodgingGoods(destination);
		return "redirect:/platform/destination/lodgingGoodsManage";
	}
	@GetMapping("/destination/addLodgingGoods")
	public String addLodgingGoods(Model model) {
		List<Destination> addLodgingGoodsList = destinationService.getLodgingGoodsList();
		
		model.addAttribute("addLodgingGoodsList", addLodgingGoodsList);
		model.addAttribute("title", "숙소 상제정보 등록");
		
		return "platform/destination/addLodgingGoods";
	}
	/**
	 * 음식점 중복체크 240606: 메서드 사용 안함 처리
	 * @param restaurantName
	 * @return
	 */
	/*@GetMapping("destination/addRestaurantCheckList")
	@ResponseBody
	public boolean addRestaurantCheckList(@RequestParam(value = "restaurantName") String restaurantName) {
		boolean isRestaurantName = destinationService.addRestaurantCheckList(restaurantName);
		return isRestaurantName;
	}*/
	
	/**
	 * 숙소이름 중복체크
	 * @param lodgingName
	 * @return
	 */
	@GetMapping("/destination/addLodgingCheckList")
	@ResponseBody
	public boolean addLodgingCheckList(@RequestParam(value = "lodgingName") String lodgingName) {
		boolean isLodgingName = destinationService.addLodgingCheckList(lodgingName);
		return isLodgingName;
	}
	
	
	/**
	 * 숙소 상세정보 중복체크
	 * @param lodgingMenuCode
	 * @return
	 */
	@GetMapping("/destination/addLodgingGoodsCheckList")
	@ResponseBody
	public boolean addLodgingGoodsCheckList(@RequestParam(value = "lodgingMenuCode") String lodgingMenuCode) {
		boolean isLodgingMenuCode = destinationService.addLodgingGoodsCheckList(lodgingMenuCode);
		return isLodgingMenuCode;
	}
	
	/**
	 * 숙소 목록 삭제 240606: 인수 변경
	 * @param lodgingInfoCode
	 * @return
	 */
	@PostMapping("/removeLodging")
	public String removeLodging(@RequestParam(value = "lodgingInfoCode") String lodgingInfoCode) {

		return "redirect:/platform/destination/lodgingManage";
	}
	@GetMapping("/removeLodging")
	public String removeLodgingProcess(@RequestParam(value = "lodgingInfoCode") String lodgingInfoCode, Model model) {
		List<Destination> lodgingList = destinationService.getLodgingInfoList();
		destinationService.removeLodging(lodgingInfoCode);
		
		model.addAttribute("lodgingInfoCode", lodgingInfoCode);
		model.addAttribute("lodgingList", lodgingList);
		model.addAttribute("title", "숙소 삭제");
		
		return "redirect:/platform/destination/lodgingManage";
	}
	/**
	 * 숙소 상제정보 삭제
	 * @param lodgingMenuCode
	 * @return
	 */
	@PostMapping("/removeLodgingGoods")
	public String removeLodgingGoods(@RequestParam(value = "lodgingMenuCode") String lodgingMenuCode) {

		return "redirect:/platform/destination/lodgingGoodsManage";
	}
	@GetMapping("/removeLodgingGoods")
	public String removeLodgingGoodsProcess(@RequestParam(value = "lodgingMenuCode") String lodgingMenuCode, Model model) {
		List<Destination> lodgingGoodsList =  destinationService.getLodgingGoodsList();
		destinationService.removeLodgingGoods(lodgingMenuCode);
		
		model.addAttribute("lodgingMenuCode", lodgingMenuCode);
		model.addAttribute("lodgingGoodsList", lodgingGoodsList);
		model.addAttribute("title", "숙소상제정보 삭제");
		
		return "redirect:/platform/destination/lodgingGoodsManage";
	}
	
	
	/**
	 * 숙소 수정
	 * @param destination
	 * @return
	 */
	@PostMapping("/destination/lodgingModify")
	public String lodgingModifyProcess(Destination destination) {
		
		destinationService.lodgingModify(destination);
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
	/**
	 * 숙소상품 수정
	 * @param destination
	 * @return
	 */
	@PostMapping("/destination/lodgingGoodsModify")
	public String lodgingGoodsModifyProcess(Destination destination) {
		
		destinationService.lodgingGoodsModify(destination);
		return "redirect:/platform/destination/lodgingGoodsManage";
	}
	
	@GetMapping("/destination/lodgingGoodsModify")
	public String lodgingGoodsModify(@RequestParam(value = "lodgingMenuCode") String lodgingMenuCode, Model model) {
		Destination lodgingGoodsInfo = destinationService.getLodgingGoodsInfoById(lodgingMenuCode);
		List<Destination> lodgingGoodsList = destinationService.getLodgingGoodsList();
		
		model.addAttribute("lodgingGoodsInfo", lodgingGoodsInfo);
		model.addAttribute("lodgingGoodsList", lodgingGoodsList);
		model.addAttribute("title", "숙소 상세정보 수정");
	
		return "/platform/destination/lodgingGoodsModify";
	}
	/**
	 * 숙소 관리
	 * @param model
	 * @return
	 */
	@GetMapping("/destination/lodgingManage")
	public String lodgingManage(Model model) {
		List<Destination> lodgingInfoList = destinationService.getLodgingInfoList();
		
		model.addAttribute("lodgingInfoList", lodgingInfoList);
		model.addAttribute("title", "숙소 관리");
		
		return "/platform/destination/lodgingManage";
	
	}
	/**
	 * 숙소상세메뉴 관리
	 * @param model
	 * @return
	 */
	@GetMapping("/destination/lodgingGoodsManage")
	public String lodgingGoodsManage(Model model) {
		List<Destination> lodgingGoodsList = destinationService.getLodgingGoodsList();
		
		model.addAttribute("lodgingGoodsList", lodgingGoodsList);
		model.addAttribute("title", "숙소옵션 관리");
		
		return "/platform/destination/lodgingGoodsManage";
	}
	/**
	 * 음식점 수정 240606: 메서드 인수 변경 처리
	 * @param destination
	 * @return
	 */
	@PostMapping("/destination/restaurantModify")
	public String restaurantModifyProcess(Destination destination) {
		
		destinationService.restaurantMoidfy(destination);
		
		return "redirect:/platform/destination/restaurantManage";
	}

	@GetMapping("/destination/restaurantModify")
	public String restaurantModify(@RequestParam(value = "restaurantInfoCode") String restaurantInfoCode, Model model) {
		Destination restaurantInfo = destinationService.getRestaurantInfoById(restaurantInfoCode);
		List<Destination> restaurantList = destinationService.getRestaurantInfoList();
		
		model.addAttribute("restaurantInfo", restaurantInfo);
		model.addAttribute("restaurantList", restaurantList);
		model.addAttribute("title", "음식점 수정");
		
		return "/platform/destination/restaurantModify";
	}
	/**
	 * 음식점 상세정보 수정
	 * @param destination
	 * @return
	 */
	@PostMapping("/destination/restaurantMenuModify")
	public String restaurantMenuModify(Destination destination) {
		
		destinationService.restaurantMenuModify(destination);
		
		return "redirect:/platform/destination/restaurantMenuManage";
	}
	@GetMapping("/destination/restaurantMenuModify")
	public String restaurantMenuModify(@RequestParam(value = "restaurantMenuManageCode") String restaurantMenuManageCode, Model model) {
		Destination restaurantMenuInfo = destinationService.getRestaurantMenuInfoById(restaurantMenuManageCode);
		List<Destination> retaurantMenuList = destinationService.getRestaurantMenuList();
		
		model.addAttribute("retaurantMenuList", retaurantMenuList);
		model.addAttribute("restaurantMenuInfo", restaurantMenuInfo);
		model.addAttribute("title", "음식점 상세정보 수정");
		
		return "/platform/destination/restaurantMenuModify";
	}
	
	
	
	/**
	 * 음식점 관리
	 * @param model
	 * @return
	 */
	@GetMapping("/destination/restaurantManage")
	public String restaurantManage(Model model) {
		List<Destination> restaurantInfoList = destinationService.getRestaurantInfoList();
		
		model.addAttribute("restaurantInfoList", restaurantInfoList);
		model.addAttribute("title", "음식점 관리");
		
		return "/platform/destination/restaurantManage";
	}
	/**
	 * 음식점 상세정보 관리
	 * @param model
	 * @return
	 */
	@GetMapping("/destination/restaurantMenuManage")
	public String restaurantMenuManage(Model model) {
		List<Destination> restaurantMenuList = destinationService.getRestaurantMenuList();
		log.info("DestinationManageController restaurantMenuList:{}", restaurantMenuList);
		
		model.addAttribute("restaurantMenuList", restaurantMenuList);
		model.addAttribute("title", "음식점메뉴 관리");
		
		return "/platform/destination/restaurantMenuManage";
	}
	
	/*
	 * @GetMapping("/destination/destinationRegister") public String
	 * destinationRegister(Model model) {
	 * 
	 * model.addAttribute("title", "여행지 등록");
	 * 
	 * return "/platform/destination/destinationRegister"; }
	 * 
	 * @GetMapping("/destination/categoryManage") public String categoryManage(Model
	 * model) {
	 * 
	 * model.addAttribute("title", "카테고리 관리");
	 * 
	 * return "/platform/destination/categoryManage";
	 * 
	 * }
	 */
	

	/**
	 * 음식정 목록 삭제
	 * @param restaurantInfoCode
	 * @return
	 */
	@PostMapping("/removeRestaurant")
	public String removeRestaurant(@RequestParam(value = "restaurantInfoCode") String restaurantInfoCode) {
		destinationService.removeRestaurant(restaurantInfoCode);
		
		//model.addAttribute("title", "음식점 목록 삭제");
		//model.addAttribute("tourInfoCode", tourInfoCode);
		
		return "redirect:/platform/destination/restaurantManage";
	}
	
	@GetMapping("/removeRestaurant")
	public String removeRestaurant(@RequestParam(value = "restaurantInfoCode") String restaurantInfoCode, Model model) {
		List<Destination> restaurantList = destinationService.getRestaurantInfoList();
		destinationService.removeRestaurant(restaurantInfoCode);
		
		model.addAttribute("restaurantList", restaurantList);
		model.addAttribute("restaurantInfoCode", restaurantInfoCode);
		model.addAttribute("title", "음식점 목록 삭제");
		
		return "redirect:/platform/destination/restaurantManage";
	}
	
	
	@PostMapping("/removeRestaurantMenu")
	public String removeRestaurantMenu(@RequestParam(value = "restaurantMenuManageCode") String tourGoodsOptionCd) {
		return "redirect:/platform/destination/restaurantMenuManage";
	}
	@GetMapping("/removeRestaurantMenu")
	public String removeRestaurantMenuProcess(@RequestParam(value = "restaurantMenuManageCode") String restaurantMenuManageCode, Model model) {
		List<Destination> restaurantMenuList = destinationService.getRestaurantMenuList();
		destinationService.removeRestaurantMenu(restaurantMenuManageCode);
		
		model.addAttribute("restaurantMenuList", restaurantMenuList);
		model.addAttribute("restaurantMenuManageCode", restaurantMenuManageCode);
		model.addAttribute("title", "음식점 상세정보 삭제");
		
		return "redirect:/platform/destination/restaurantMenuManage";
		
	}

}
