package ksmart.ks50team01.platform.trip.service;

import java.util.List;
import java.util.Optional;

import ksmart.ks50team01.platform.trip.dto.PTourApi;
import reactor.core.publisher.Mono;

public interface PTourApiService {
	Mono<List<PTourApi>> getAreaData(String apiKey, Optional<String> optionalAreaCode);

	Mono<List<PTourApi>> getAreaData(String apiKey);

	void upsertAreaData(String apiKey);

	void upsertSigunguData(String apiKey);

	List<PTourApi> getAreaCodeList();

	List<PTourApi> getSigunguCodeList();
}
