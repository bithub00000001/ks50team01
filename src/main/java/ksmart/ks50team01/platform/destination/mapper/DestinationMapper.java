package ksmart.ks50team01.platform.destination.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.platform.destination.dto.Destination;

@Mapper
public interface DestinationMapper {
	
	//관광지 등록
	int addTour(Destination destination);

	//관광지 세부등록
	int addTourGoods(Destination destination);
	
	//관광지 세부옵션 등록
	int addTourGoodsOp(Destination destination);
	
	//관광상품 중복체크
	boolean addTourGoodsCheckList(String tourGoodsOptionCd);
	
	//관광지 중복체크
	boolean addTourCheckList(String tourName);
	
	//관광지 상세목록 삭제
	void removeTourGoods(String tourGoodsOptionCd);
	
	// 관광지 삭제
	void removeTour(String tourInfoId);
	
	// 관광지 조회
	List<Destination> getTourInfoList();
	
	// 관광지 이름 조회
	List<Destination> getTourInfoListByName(String tourName);
	
	// 관광지 수정
	int tourModify(Destination destination);
	Destination getTourInfoByName(String tourInfoCode);
	
	//관광지 세부정보 수정
	int tourGoodsModify(Destination destination);
	Destination getTourGoodsInfoById(String tourGoodsOptionCd);
	
	//관광상품 조회
	List<Destination> getTourGoodsList();
	
	//숙소 이름 조회
	List<Destination> getLodgingInfoListByName(String lodgingName);
	
	//숙소 등록
	int addLodging(Destination destination);
	
	//숙소 상세정보 등록
	int addLodgingGoods(Destination destination);
	
	//숙소 중복체크
	boolean addLodgingCheckList(String lodgingName);
	
	//숙소 상세 중복체크
	boolean addLodgingGoodsCheckList(String lodgingMenuCode);

	//숙소 삭제
	void removeLodging(String lodgingInfoCode);
	
	//숙소 상세목록 삭제
	void removeLodgingGoods(String lodgingMenuCode);
	
	//숙소 수정
	int lodgingModify(Destination destination);
	Destination getLodgingInfoById(String lodgingName);
	
	//숙소 세부정보 수정
	int lodgingGoodsModify(Destination destination);
	Destination getLodgingGoodsInfoById(String lodgingName);

	//숙소 조회
	List<Destination> getLodgingInfoList();
	
	//숙소상품 조회
	List<Destination> getLodgingGoodsList();
	
	//음식점 조회
	List<Destination> getRestaurantInfoList();
	
	//음식점 메뉴 조회
	List<Destination> getRestaurantMenuList();
	
	
	//음식점 이름 중복체크
	boolean addRestaurantCheckList(String restaurantName);
	
	//음식점 수정
	int restaurantMoidfy(Destination destination);
	Destination getRestaurantInfoById(String RestaurantName);
	
	//음식점 상세정보 수정
	int restaurantMenuModify(Destination destination);
	Destination getRestaurantMenuInfoById(String restaurantMenuManageCode);
	
	//음식점 삭제
	void removeRestaurant(String restaurantInfoCode);
	
	//음식점 상세정보 삭제
	void removeRestaurantMenu(String restaurantMenuManageCode);


	



	// List<Destination> searchTourName(String tourName);




	


	

	
}
