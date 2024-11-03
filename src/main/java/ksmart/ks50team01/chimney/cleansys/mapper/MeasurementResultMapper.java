package ksmart.ks50team01.chimney.cleansys.mapper;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.chimney.cleansys.dto.MeasurementResultDTO;

/**
 * 실시간 측정 결과를 데이터베이스에 매핑하기 위한 Mapper 인터페이스
 */
@Mapper
public interface MeasurementResultMapper {

	/**
	 * 측정 결과를 삽입하거나 중복 시 업데이트하는 메서드
	 *
	 * @param measurementResult 삽입 또는 업데이트할 측정 결과 DTO
	 */
	void insertMeasurementResult(MeasurementResultDTO measurementResult);

	/**
	 * db의 모든 값을 삭제하는 메서드
	 */
	void deleteAllMeasurementResults();
}
