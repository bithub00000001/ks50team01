package ksmart.ks50team01.chimney.chart.service;

import java.util.List;

import ksmart.ks50team01.chimney.cleansys.dto.MeasurementResultDTO;

/**
 * 차트 데이터를 위한 서비스 인터페이스
 */
public interface ChartService {

	/**
	 * 특정 사업장과 배출구의 측정 데이터를 가져오는 메서드.
	 *
	 * @param business 사업장명.
	 * @param stackCode 배출구 번호.
	 * @return 측정 데이터 목록.
	 */
	List<MeasurementResultDTO> getMeasurementsByBusinessAndStack(String business, String stackCode);

	/**
	 * 특정 지역에 속한 사업장 목록을 가져오는 메서드.
	 *
	 * @param region 지역명.
	 * @return 사업장 목록.
	 */
	List<String> getBusinessesByRegion(String region);

	/**
	 * 특정 사업장의 배출구 목록을 가져오는 메서드.
	 *
	 * @param business 사업장명.
	 * @return 배출구 목록.
	 */
	List<String> getStacksByBusiness(String business);
}
