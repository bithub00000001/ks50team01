package ksmart.ks50team01.user.trip.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

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
