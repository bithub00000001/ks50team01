package ksmart.ks50team01.user.trip.service;

import java.util.List;
import java.util.Optional;

import ksmart.ks50team01.user.trip.dto.UArea;
import ksmart.ks50team01.user.trip.dto.UTourApi;
import reactor.core.publisher.Mono;

public interface UTourApiService {
	// Tour api 에서 시군 코드를 조회하는 메서드
	Mono<List<UArea>> getRegionData(String apiKey, Optional<String> optionalAreaCode);

	// Tour api 에서 지역 코드만을 조회하는 메서드
	Mono<List<UArea>> getRegionData(String apiKey);
}
