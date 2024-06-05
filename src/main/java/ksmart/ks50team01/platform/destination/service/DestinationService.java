package ksmart.ks50team01.platform.destination.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.platform.destination.dto.Destination;
import ksmart.ks50team01.platform.destination.mapper.DestinationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DestinationService {
	
	private final DestinationMapper destinationMapper;
	
	/**
	 * 관광지 목록조회 (관광지 이름 검색) 
	 * @param tourName
	 * @return
	 */
	public List<Destination> getTourInfoListByName(String tourName){
		return destinationMapper.getTourInfoListByName(tourName);
	}
	
	/**
	 * 관광지 세부항목 추가
	 * @param tourGoodsOptionCd
	 * @return
	 */
	public boolean addTourGoodsCheckList(String tourGoodsOptionCd) {
		return destinationMapper.addTourGoodsCheckList(tourGoodsOptionCd);
	}
	
	/**
	 * 관광지 중복체크
	 * @param tourName
	 * @return
	 */
	public boolean addTourCheckList(String tourName) {
		return destinationMapper.addTourCheckList(tourName);
	}
	
	/**
	 * 관광지 세부항목 제거
	 * @param tourGoodsOptionCd
	 */
	public void removeTourGoods(String tourGoodsOptionCd) {
		destinationMapper.removeTourGoods(tourGoodsOptionCd);
		
	}
	/**
	 * 관광지 삭제
	 * @param tourInfoCode
	 */
	public void removeTour(String tourInfoCode) {
		destinationMapper.removeTour(tourInfoCode);
		
	}
	/**
	 * 관광지 등록
	 * @param destination
	 * @return
	 */
	public int addTour(Destination destination) {
		return destinationMapper.addTour(destination);
	}
	
	
	/**
	 *  관광지 세부사항 등록
	 * @param destination
	 * @return
	 */
	public int addTourGoods(Destination destination) {
		destinationMapper.addTourGoods(destination);
		return destinationMapper.addTourGoodsOp(destination);
	}

	/**
	 * 관광지 수정
	 * @param destination
	 * @return
	 */
	public int tourModify(Destination destination) {
		return destinationMapper.tourModify(destination);
	}
	
	/**
	 * 관광지 세부정보 수정
	 * @param destination
	 * @return
	 */
	public int tourGoodsModify(Destination destination) {
		return destinationMapper.tourGoodsModify(destination);
		
	}

	
	/**
	 * 여행 정보 id에 해당하는 투어 정보 조회
	 * @param tourName
	 * @return
	 */
	public Destination getTourInfoByName(String tourName) {
		
		return destinationMapper.getTourInfoByName(tourName);
	}
	/**
	 * 관광지 정보 리스트
	 * @return
	 */
	public List<Destination> getTourInfoList(){
		List<Destination> tourInfoList = destinationMapper.getTourInfoList();
		
		return destinationMapper.getTourInfoList();
	}
	
	/**
	 * 관광지 옵션 리스트
	 * @return
	 */
	public List<Destination> getTourGoodsList(){
		List<Destination> tourGoodsList = destinationMapper.getTourGoodsList();
		
		return destinationMapper.getTourGoodsList();
	}
	
	
	/**
	 * 관광지 상세목록 정보 조회
	 * @param tourGoodsOptionCd
	 * @return
	 */
	public Destination getTourGoodsInfoById(String tourGoodsOptionCd) {
		return destinationMapper.getTourGoodsInfoById(tourGoodsOptionCd);
		
	}
	/**
	 * 숙소 등록
	 * @param destination
	 */
	public int addLodging(Destination destination) {
		return destinationMapper.addLodging(destination);
		
	}
	/**
	 * 숙소 상세 등록
	 * @param destination
	 * @return
	 */
	public int addLodgingGoods(Destination destination) {
		return destinationMapper.addLodgingGoods(destination);
		
	}

	
	/**
	 * 숙소 중복체크
	 * @param lodgingName
	 * @return
	 */
	public boolean addLodgingCheckList(String lodgingName) {
		
		return destinationMapper.addLodgingCheckList(lodgingName);
	}
	/**
	 * 숙소 상세정보 중복체크
	 * @param lodgingMenuCode
	 * @return
	 */
	public boolean addLodgingGoodsCheckList(String lodgingMenuCode) {
		
		return destinationMapper.addLodgingGoodsCheckList(lodgingMenuCode);
	}


	
	/**
	 * 숙소 삭제
	 * @param lodgingInfoCode
	 */
	public void removeLodging(String lodgingInfoCode) {
		destinationMapper.removeLodging(lodgingInfoCode);
		
	}
	
	/**
	 * 슥소 세부항목 제거
	 * @param lodgingMenuCode
	 */
	public void removeLodgingGoods(String lodgingMenuCode) {
		destinationMapper.removeLodgingGoods(lodgingMenuCode);
		
	}
	
	/**
	 * 숙소 수정
	 * @param destination
	 */

	public void lodgingModify(Destination destination) {
		destinationMapper.lodgingModify(destination);
	}
	
	/**
	 * 숙소 
	 * @param lodgingInfoCode
	 * @return
	 */
	public Destination getLodgingInfoById(String lodgingInfoCode) {
		
		return destinationMapper.getLodgingInfoById(lodgingInfoCode);

	}
	/**
	 * 숙소 정보
	 * @return
	 */
	public List<Destination> getLodgingInfoList(){
		List<Destination> lodgingInfoList = destinationMapper.getLodgingInfoList();
		
		return destinationMapper.getLodgingInfoList();
	}
	/**
	 * 숙소 옵션 리스트
	 * @return
	 */
	public List<Destination> getLodgingGoodsList(){
		List<Destination> lodgingGoodsList = destinationMapper.getLodgingGoodsList();
		
		return destinationMapper.getLodgingGoodsList();
	}
	/**
	 * 숙소 상세 수정
	 * @param destination
	 */
	public void lodgingGoodsModify(Destination destination) {
		destinationMapper.lodgingGoodsModify(destination);
	}
	
	public Destination getLodgingGoodsInfoById(String lodgingGoodsName) {
		return destinationMapper.getLodgingGoodsInfoById(lodgingGoodsName);
	}
	
	/**
	 * 음식점 수정
	 * @param destination
	 */
	public void restaurantMoidfy(Destination destination) {
		destinationMapper.restaurantMoidfy(destination);
	}
	public Destination getRestaurantInfoById(String restaurantInfoCode) {
		return destinationMapper.getRestaurantInfoById(restaurantInfoCode);
	}
	
	
	/**
	 * 음식점 정보
	 * @return
	 */
	
	public List<Destination> getRestaurantInfoList(){
		List<Destination> restaurantInfoList = destinationMapper.getRestaurantInfoList();
		
		return destinationMapper.getRestaurantInfoList();
	}
	/**
	 * 음식점 메뉴 리스트
	 * @return
	 */
	
	public List<Destination> getRestaurantMenuList(){
		List<Destination> restaurantMenuList = destinationMapper.getRestaurantMenuList();
		log.info("DestinationService restaurantMenuList:{}", restaurantMenuList);
		
		return restaurantMenuList;
	}
	
	
	public void restaurantMenuModify(Destination destination) {
		destinationMapper.restaurantMenuModify(destination);
	}
	
	public Destination getRestaurantMenuInfoById(String restaurantMenuManageCode) {
		return destinationMapper.getRestaurantMenuInfoById(restaurantMenuManageCode);
	}

	
	/**
	 * 음식점 삭제
	 * @param restaurantInfoCode
	 */
	public void removeRestaurant(String restaurantInfoCode) {
		destinationMapper.removeRestaurant(restaurantInfoCode);
		
	}
	
	/**
	 * 음식점 상세정보 삭제
	 * @param restaurantMenuManageCode
	 */
	public void removeRestaurantMenu(String restaurantMenuManageCode) {
		destinationMapper.removeRestaurantMenu(restaurantMenuManageCode);
		
	}
	/**
	 * 음식점 중복체크
	 * @param restaurantName
	 * @return
	 */
	public boolean addRestaurantCheckList(String restaurantName) {
		return destinationMapper.addRestaurantCheckList(restaurantName);
	}


}






	
	
	


