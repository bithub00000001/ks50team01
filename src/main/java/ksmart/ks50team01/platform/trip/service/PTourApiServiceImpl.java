package ksmart.ks50team01.platform.trip.service;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import com.fasterxml.jackson.databind.JsonNode;

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
					.queryParam("pageNo", 1)
					.queryParam("MobileOS", "ETC")
					.queryParam("MobileApp", "KSMART50")
					.queryParam("_type", "json");
				return getTourApiUri(optionalAreaCode, uriBuilder, builder);
			})
			// 결과 타입을 JSON 으로 받도록 설정
			.accept(MediaType.APPLICATION_JSON)
			// 요청을 전송하고 결과를 반환(여기에서 실제 네트워크 요청 이루어짐)
			.retrieve()
			// 변환된 데이터를 dto.calss 타입의 객체로 변환
			.bodyToMono(JsonNode.class)
			.flatMap(jsonNode -> getRegionListMono(optionalAreaCode, jsonNode))
			.onErrorResume(error -> {
				log.error("getAreaData error: {}", error.getMessage());
				return Mono.error(error);
			});
	}

	private static URI getTourApiUri(Optional<String> optionalAreaCode, UriBuilder uriBuilder, UriBuilder builder) {
		// areaCode 가 존재한다면 그 값을 사용하여 쿼리 파라미터를 추가
		optionalAreaCode.ifPresent(areaCode ->
			uriBuilder.queryParam("areaCode", areaCode));
		log.info("uriBuilder: {}", uriBuilder.toUriString());
		return builder.build();
	}

	private Mono<List<PTourApi>> getRegionListMono(Optional<String> optionalAreaCode, JsonNode jsonNode) {
		if (optionalAreaCode.isPresent()) {
			// optionalAreaCode가 있는 경우, convertJsonToSigunguData 메서드 호출
			return convertJsonToSigunguData(jsonNode, optionalAreaCode.get());
		}else {
			// optionalAreaCode가 없는 경우, convertJsonToAreaData 메서드 호출
			return convertJsonToAreaData(jsonNode);
		}
	}

	/**
	 * JSON 데이터를 PTourAPI 객체 리스트로 변환
	 * @param jsonNode
	 * @return
	 */
	private Mono<List<PTourApi>> convertJsonToAreaData(JsonNode jsonNode) {
		// 요청이 "response.body.items.item" 경로로 설정되어 있어 경로에 맞게 데이터를 추출
		JsonNode itemsNode = jsonNode.path("response")
			.path("body")
			.path("items")
			.path("item");

		log.info("convertJsonToAreaData jsonNode: {}", itemsNode);

		// 추출한 데이터를 ObjectMapper로 변환
		List<PTourApi> pTourApiList = createAreaDataListFromJson(itemsNode);
		log.info("convertJsonToAreaData pTourApiList: {}", pTourApiList);
		return Mono.just(pTourApiList);
	}
	private List<PTourApi> createAreaDataListFromJson(JsonNode itemsNode) {
		return itemsNode.isArray() ?
			StreamSupport.stream(itemsNode.spliterator(),false)
				.map(item -> {
					PTourApi pTourApi = new PTourApi();
					pTourApi.setAreaCode(item.get("code").asText());
					pTourApi.setAreaName(item.get("name").asText());
					pTourApi.setAreaRNum(item.get("rnum").asText());
					return pTourApi;
				})
				.collect(Collectors.toList())
			: Collections.emptyList();
	}

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

			// 받아온 데이터를 DB에 업서트
			areaData.forEach(pTourApiMapper::upsertAreaCode);

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
		List<PTourApi> areaCodeList = pTourApiMapper.getAreaCodeList();

		areaCodeList.forEach(areaCode -> {
			Mono<List<PTourApi>> sigunguDataMono = getAreaData(apiKey, Optional.ofNullable(areaCode.getAreaCode()));
			sigunguDataMono.subscribe(sigunguData -> {
				// 받아온 데이터를 DB에 업서트
				sigunguData.forEach(pTourApiMapper::upsertSigunguCode);

				log.info("=== upsertSigunguData Method exit ===");
			});
		});
	}

	/**
	 * 지역 코드 데이터를 시군구 코드 데이터로 변환하는 메서드
	 * @param
	 * @return
	 */
	private Mono<List<PTourApi>> convertJsonToSigunguData(JsonNode jsonNode, String areaCode) {
		JsonNode itemsNode = jsonNode.path("response")
			.path("body")
			.path("items")
			.path("item");

		log.info("convertJsonToSigunguData jsonNode: {}", itemsNode);

		List<PTourApi> pTourApiList = createSigunguDataListFromJson(itemsNode, areaCode);
		log.info("convertJsonToSigunguData pTourApiList: {}", pTourApiList);
		return Mono.just(pTourApiList);
	}

	private List<PTourApi> createSigunguDataListFromJson(JsonNode itemsNode, String areaCode) {
		return itemsNode.isArray() ?
			StreamSupport.stream(itemsNode.spliterator(), false)
				.map(item -> {
					PTourApi pTourApi = new PTourApi();
					pTourApi.setSigunguCode(item.get("code").asText());
					pTourApi.setSigunguName(item.get("name").asText());
					pTourApi.setAreaCode(areaCode);
					return pTourApi;
				})
				.collect(Collectors.toList())
			: Collections.emptyList();
	}

	@Override
	public List<PTourApi> getAreaCodeList(){
		return pTourApiMapper.getAreaCodeList();
	}

	@Override
	public List<PTourApi> getSigunguCodeList(){

		pTourApiMapper.getSigunList();
		return pTourApiMapper.getSigunList();
	}
}
