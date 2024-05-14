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
	
	
	

}
