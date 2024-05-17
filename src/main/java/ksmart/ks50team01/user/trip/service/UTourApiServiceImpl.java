package ksmart.ks50team01.user.trip.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ksmart.ks50team01.user.trip.dto.UArea;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UTourApiServiceImpl implements UTourApiService{

	private final WebClient webClient;


	// JSON 파싱을 위한 ObjectMapper 인스턴스 생성
	private final ObjectMapper objectMapper;

	/**
	 * 지역 코드를 조회 한 후 그에 해당하는 시군 코드를 Tour api 에 요청하는 메서드
	 * @param apiKey api에서 사용하는 디코딩 키
	 * @param optionalAreaCode 인수가 1개인 메서드에서 조회한 지역 코드
	 * @return
	 */
	@Override
	public Mono<List<UArea>> getRegionData(String apiKey, Optional<String> optionalAreaCode){
		return webClient.get()
			.uri(uriBuilder -> {
				UriBuilder builder =  uriBuilder
					.path("/areaCode1")
					.queryParam("serviceKey",apiKey)
					.queryParam("numOfRows", 100)
					.queryParam("pageNo", 1)
					.queryParam("MobileOS", "ETC")
					.queryParam("MobileApp", "ETCTest")
					.queryParam("_type", "json");
					// areaCode가 존재하면 그 값을 사용하여 쿼리 파라미터를 추가
					optionalAreaCode.ifPresent(areaCode -> uriBuilder
						.queryParam("areaCode",areaCode));
					log.info("uriBuilder:{}",uriBuilder.toUriString());
					return builder.build();
			})
			// 결과 타입을 JSON으로 받기
			.accept(MediaType.APPLICATION_JSON)
			// 요청을 전송하고 결과를 반환(실제 네트워크 요청)
			.retrieve()
			// 반환된 데이터를 dto.class 타입의 객체로 변환
			.bodyToMono(JsonNode.class)
			.map(jsonNode -> {
				// "response.body.items.item" 경로에 따라 데이터를 추출하고 변환
				JsonNode itemsNode = jsonNode.path("response")
					.path("body")
					.path("items")
					.path("item");
				List<UArea> areaList = objectMapper.convertValue(itemsNode, new TypeReference<>() {
				});
				return areaList;
			});
	}

	/**
	 *  Tour api에 지역 코드 만 요청하는 메서드
	 * @param apiKey api에서 사용하는 디코딩 키
	 * @return 2개의 인수가 있는 메서드에서 Optional.empty()를 사용해 비어 있는 값 주입
	 */
	@Override
	public Mono<List<UArea>> getRegionData(String apiKey) {
		log.info("getRegionData: {}",apiKey );
		return getRegionData(apiKey, Optional.empty());
	}

}
