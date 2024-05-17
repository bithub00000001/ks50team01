package ksmart.ks50team01.platform.trip.service;

import java.net.URI;
import java.util.ArrayList;
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
import org.springframework.web.util.UriComponentsBuilder;

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
	private final PTripPlanService pTripPlanService;


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
				uriBuilder.path(("/areaCode1"))
					.queryParam("serviceKey",apiKey)
					.queryParam("numOfRows",50)
					.queryParam("pageNo", 1)
					.queryParam("MobileOS", "ETC")
					.queryParam("MobileApp", "KSMART50Test")
					.queryParam("_type", "json");
				return getTourApiUri(optionalAreaCode, uriBuilder);
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

	/**
	 * Optional 지역 코드가 존재한다면 쿼리 파라미터 추가
	 * @param optionalAreaCode Optional로 지정된 지역 코드
	 * @param uriBuilder
	 * @return
	 */
	private static URI getTourApiUri(Optional<String> optionalAreaCode, UriBuilder uriBuilder) {
		optionalAreaCode.ifPresent(areaCode ->
			uriBuilder.queryParam("areaCode", areaCode));
		log.info("uriBuilder: {}", uriBuilder.toUriString());
		return uriBuilder.build();
	}

	private Mono<List<PTourApi>> getRegionListMono(Optional<String> optionalAreaCode, JsonNode jsonNode) {
		if (optionalAreaCode.isPresent()) {
			// optionalAreaCode가 있는 경우, convertJsonToSigunguData 메서드 호출
			return convertJsonToSigunguData(jsonNode, optionalAreaCode.get());
		}else {
			// optionalAreaCode가 없는 경우, convertJsonToAreaData 메서드 호출
			return convertJsonToAreaData(jsonNode);
		}
		// 함수 표현식으로도 표현 가능
		/*return optionalAreaCode.map(s -> convertJsonToSigunguData(jsonNode, s))
			.orElseGet(() -> convertJsonToAreaData(jsonNode));*/
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
		List<PTourApi> areaCodeList = pTripPlanService.getAreaCodeList();

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
	 * @param jsonNode API에서 응답받은 결과를 변환한 JsonNode
	 * @param areaCode 지역 코드
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

	// 숙소 정보 조회 메서드 작성중

	/**
	 * 원본 메서드
	 * Tour API 에서 관광타입(12:관광지, 14:문화시설, 15:축제공연행사, 25:여행코스, 28:레포츠, 32:숙박, 38:쇼핑, 39:음식점)을 조회하는 메서드
	 * @param apiKey 개인 디코딩 키
	 * @param contentTypeId 관광타입(12:관광지, 14:문화시설, 15:축제공연행사, 25:여행코스, 28:레포츠, 32:숙박, 38:쇼핑, 39:음식점)
	 * @param numOfRows 한페이지결과수
	 * @param pageNo 페이지번호
	 * @param areaCode 지역코드
	 * @param optionalSigunguCode 시군구코드
	 * @return
	 */
	/*public Mono<List<PTourApi>> getTourInfo(String apiKey, Integer contentTypeId, Integer numOfRows, Integer pageNo, String areaCode, Optional<String> optionalSigunguCode) {
		return webClient.get()
			.uri(uriBuilder -> {
				UriBuilder builder = uriBuilder
					.path(("/areaBasedList1"))
					.queryParam("ServiceKey", apiKey)
					.queryParam("numOfRows", numOfRows)
					.queryParam("pageNo", pageNo)
					.queryParam("contentTypeId", contentTypeId)
					.queryParam("areaCode", areaCode)
					.queryParam("MobileOS", "ETC")
					.queryParam("MobileApp", "AppTest")
					.queryParam("listYN", "Y")
					.queryParam("_type", "json");
				optionalSigunguCode.ifPresent(sigunCode -> builder.queryParam("sigunguCode", sigunCode));
				return builder.build();
			})
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(JsonNode.class)
			.flatMap(jsonNode -> {
				try {
					String resultMsg = jsonNode
						.path("response")
						.path("header")
						.path("resultMsg")
						.asText();
					if ("OK".equals(resultMsg)) {
						JsonNode itemNodes = jsonNode
							.path("response")
							.path("body")
							.path("items")
							.path("item");
						if (itemNodes.isArray()) {
							List<PTourApi> tourList = new ArrayList<>();
							for (JsonNode itemNode : itemNodes) {
								PTourApi tourApi = new PTourApi();
								// itemNode의 각 필드 값을 직접 읽어와 PTourApi 객체에 설정
								tourApi.setDestinationFirstAddress(itemNode.get("addr1").asText());
								tourApi.setDestinationSecondAddress(itemNode.get("addr2").asText());
								tourApi.setAreaCode(itemNode.get("areaCode").asText());
								tourApi.setDestinationContentId(itemNode.get("contentid").asText());
								tourApi.setDestinationContentTypeId(itemNode.get("contenttypeid").asText());
								tourApi.setDestinationFirstImageLink(itemNode.get("firstimage").asText());
								tourApi.setDestinationSecondImageLink(itemNode.get("firstimage2").asText());
								tourApi.setDestinationLongitude(itemNode.get("mapx").asDouble());
								tourApi.setDestinationLatitude(itemNode.get("mapy").asDouble());
								tourApi.setSigunguCode(itemNode.get("sigungucode").asText());
								tourApi.setDestinationTitle(itemNode.get("title").asText());
								tourApi.setDestinationTelNum(itemNode.get("tel").asText());
								tourApi.setDestinationZipcode(itemNode.get("zipcode").asText());
								// 다른 필드들도 마찬가지로 설정
								tourList.add(tourApi);
							}
							return Mono.just(tourList);
						} else {
							return Mono.error(new RuntimeException("Invalid API Response: items is not an array"));
						}
					} else {
						return Mono.error(new RuntimeException("API Response Error: " + resultMsg));
					}
				} catch (Exception e) {
					return Mono.error(new RuntimeException("Error processing API response", e));
				}
			});
	}*/

	// getTourInfo 메서드 리팩토링

	/**
	 * webClient를 사용하여 외부 API에 Get 요청을 보내고, 응답을 처리해 List<PTourApi> 객체로 반환
	 * @param apiKey 개인 디코딩 키
	 *  @param contentTypeId 관광 타입(12:관광지, 14:문화시설, 15:축제공연행사, 25:여행코스, 28:레포츠, 32:숙박, 38:쇼핑, 39:음식점)
	 * @param numOfRows 한 페이지 결과수
	 * @param pageNo 페이지번호
	 * @param areaCode 지역 코드
	 * @param optionalSigunguCode 시군구 코드, 비어 있어도 메서드가 작동하도록
	 * @return List<PTourApi> 객체
	 */
	@Override
	public Mono<List<PTourApi>> getTourInfo(String apiKey, int contentTypeId, int numOfRows, int pageNo, String areaCode, Optional<String> optionalSigunguCode) {
		return webClient.get()
			.uri(uriBuilder -> buildUri(apiKey, contentTypeId, numOfRows, pageNo, areaCode, optionalSigunguCode))
			.retrieve()
			.bodyToMono(JsonNode.class)
			.flatMap(this::processApiResponse)
			.onErrorResume(error -> {
				log.error("Error occurred while fetching tour info: {}", error.getMessage());
				return Mono.error(error);
			});
	}

	/**
	 * API 요청을 위한 URI 생성
	 * @param apiKey 개인 디코딩 키
	 * @param contentTypeId 관광타입(12:관광지, 14:문화시설, 15:축제공연행사, 25:여행코스, 28:레포츠, 32:숙박, 38:쇼핑, 39:음식점)
	 * @param numOfRows 한 페이지 결과수
	 * @param pageNo 페이지 번호
	 * @param areaCode 지역 코드
	 * @param optionalSigunguCode 시군구 코드, 비어 있어도 메서드가 작동하도록
	 * @return URI 반환
	 */
	private URI buildUri(String apiKey, Integer contentTypeId, Integer numOfRows, Integer pageNo, String areaCode, Optional<String> optionalSigunguCode) {
		UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
			.scheme("https")
			.host("apis.data.go.kr")
			.path("/B551011/KorService1/areaBasedList1")
			.queryParam("ServiceKey", apiKey)
			.queryParam("numOfRows", numOfRows)
			.queryParam("pageNo", pageNo)
			.queryParam("contentTypeId", contentTypeId)
			.queryParam("areaCode", areaCode)
			.queryParam("MobileOS", "ETC")
			.queryParam("MobileApp", "AppTest")
			.queryParam("listYN", "Y")
			.queryParam("_type", "json");
		optionalSigunguCode.ifPresent(sigunguCode -> builder.queryParam("sigunguCode", sigunguCode));
		log.info("url: {}",builder.build().toUri());
		return builder.build().toUri();
	}

	/**
	 * API 응답을 처리해 List<PTourApi> 객체 반환
	 * @param jsonNode getTourInfo 메서드에서 생성된 JsonNode 객체
	 * @return List<PTourApi> 객체
	 */
	private Mono<List<PTourApi>> processApiResponse(JsonNode jsonNode) {
		return Mono.justOrEmpty(jsonNode)
			.flatMap(node -> {
				String resultMsg = node.path("response").path("header").path("resultMsg").asText();
				if (!"OK".equals(resultMsg)) {
					return Mono.error(new RuntimeException("API Response Error: " + resultMsg));
				}

				JsonNode itemNodes = node.path("response").path("body").path("items").path("item");
				if (!itemNodes.isArray()) {
					return Mono.error(new RuntimeException("Invalid API Response: items is not an array"));
				}

				List<PTourApi> tourList = new ArrayList<>();
				itemNodes.forEach(itemNode -> tourList.add(mapToPTourApi(itemNode)));
				return Mono.just(tourList);
			});
	}

	/**
	 * JsonNode 객체를 PTourApi dto 객체로 변환
	 * @param itemNode itemNodes.forEach 에서 생성된 JsonNode
	 * @return PTourApi 객체
	 */
	private PTourApi mapToPTourApi(JsonNode itemNode) {
		PTourApi tourApi = new PTourApi();
		tourApi.setDestinationFirstAddress(getJsonNodeText(itemNode, "addr1"));
		tourApi.setDestinationSecondAddress(getJsonNodeText(itemNode, "addr2"));
		tourApi.setAreaCode(getJsonNodeText(itemNode, "areacode"));
		tourApi.setDestinationContentId(getJsonNodeText(itemNode, "contentid"));
		tourApi.setDestinationContentTypeId(getJsonNodeText(itemNode, "contenttypeid"));
		tourApi.setDestinationFirstImageLink(getJsonNodeText(itemNode, "firstimage"));
		tourApi.setDestinationSecondImageLink(getJsonNodeText(itemNode, "firstimage2"));
		tourApi.setDestinationLongitude(getJsonNodeDouble(itemNode, "mapx"));
		tourApi.setDestinationLatitude(getJsonNodeDouble(itemNode, "mapy"));
		tourApi.setSigunguCode(getJsonNodeText(itemNode, "sigungucode"));
		tourApi.setDestinationTitle(getJsonNodeText(itemNode, "title"));
		tourApi.setDestinationTelNum(getJsonNodeText(itemNode, "tel"));
		tourApi.setDestinationZipcode(getJsonNodeText(itemNode, "zipcode"));
		return tourApi;
	}

	/**
	 * mapToPTourApi 메서드에서 JsonNode에 특정 필드의 텍스트 값을 읽어 오는 메서드
	 * @param itemNode itemNodes.forEach 에서 생성된 JsonNode
	 * @param fieldName JSON의 필드 값
	 * @return 비어 있지 않으면 json의 필드 값을 찾아 반환
	 */
	private String getJsonNodeText(JsonNode itemNode, String fieldName) {
		return itemNode.has(fieldName) ? itemNode.get(fieldName).asText(null) : null;
	}

	/**
	 * mapToPTourApi 메서드에서 JsonNode에 특정 필드의 더블 값을 읽어 오는 메서드
	 * @param itemNode itemNodes.forEach 에서 생성된 JsonNode
	 * @param fieldName JSON의 필드 값
	 * @return 비어 있지 않으면 json의 필드 값을 찾아 반환
	 */
	private Double getJsonNodeDouble(JsonNode itemNode, String fieldName) {
		return itemNode.has(fieldName) ? itemNode.get(fieldName).asDouble() : 0.0;
	}

	@Override
	public void saveData(List<PTourApi> tourInfoList) {
		for (PTourApi tourInfo : tourInfoList) {
			pTourApiMapper.insertTourInfo(tourInfo);
		}
	}
}
