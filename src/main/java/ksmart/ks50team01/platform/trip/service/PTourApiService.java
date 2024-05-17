package ksmart.ks50team01.platform.trip.service;

import java.util.List;
import java.util.Optional;

import ksmart.ks50team01.platform.trip.dto.PTourApi;
import reactor.core.publisher.Mono;

public interface PTourApiService {
	Mono<List<PTourApi>> getAreaData(String apiKey, Optional<String> optionalAreaCode);

	Mono<List<PTourApi>> getAreaData(String apiKey);

	void upsertAreaData(String apiKey);

	void upsertSigunguData(String apiKey);

	/*Mono<List<PTourApi>> getTourInfo(String apiKey, Integer contentTypeId, Integer numOfRows, Integer pageNo, String areaCode, Optional<String> optionalSigunguCode);*/

	Mono<List<PTourApi>> getTourInfo(String apiKey, int contentTypeId, int numOfRows, int pageNo, String areaCode, Optional<String> optionalSigunguCode);

	void saveData(List<PTourApi> tourInfoList);
}

