package ksmart.ks50team01.user.trip.service;

import ksmart.ks50team01.user.trip.dto.TMapApiResponse;
import reactor.core.publisher.Mono;

public interface UTmapApiService {
	Mono<TMapApiResponse> fetchFromApi(
		String startName,
		String endName,
		double startX,
		double startY,
		double endX,
		double endY);
}
