package ksmart.ks50team01.platform.trip.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import ksmart.ks50team01.platform.trip.dto.PTourApi;
import ksmart.ks50team01.platform.trip.dto.PTourDetail;
import reactor.core.publisher.Mono;

public interface PTourApiService {
	// 시군 코드 Tour API에서 호출
	Mono<List<PTourApi>> getAreaData(String apiKey, Optional<String> optionalAreaCode);

	// 지역 코드 Tour API에서 호출
	Mono<List<PTourApi>> getAreaData(String apiKey);

	// 지역 코드 업서트 메서드
	void upsertAreaData(String apiKey);

	// 시군 코드 업서트 메서드
	void upsertSigunguData(String apiKey);

	// 여행지 정보 Tour API에서 호출
	Mono<List<PTourApi>> getTourInfo(String apiKey, int contentTypeId, int numOfRows, int pageNo, String areaCode, Optional<String> optionalSigunguCode);

	// 여행지 상세 정보 Tour API에서 호출
	Mono<PTourDetail> getTourDetail(String apiKey, String contentId, String contentTypeId);

	// 여행지 정보 업서트 메서드
	void upsertTourInfoList(List<PTourApi> tourInfoList);

	//
	void upsertTourDetail(PTourDetail tourDetail);
}

