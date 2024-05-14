package ksmart.ks50team01.platform.destination.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.platform.destination.dto.Destination;

@Mapper
public interface DestinationMapper {
	
	// 관광지 조회
	List<Destination> getTourInfoList();
	
	// 관광지 수정
	int updateTour(Destination destination);
	
	
	Destination getTourInfoByName(String tourName);
	//숙소 수정
	
	int updateLodging(Destination destination);
	
	Destination getLodgingInfoById(String lodgingName);
	
	//숙소 조회
	List<Destination> getLodgingInfoList();
	
	//식당 조회
	List<Destination> getRestaurantInfoList();
	
	//관광상품 조회
	List<Destination> getTourGoodsList();
	
	//숙소상품 조회
	List<Destination> getLodgingGoodsList();
	
	//식당 메뉴 조회
	List<Destination> getRestaurantMenuList();

	

	
}
