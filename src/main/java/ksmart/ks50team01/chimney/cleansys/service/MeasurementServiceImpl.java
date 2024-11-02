package ksmart.ks50team01.chimney.cleansys.service;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
	 * 5분마다 API 데이터를 fetch하여 DB에 저장
	 */
	@Override
	@Scheduled(fixedRate = 300000) // 5분
	public void fetchAndStoreMeasurementData() {
		log.info("데이터 fetch 및 저장 작업 시작");
		Flux.fromIterable(TARGET_AREAS)
			.doOnNext(area -> log.info("지역명: {}", area)) // 각 지역명을 로그로 출력
			.parallel()
			.runOn(reactor.core.scheduler.Schedulers.boundedElastic())
			.flatMap(this::fetchDataForArea)
			.sequential()
			.subscribe(
				this::saveMeasurementResult,
				error -> log.error("데이터 fetch 중 오류 발생: {}", error.getMessage())
			);
	}

	/**
	 * 특정 지역에 대한 데이터를 API에서 가져오는 메서드
	 *
	 * @param area 지역명
	 * @return MeasurementResultDTO 객체의 Flux
	 */
	private Flux<MeasurementResultDTO> fetchDataForArea(String area) {
		log.info("API 호출 - 지역명: {}", area); // 지역명 출력
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
			.retryWhen(Retry.backoff(3, Duration.ofSeconds(2))
				.filter(throwable -> {
					log.warn("재시도 조건에 맞는 에러 발생: {}", throwable.getMessage());
					return true; // 모든 예외에 대해 재시도 수행하도록 설정 가능 (필요 시 조건 추가)
				}))
			.flatMapMany(response -> {
				if (response != null && response.getResponse() != null &&
					response.getResponse().getBody() != null &&
					response.getResponse().getBody().getItems() != null) {
					return Flux.fromIterable(response.getResponse().getBody().getItems());
				} else {
					log.warn("API 응답에 데이터가 없습니다.");
					return Flux.empty();
				}
			})
			.onErrorResume(e -> {
				log.error("지역 {} 데이터 fetch 실패: {}", area, e.getMessage());
				return Flux.empty();
			});
	}

	/**
	 * 측정 결과를 데이터베이스에 저장하는 메서드
	 *
	 * @param result 측정 결과 DTO
	 */
	private void saveMeasurementResult(MeasurementResultDTO result) {
		if (result != null) {
			try {
				// LocalDateTime으로 변환된 mesureDt가 이미 존재하므로 변환 불필요 (Jackson이 자동 변환)
				measurementResultMapper.insertMeasurementResult(result);
			} catch (Exception e) {
				log.error("데이터 저장 중 오류 발생: {}", e.getMessage());
			}
		} else {
			log.warn("필수 값 누락: areaNm={}, factManageNm={}, stackCode={}", result.getAreaNm(), result.getFactManageNm(), result.getStackCode());
		}
	}
}

