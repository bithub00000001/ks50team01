package ksmart.ks50team01.chimney.chart.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ksmart.ks50team01.chimney.chart.service.ChartService;
import ksmart.ks50team01.chimney.cleansys.dto.MeasurementResultDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 차트 데이터를 제공하는 컨트롤러 클래스.
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class ChartController {

	private final ChartService chartService;

	/**
	 * 특정 사업장과 배출구의 측정 데이터를 반환하는 API 엔드포인트.
	 *
	 * @param business 사업장명 (RequestParam).
	 * @param stackCode 배출구 번호 (RequestParam).
	 * @return 측정 데이터 목록 (JSON).
	 */
	@GetMapping("/api/measurements")
	@ResponseBody
	public List<MeasurementResultDTO> getMeasurements(@RequestParam String business,
		@RequestParam String stackCode) {
		try {
			List<MeasurementResultDTO> mesurementsResult = chartService.getMeasurementsByBusinessAndStack(business, stackCode);
			log.info(mesurementsResult.toString());
			return mesurementsResult;
		} catch (Exception e) {
			log.error("측정 데이터 조회 중 오류 발생: {}", e.getMessage());
			throw e; // 에러 발생 시 로그 출력 후 예외 발생.
		}
	}

	/**
	 * 특정 지역에 속한 사업장 목록을 반환하는 API 엔드포인트.
	 *
	 * @param region 지역명 (RequestParam).
	 * @return 사업장 목록 (JSON).
	 */
	@GetMapping("/api/businesses")
	@ResponseBody
	public List<String> getBusinesses(@RequestParam String region) {
		try {
			return chartService.getBusinessesByRegion(region);
		} catch (Exception e) {
			log.error("사업장 목록 조회 중 오류 발생: {}", e.getMessage());
			throw e; // 에러 발생 시 로그 출력 후 예외 발생.
		}
	}

	/**
	 * 특정 사업장의 배출구 목록을 반환하는 API 엔드포인트.
	 *
	 * @param business 사업장명 (RequestParam).
	 * @return 배출구 목록 (JSON).
	 */
	@GetMapping("/api/stacks")
	@ResponseBody
	public List<String> getStacks(@RequestParam String business) {
		try {
			return chartService.getStacksByBusiness(business);
		} catch (Exception e) {
			log.error("배출구 목록 조회 중 오류 발생: {}", e.getMessage());
			throw e; // 에러 발생 시 로그 출력 후 예외 발생.
		}
	}

	/**
	 * 차트 페이지를 반환하는 메서드
	 *
	 * @param model 모델 객체 (필요시 데이터 추가 가능)
	 * @return chart.html 템플릿
	 */
	@GetMapping("/chart")
	public String getChartPage(Model model) {
		log.info("차트 페이지 호출");
		return "chimney/chart"; // templates/chimney/chart.html 경로의 템플릿을 반환
	}
}
