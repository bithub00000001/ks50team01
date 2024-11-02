package ksmart.ks50team01.chimney.cleansys.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import jakarta.annotation.PostConstruct;
import ksmart.ks50team01.chimney.cleansys.dto.MeasurementResponseDTO;
import ksmart.ks50team01.chimney.cleansys.dto.MeasurementResultDTO;
import ksmart.ks50team01.chimney.cleansys.mapper.MeasurementResultMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

/**
 * 실시간 측정 결과 서비스 구현 클래스
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MeasurementServiceImpl implements MeasurementService {

	private final MeasurementResultMapper measurementResultMapper;
	private final WebClient cleanWebClient;

	@Value("${tour.api.key}")
	private String serviceKey;

	// 지역 목록 설정 (예시)
	private static final List<String> TARGET_AREAS = List.of("충청북도", "충청남도", "세종", "대전광역시");
	// 날짜 포맷터 설정
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	/**
	 * 서버 시작 시 자동으로 데이터 fetch 및 저장을 트리거하는 메서드
	 */
	@PostConstruct
	public void init() {
		log.info("서버 시작 시 초기 데이터 fetch 및 저장을 트리거합니다.");
		fetchAndStoreMeasurementData();
	}

	/**
	 * 매시 12분, 42분에 실행 (데이터 갱신 시점 10분, 40분 이후 2분 대기)
	 */
	@Override
	@Scheduled(cron = "0 8,12,38,42 * * * *")  // 8,12분과 38,42분에 실행
	public void fetchAndStoreMeasurementData() {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime targetTime = calculateTargetTime(now);

		log.info("데이터 수집 시작 - 현재시각: {}, 목표 측정시각: {}",
			now.format(FORMATTER), targetTime.format(FORMATTER));

		Flux.fromIterable(TARGET_AREAS)
			.doOnNext(area -> log.info("지역 데이터 수집 시작 - 지역: {}, 측정시간: {}",
				area, targetTime.format(FORMATTER)))
			.parallel()
			.runOn(reactor.core.scheduler.Schedulers.boundedElastic())
			.flatMap(area -> fetchDataForArea(area)
				.doOnError(error -> log.error("지역 {} 데이터 수집 실패: {}", area, error.getMessage()))
				// 1분 간격으로 2회 재시도
				.retryWhen(Retry.backoff(2, Duration.ofMinutes(1))
					.doBeforeRetry(signal -> log.warn("지역 {} 데이터 수집 재시도 #{}",
						area, signal.totalRetries() + 1))))
			.sequential()
			.subscribe(
				this::saveMeasurementResult,
				error -> log.error("데이터 수집 중 오류 발생: {}", error.getMessage()),
				() -> log.info("전체 지역 데이터 수집 완료 - 측정시각: {}", targetTime.format(FORMATTER))
			);
	}

	/**
	 * 현재 시간에 따른 목표 측정 시간 계산
	 * 8분, 12분 실행 -> 이전 시간의 정각 데이터
	 * 38분, 42분 실행 -> 현재 시간의 30분 데이터
	 */
	private LocalDateTime calculateTargetTime(LocalDateTime now) {
		int minute = now.getMinute();
		if (minute >= 38) {
			return now.withMinute(0);  // 같은 시간의 00분 데이터
		} else if (minute >= 8) {
			return now.minusHours(1).withMinute(30);  // 이전 시간의 30분 데이터
		} else {
			return now.minusHours(1).withMinute(0);  // 이전 시간의 00분 데이터
		}
	}

	private Flux<MeasurementResultDTO> fetchDataForArea(String area) {
		return cleanWebClient.get()
			.uri(uriBuilder -> uriBuilder
				.scheme("http")
				.host("apis.data.go.kr")
				.path("/B552584/cleansys/rltmMesureResult")
				.queryParam("serviceKey", serviceKey)
				.queryParam("areaNm", area)
				.queryParam("type", "json")
				.build())
			.retrieve()
			.bodyToMono(MeasurementResponseDTO.class)
			.timeout(Duration.ofSeconds(30))  // 타임아웃 30초 설정
			.flatMapMany(response -> validateAndExtractData(response, area))
			.onErrorResume(e -> {
				log.error("지역 {} 데이터 fetch 실패: {}", area, e.getMessage());
				return Flux.empty();
			});
	}

	/**
	 * API 응답 데이터 검증 및 추출
	 */
	private Flux<MeasurementResultDTO> validateAndExtractData(MeasurementResponseDTO response, String area) {
		if (response != null && response.getResponse() != null &&
			response.getResponse().getBody() != null &&
			response.getResponse().getBody().getItems() != null) {

			List<MeasurementResultDTO> items = response.getResponse().getBody().getItems();
			if (items.isEmpty()) {
				log.warn("지역 {} 데이터 없음", area);
				return Flux.empty();
			}
			return Flux.fromIterable(items);
		} else {
			log.warn("지역 {} API 응답 데이터 형식 오류", area);
			return Flux.empty();
		}
	}

	private void saveMeasurementResult(MeasurementResultDTO result) {
		Optional.ofNullable(result)
			.filter(r -> r.getAreaNm() != null)
			.filter(r -> r.getFactManageNm() != null)
			.filter(r -> r.getStackCode() != null)
			.ifPresentOrElse(
				r -> {
					try {
						measurementResultMapper.insertMeasurementResult(r);
						log.debug("데이터 저장 성공: 지역={}, 시설={}, 배출구={}",
							r.getAreaNm(), r.getFactManageNm(), r.getStackCode());
					} catch (Exception e) {
						log.error("데이터 저장 실패: {}", e.getMessage());
					}
				},
				() -> log.warn("필수 값이 누락되었습니다.")
			);
	}
}

