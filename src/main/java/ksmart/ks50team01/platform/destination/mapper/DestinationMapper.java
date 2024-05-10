package ksmart.ks50team01.platform.destination.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.platform.destination.dto.Destination;

@Mapper
public interface DestinationMapper {
	// 관광지 조회
	List<Destination> getTourInfoList();
	
	//숙소 조회
	List<Destination> getLodgingInfoList();
	
	//식당 조회
	List<Destination> getRestaurantInfoList();
	
	//관광상품 조회
	List<Destination> getTourGoodsList();
}
