package ksmart.ks50team01.user.trip.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import ksmart.ks50team01.user.trip.dto.UTourApi;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Transactional
@Slf4j
public class UTourApiServiceImpl implements UTourApiService{
	private final WebClient webClient;

	@Value("${tour.api.key}")
	private String apiKey2;

	public UTourApiServiceImpl(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.baseUrl("http://apis.data.go.kr/").build();
	}

	@Override
	public Mono<UTourApi> getTourData(String apiKey, String numOfRows, String pageNo, String mobileOS, String mobileApp, String responseType){

		return webClient.get()
			.uri(UriBuilder -> UriBuilder
				.path("/B551001/KorService1")
				.queryParam("serviceKey",apiKey)
				.queryParam("numOfRows", numOfRows)
				.queryParam("pageNo", pageNo)
				.queryParam("MobileOS", mobileOS)
				.queryParam("MobileApp", mobileApp)
				.queryParam("_type", responseType)

				// 필요한 다른 쿼리 매개변수 추가
				.build())
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(UTourApi.class);
	}

}
