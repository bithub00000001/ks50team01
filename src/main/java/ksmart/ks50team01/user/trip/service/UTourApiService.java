package ksmart.ks50team01.user.trip.service;

import ksmart.ks50team01.user.trip.dto.UTourApi;
import reactor.core.publisher.Mono;

public interface UTourApiService {
	Mono<UTourApi> getTourData(String apiKey, String numOfRows, String pageNo, String mobileOS, String mobileApp, String responseType);
}
