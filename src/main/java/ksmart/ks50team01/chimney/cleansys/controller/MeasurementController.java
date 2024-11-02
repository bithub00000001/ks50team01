package ksmart.ks50team01.chimney.cleansys.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ksmart.ks50team01.chimney.cleansys.service.MeasurementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 실시간 측정 결과 관련 컨트롤러
 */
@RestController
@Slf4j
@RequiredArgsConstructor
public class MeasurementController {

	private final MeasurementService measurementService;

	/**
	 * 수동으로 데이터 fetch 및 저장을 트리거하는 엔드포인트
	 *
	 * @return 실행 결과 메시지
	 */
	@GetMapping("/fetch-measurements")
	public String fetchMeasurements() {
		try {
			measurementService.fetchAndStoreMeasurementData();
			return "데이터 fetch 및 저장을 완료했습니다.";
		} catch (Exception e) {
			log.error("데이터 fetch 중 오류 발생: {}", e.getMessage());
			return "데이터 fetch 중 오류가 발생했습니다.";
		}
	}
}
