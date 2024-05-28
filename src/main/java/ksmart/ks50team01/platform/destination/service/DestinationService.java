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
	 * 관광지 세부항목 추가
	 * @param tourGoodsOptionCd
	 * @return
	 */
	public boolean addTourGoodsCheckList(String tourGoodsOptionCd) {
		return destinationMapper.addTourGoodsCheckList(tourGoodsOptionCd);
	}
	
	/**
	 * 관광지 추가
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
	
	
	public void updateTour(Destination destination) {
		log.info("service destination:{}", destination);
		destinationMapper.updateTour(destination);
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
	
	public void updateTourGoods(Destination destination) {
		destinationMapper.updateTourGoods(destination);
	}
	public Destination getTourGoodsInfoById(String tourGoodsOptionCd) {
		return destinationMapper.getTourGoodsInfoById(tourGoodsOptionCd);
		
	}
	/**
	 * 숙소 삭제
	 * @param lodgingInfoCode
	 */
	public void removeLodging(String lodgingInfoCode) {
		destinationMapper.removeLodging(lodgingInfoCode);
		
	}
	
	/**
	 * 관광지 세부항목 제거
	 * @param tourGoodsOptionCd
	 */
	public void removeLodgingGoods(String lodgingMenuCode) {
		destinationMapper.removeLodgingGoods(lodgingMenuCode);
		
	}
	
	/**
	 * 숙소 수정
	 * @param destination
	 */

	public void updateLodging(Destination destination) {
		destinationMapper.updateLodging(destination);
	}
	
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
	
	public void updateLodgingGoods(Destination destination) {
		destinationMapper.updateLodgingGoods(destination);
	}
	
	public Destination getLodgingGoodsInfoById(String lodgingGoodsName) {
		return destinationMapper.getLodgingGoodsInfoById(lodgingGoodsName);
	}
	
	/**
	 * 음식점 수정
	 * @param destination
	 */
	public void updateRestaurant(Destination destination) {
		destinationMapper.updateRestaurant(destination);
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

	public void updateRestaurantMenu(Destination destination) {
		destinationMapper.updateRestaurantMenu(destination);
	}
	public Destination getRestaurantMenuInfoById(String restaurantMenuManageCode) {
		return destinationMapper.getRestaurantMenuInfoById(restaurantMenuManageCode);
	}


	public void removeRestaurant(String restaurantInfoCode) {
		destinationMapper.removeRestaurant(restaurantInfoCode);
		
	}

}






	
	
	


