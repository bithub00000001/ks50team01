package ksmart.ks50team01.platform.trip.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import com.fasterxml.jackson.databind.JsonNode;

import ksmart.ks50team01.platform.trip.dto.PTourApi;
import ksmart.ks50team01.platform.trip.dto.PTourDetail;
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
	private final WebClient tourApiWebClient;

	// API 호출 후 응답을 처리하는 일반화된 메서드
	private <T> Mono<List<T>> fetchFromApi(String apiKey, String path, Map<String, String> queryParams, Class<T> responseClass, Optional<String> optionalAreaCode) {
		return tourApiWebClient.get()
			.uri(uriBuilder -> buildUri(uriBuilder, path, queryParams, apiKey))
			.retrieve()
			.bodyToMono(JsonNode.class)
			.flatMap(jsonNode -> processApiResponse(jsonNode, responseClass, optionalAreaCode, path))
			.onErrorResume(error -> {
				log.error("Error occurred while fetching data: {}", error.getMessage());
				return Mono.error(error);
			});
	}
	// URI 생성 메서드
	private URI buildUri(UriBuilder uriBuilder, String path, Map<String, String> queryParams, String apiKey) {
		UriBuilder builder = uriBuilder
			.path(path)
			.queryParam("ServiceKey", apiKey)
			.queryParam("MobileOS", "ETC")
			.queryParam("MobileApp", "AppTest")
			.queryParam("_type", "json");
		queryParams.forEach(builder::queryParam);
		log.info("url: {}", builder.toUriString());
		return builder.build();
	}

	// API 응답 처리 메서드
	private <T> Mono<List<T>> processApiResponse(JsonNode jsonNode, Class<T> responseClass, Optional<String> optionalAreaCode, String path) {
		return Mono.justOrEmpty(jsonNode)
			.flatMap(node -> {
				String resultMsg = node.path("response").path("header").path("resultMsg").asText();
				if (!"OK".equals(resultMsg)) {
					return Mono.error(new RuntimeException("API Response Error: " + resultMsg));
				}

				JsonNode itemsNode = node.path("response").path("body").path("items").path("item");
				if (!itemsNode.isArray()) {
					return Mono.error(new RuntimeException("Invalid API Response: items is not an array"));
				}

				List<T> responseList = new ArrayList<>();
				itemsNode.forEach(itemNode -> {
					List<T> mappedObjects;
					if ("/areaCode1".equals(path)) {
						mappedObjects = mapToAreaDataResponseObject(itemNode, responseClass, optionalAreaCode);
					} else {
						mappedObjects = mapToResponseObject(itemNode, responseClass);
					}
					if (mappedObjects != null) {
						responseList.addAll(mappedObjects);
					}
				});
				return Mono.just(responseList);
			});
	}

	// "/areaCode1" 경로에서 호출할 데이터 변환 메서드
	private <T> List<T> mapToAreaDataResponseObject(JsonNode itemNode, Class<T> responseClass, Optional<String> optionalAreaCode) {
		return optionalAreaCode.map(areaCode -> createSigunguDataListFromJson(itemNode, areaCode).stream()
			.map(responseClass::cast)
			.toList()).orElseGet(() -> createAreaDataListFromJson(itemNode).stream()
			.map(responseClass::cast)
			.toList());
	}

	// JsonNode를 응답 객체로 변환하는 메서드
	private <T> List<T> mapToResponseObject(JsonNode itemNode, Class<T> responseClass) {
		if (responseClass == PTourApi.class) {
			List<T> result = new ArrayList<>();
			result.add(responseClass.cast(mapToPTourApi(itemNode)));
			return result;
		} else if (responseClass == PTourDetail.class) {
			return Collections.singletonList(responseClass.cast(mapToPTourDetail(itemNode)));
		}
		return Collections.emptyList();
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
	 * JsonNode 객체를 PTourDetail dto 객체로 변환
	 * @param itemNode itemNodes.forEach 에서 생성된 JsonNode
	 * @return PTourDetail 객체
	 */
	private PTourDetail mapToPTourDetail(JsonNode itemNode) {
		PTourDetail tourDetail = new PTourDetail();
		tourDetail.setContentId(getJsonNodeText(itemNode, "contentid"));
		tourDetail.setContentTypeId(getJsonNodeText(itemNode, "contenttypeid"));
		tourDetail.setTitle(getJsonNodeText(itemNode, "title"));
		tourDetail.setTel(getJsonNodeText(itemNode, "tel"));
		tourDetail.setHomepage(getJsonNodeText(itemNode, "homepage"));
		tourDetail.setFirstImage(getJsonNodeText(itemNode, "firstimage"));
		tourDetail.setSecondImage(getJsonNodeText(itemNode, "firstimage2"));
		tourDetail.setMapLevel(getJsonNodeText(itemNode, "mlevel"));
		return tourDetail;
	}

	/**
	 * JsonNode에서 필드 값을 텍스트로 가져오는 메서드
	 * @param itemNode JsonNode 객체
	 * @param fieldName 가져올 필드 이름
	 * @return 필드 값 텍스트
	 */
	private String getJsonNodeText(JsonNode itemNode, String fieldName) {
		return itemNode.has(fieldName) ? itemNode.get(fieldName).asText(null) : null;
	}


	/**
	 * JsonNode에서 필드 값을 Double로 가져오는 메서드
	 * @param itemNode JsonNode 객체
	 * @param fieldName 가져올 필드 이름
	 * @return 필드 값 Double
	 */
	private Double getJsonNodeDouble(JsonNode itemNode, String fieldName) {
		return itemNode.has(fieldName) ? itemNode.get(fieldName).asDouble() : 0.0;
	}

	/**
	 * JsonNode 객체를 PTourApi dto 리스트 객체로 변환
	 * @param itemsNode JsonNode 객체
	 * @param areaCode 해당 지역 코드
	 * @return PTourApi 객체 리스트
	 */
	private List<PTourApi> createSigunguDataListFromJson(JsonNode itemsNode, String areaCode) {
		if (itemsNode.isEmpty()) {
			return Collections.emptyList();
		}
		PTourApi pTourApi = new PTourApi();
		pTourApi.setSigunguCode(getJsonNodeText(itemsNode, "code"));
		pTourApi.setSigunguName(getJsonNodeText(itemsNode, "name"));
		pTourApi.setAreaCode(areaCode);

		return Collections.singletonList(pTourApi);
	}

	/**
	 * JsonNode 객체를 PTourApi dto 리스트 객체로 변환
	 * @param itemsNode JsonNode 객체
	 * @return PTourApi 객체 리스트
	 */
	private List<PTourApi> createAreaDataListFromJson(JsonNode itemsNode) {
		if (itemsNode.isEmpty()) {
			return Collections.emptyList();
		}

		PTourApi pTourApi = new PTourApi();
		pTourApi.setAreaCode(getJsonNodeText(itemsNode, "code"));
		pTourApi.setAreaName(getJsonNodeText(itemsNode, "name"));
		pTourApi.setAreaRNum(getJsonNodeText(itemsNode, "rnum"));

		return Collections.singletonList(pTourApi);
	}


	@Override
	public Mono<List<PTourApi>> getAreaData(String apiKey, Optional<String> optionalAreaCode) {
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("numOfRows", "50");
		queryParams.put("pageNo", "1");
		optionalAreaCode.ifPresent(areaCode -> queryParams.put("areaCode", areaCode));

		return fetchFromApi(apiKey, "/areaCode1", queryParams, PTourApi.class, optionalAreaCode);
	}

	@Override
	public Mono<List<PTourApi>> getAreaData(String apiKey) {
		return getAreaData(apiKey, Optional.empty());
	}

	@Override
	public void upsertAreaData(String apiKey) {
		log.info("upsertAreaData apiKey: {}", apiKey);

		Mono<List<PTourApi>> areaDataMono = getAreaData(apiKey);
		areaDataMono.subscribe(areaData -> {
			log.info("Tour API 지역 코드 데이터 조회: {}", areaData);

			areaData.forEach(pTourApiMapper::upsertAreaCode);

			log.info("=== upsertAreaData Method exit ===");
		});
	}

	@Override
	public void upsertSigunguData(String apiKey) {
		log.info("upsertSigunguData apiKey: {}", apiKey);

		List<PTourApi> areaCodeList = pTripPlanService.getAreaCodeList();

		areaCodeList.forEach(areaCode -> {
			Optional<String> areaCodeValue = Optional.ofNullable(areaCode.getAreaCode());

			Mono<List<PTourApi>> sigunguDataMono = getAreaData(apiKey, areaCodeValue);
			sigunguDataMono.subscribe(sigunguData -> {
				log.info("Tour API 시군 코드 데이터 조회: {}", sigunguData);
				sigunguData.forEach(pTourApiMapper::upsertSigunguCode);

				log.info("=== upsertSigunguData Method exit ===");
			});
		});
	}

	@Override
	public Mono<List<PTourApi>> getTourInfo(String apiKey, int contentTypeId, int numOfRows, int pageNo,
		String areaCode, Optional<String> optionalSigunguCode) {
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("numOfRows", String.valueOf(numOfRows));
		queryParams.put("pageNo", String.valueOf(pageNo));
		queryParams.put("contentTypeId", String.valueOf(contentTypeId));
		queryParams.put("areaCode", areaCode);
		optionalSigunguCode.ifPresent(sigunguCode -> queryParams.put("sigunguCode", sigunguCode));

		return fetchFromApi(apiKey, "/areaBasedList1", queryParams, PTourApi.class, Optional.empty());
	}

	@Override
	public Mono<PTourDetail> getTourDetail(String apiKey, String contentId, String contentTypeId, Map<String, String> optionalParams) {
		Map<String, String> queryParams = new HashMap<>(optionalParams);
		queryParams.put("contentId", contentId);
		queryParams.put("contentTypeId", contentTypeId);

		return fetchFromApi(apiKey, "/detailCommon1", queryParams, PTourDetail.class, Optional.empty())
			.flatMap(details -> details.isEmpty() ? Mono.empty() : Mono.just(details.get(0)));
	}

	@Override
	public void upsertTourInfoList(List<PTourApi> tourInfoList) {
		for (PTourApi tourInfo : tourInfoList) {
			pTourApiMapper.upsertTourInfo(tourInfo);
		}
	}

}
