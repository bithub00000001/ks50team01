package ksmart.ks50team01.platform.trip.service;

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

import ksmart.ks50team01.platform.trip.dto.PTourApi;
import ksmart.ks50team01.platform.trip.mapper.PTourApiMapper;
import ksmart.ks50team01.platform.trip.mapper.PTripPlanMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PTourApiServiceImpl implements PTourApiService {

	private final PTripPlanMapper pTripPlanMapper;
	private final PTourApiMapper pTourApiMapper;


	// API를 WebClientConfig에서 설정한 baseUrl로 이용하기 위해 DI 주입
	private final WebClient webClient;

	// JSON 파싱을 위한 ObjectMapper DI 주입
	private final ObjectMapper objectMapper;

	/**
	 * 지역 코드를 조회 하거나 지역코드에 해당하는 시군 코드를 Tour API에 요청
	 * @param apiKey API 에서 사용하는 개인 디코딩 키
	 * @param optionalAreaCode 인수가 1개인 메서드에서 조회한 지역 코드
	 * @return 결과를 json 형태로 변환해 반환
	 */
	@Override
	public Mono<List<PTourApi>> getAreaData(String apiKey, Optional<String> optionalAreaCode){
		// API 키가 정상적으로 들어오는지 확인
		log.info("getAreaData 메서드의 apiKey: {}", apiKey);
		return webClient.get()
			.uri(uriBuilder -> {
				UriBuilder builder = uriBuilder
					.path(("/areaCode1"))
					.queryParam("serviceKey",apiKey)
					.queryParam("numOfRows",50)
					.queryParam("pagerNo", 1)
					.queryParam("MobileOS", "ETC")
					.queryParam("MobileApp", "KSMART50")
					.queryParam("_type", "json");

				// areaCode 가 존재한다면 그 값을 사용하여 쿼리 파라미터를 추가
				optionalAreaCode.ifPresent(areaCode ->
					uriBuilder.queryParam("areaCode", areaCode));
				log.info("uriBuilder: {}", uriBuilder.toUriString());
				return builder.build();
		})
			// 결과 타입을 JSON 으로 받도록 설정
			.accept(MediaType.APPLICATION_JSON)
			// 요청을 전송하고 결과를 반환(여기에서 실제 네트워크 요청 이루어짐)
			.retrieve()
			// 변환된 데이터를 dto.calss 타입의 객체로 변환
			.bodyToMono(JsonNode.class)
			.map(jsonNode -> {
				// 요청이 "response.body.items.item" 경로로 설정되어 있어 경로에 맞게 데이터를 추출
				JsonNode itemsNode = jsonNode.path("response")
					.path("body")
					.path("items")
					.path("item");

				// 추출한 데이터를 ObjectMapper로 변환
				List<PTourApi> pTourApiList = objectMapper.convertValue(itemsNode, new TypeReference<>() {
				});
				return pTourApiList;
			});
	}
	/*
	* Changes made:

	 * Removed unnecessary comments
	 * Simplified the UriBuilder creation using method chaining
	 * Removed the unnecessary `builder` variable
	 * Simplified the lambda expression in `optionalAreaCode.ifPresent()`
	 * Removed the unnecessary `return` statement at the end of the method
	 * Simplified the `map()` function by removing the unnecessary variable declaration
	 * I replaced `UriBuilder.of` with `UriBuilder.newInstance()`, which is the correct method to create a new `UriBuilder` instance.
	 * */
	/*public Mono<List> getAreaData(String apiKey, Optional optionalAreaCode) {
		log.info("getAreaData method's apiKey: {}", apiKey);
		return webClient.get().uri(uriBuilder -> {
			UriBuilder builder = uriBuilder
				.path("/areaCode1")
				.queryParam("serviceKey", apiKey)
				.queryParam("numOfRows", 50)
				.queryParam("pagerNo", 1)
				.queryParam("MobileOS", "ETC")
				.queryParam("MobileApp", "KSMART50")
				.queryParam("_type", "json");
			optionalAreaCode.ifPresent(areaCode -> builder.queryParam("areaCode", areaCode));
			return builder.build();
		}).accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(JsonNode.class).map(jsonNode -> {
			JsonNode itemsNode = jsonNode.path("response").path("body").path("items").path("item");
			return objectMapper.convertValue(itemsNode, new TypeReference<>() {
			});
		});
	}*/

	// 숙소 정보 조회 메서드 작성중
	/*public Mono<PTourApi> getAccommodationData(String apiKey){
		return webClient.get().uri(uriBuilder -> {
			UriBuilder builder = uriBuilder
				.path("/api/rest/pacakge")
				.queryParam("ServiceKey", apiKey)
				.queryParam()

		})
	}*/
	//public Mono<PTourApi> getAccommodationData(String apiKey, )

	/**
	 * Tour API 에 지역 코드만 요청하는 메서드
	 * @param apiKey API 에서 사용하는 개인 디코딩 키
	 * @return 2개의 인수가 있는 메서드에서 Optional.empty()를 사용해 빈 값을 주입하면 지역 코드만 조회
	 */
	@Override
	public Mono<List<PTourApi>> getAreaData(String apiKey){
		return getAreaData(apiKey,Optional.empty());
	}

	/**
	 * Tour API 지역 코드 데이터 조회 후 DB 업서트
	 * @param apiKey 공공데이터포탈 개인 디코딩 키
	 */
	@Override
	public void upsertAreaData(String apiKey) {
		log.info("upsertAreaData apiKey: {}", apiKey);

		// Tour APi 에서 지역 코드 데이터 조회
		Mono<List<PTourApi>> areaDataMono = getAreaData(apiKey);
		areaDataMono.subscribe(areaData -> {
			log.info("Tour API 지역 코드 데이터 조회: {}", areaData);

			// 받아온 데이터 DTO 객체 시군 코드 변환
			List<PTourApi> areaList = areaData.stream()
				.map(item -> {
					PTourApi pTourApi = new PTourApi();
					pTourApi.setAreaCode(item.getAreaCode());
					pTourApi.setAreaName(item.getAreaName());
					return pTourApi;
				})
				.toList();
			log.info("변환된 지역 코드 객체 리스트: {}", areaList);

			// 객체 리스트를 DB에 업서트
			areaList.forEach(pTourApiMapper::upsertAreaCode);

			log.info("=== upsertAreaData Method exit ===");
		});
	}

	/**
	 * DB에 저장된 지역 코드를 가져와 Tour API 시군구 코드 조회 후 DB에 업서트
	 * @param apiKey 공공데이터포탈 개인 디코딩 키
	 */
	@Override
	public void upsertSigunguData(String apiKey) {
		log.info("upsertSigunguData apiKey: {}", apiKey);

		// DB에서 모든 지역 코드 조회
		List<PTourApi> areaCodeList = pTourApiMapper.getAllAreaCodes();
		log.info("areaCodeList: {}", areaCodeList);

		areaCodeList.forEach(areaCode -> {
			log.info("processing areaCode: {}", areaCode);

			// 해당 지역 코드로 Tour API 에서 시군구 코드 조회
			Mono<List<PTourApi>> sigunguDataMono = getAreaData(apiKey,Optional.of(areaCode.getAreaCode()));
			sigunguDataMono.subscribe(sigunguDataList -> {
				log.info("sigunguDataList: {}", sigunguDataList);

				// 받아온 데이터를 객체 리스트로 변환
				List<PTourApi> sigunguList = sigunguDataList.stream()
					.map(item -> {
						PTourApi sigunguData = new PTourApi();

						// 시군구 코드 설정
						sigunguData.setSigunguCode(item.getSigunguCode());
						sigunguData.setSigunguName(item.getSigunguName());

						sigunguData.setAreaCode(item.getAreaCode());
						return sigunguData;
					}).toList();
				log.info("변환된 시군구 객체 리스트 sigunguList: {}", sigunguList);

				// 변환된 객체 리스트를 DB에 업서트
				sigunguList.forEach(pTourApiMapper::upsertSigunguCode);

			});
		});
	}
}
