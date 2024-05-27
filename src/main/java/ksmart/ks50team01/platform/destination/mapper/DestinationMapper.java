package ksmart.ks50team01.platform.destination.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.platform.destination.dto.Destination;

@Mapper
public interface DestinationMapper {
	
	//관광상품 등록
	boolean addTourGoodsCheckList(String tourGoodsOptionCd);
	
	//관광지 등록
	boolean addTourCheckList(String tourName);
	
	//관광지 상세목록 
	void removeTourGoods(String tourGoodsOptionCd);
	
	// 관광지 삭제
	void removeTour(String tourInfoId);
	
	// 관광지 조회
	List<Destination> getTourInfoList();
	
	// 관광지 수정
	int updateTour(Destination destination);
	Destination getTourInfoByName(String tourName);
	
	//관광지 세부정보 수정
	int updateTourGoods(Destination destination);
	Destination getTourGoodsInfoById(String tourName);
	
	//관광상품 조회
	List<Destination> getTourGoodsList();
	
	//숙소 수정
	
	int updateLodging(Destination destination);
	Destination getLodgingInfoById(String lodgingName);
	
	//숙소 세부정보 수정
	int updateLodgingGoods(Destination destination);
	Destination getLodgingGoodsInfoById(String lodgingName);

	
	//숙소 조회
	List<Destination> getLodgingInfoList();
	
	//식당 조회
	List<Destination> getRestaurantInfoList();
	
	
	//숙소상품 조회
	List<Destination> getLodgingGoodsList();
	
	//식당 메뉴 조회
	List<Destination> getRestaurantMenuList();
	
	//식당 수정
	int updateRestaurant(Destination destination);
	Destination getRestaurantInfoById(String RestaurantName);
	//식당 상세정보 수정
	int updateRestaurantMenu(Destination destination);
	Destination getRestaurantMenuInfoById(String restaurantMenuManageCode);

	List<Destination> searchTourName(String tourName);




	


	

	
}
