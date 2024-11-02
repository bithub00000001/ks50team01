package ksmart.ks50team01.chimney.cleansys.service;

import ksmart.ks50team01.chimney.cleansys.dto.MeasurementResultDTO;

/**
 * 실시간 측정 결과 서비스를 위한 인터페이스
 */
public interface MeasurementService {
	/**
	 * API 데이터를 가져와 DB에 저장하는 메서드
	 */
	void fetchAndStoreMeasurementData();
}
