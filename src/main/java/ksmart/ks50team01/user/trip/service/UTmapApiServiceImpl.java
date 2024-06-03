package ksmart.ks50team01.user.trip.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;

import ksmart.ks50team01.user.trip.dto.TMapApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UTmapApiServiceImpl implements UTmapApiService {

	private final WebClient tMapApiWebClient;

	@Value("${tmap.api.key}")
	private String tmapAppKey;

	/**
	 * T map APi에서 출발지, 출발지 위경도, 도착지, 도착지 위경도를 입력 받아 정보를 요청하는 메서드
	 * @param startName 출발지 명
	 * @param endName 도착지 명
	 * @param startX 출발지 경도
	 * @param startY 출발지 위도
	 * @param endX 도착지 경도
	 * @param endY 도착지 위도
	 * @return
	 */
	@Override
	public Mono<TMapApiResponse> fetchFromApi(
		String startName,
		String endName,
		double startX,
		double startY,
		double endX,
		double endY) {
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("reqCoordType", "WGS84GEO");
		queryParams.put("resCoordType", "EPSG3857");
		queryParams.put("startName", startName);
		queryParams.put("endName", endName);
		queryParams.put("startX", startX);
		queryParams.put("startY", startY);
		queryParams.put("endX", endX);
		queryParams.put("endY", endY);
		log.info("queryParams:{}", queryParams);
		return tMapApiWebClient.post()
			.uri(uriBuilder -> uriBuilder
				.path("/pedestrian")
				.queryParam("version",1)
				.queryParam("format","json")
				.queryParam("callback","result")
				.build())
			.bodyValue(queryParams)
			.header("appKey", tmapAppKey)
			.retrieve()
			.bodyToMono(JsonNode.class)
			.flatMap(this::parseApiResponse);
	}

	/**
	 * T map API에서 응답받은 데이터를 거리와 소요 시간으로 파싱하여 DTO에 세팅하는 메서드
	 * @param jsonNode
	 * @return
	 */
	private Mono<TMapApiResponse> parseApiResponse(JsonNode jsonNode) {
		try {
			JsonNode responseNode = jsonNode.get("features").get(0).get("properties");
			double distance = responseNode.get("totalDistance").asDouble();
			int duration = responseNode.get("totalTime").asInt();

			TMapApiResponse tMapApiResponse = new TMapApiResponse();
			tMapApiResponse.setDistance(distance);
			tMapApiResponse.setDuration(duration);
			return Mono.just(tMapApiResponse);
		} catch (Exception e) {
			return Mono.error(e);
		}
	}
}
