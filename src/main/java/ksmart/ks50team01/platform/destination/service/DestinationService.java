package ksmart.ks50team01.platform.destination.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.platform.destination.dto.Destination;
import ksmart.ks50team01.platform.destination.mapper.DestinationMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
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
	
	public List<Destination> getrestaurantInfoList(){
		List<Destination> restaurantInfoList = destinationMapper.getRestaurantInfoList();
		
		return destinationMapper.getRestaurantInfoList();
	}
	
	
	

}
