package ksmart.ks50team01.chimney.chart.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import ksmart.ks50team01.chimney.cleansys.dto.MeasurementResultDTO;

/**
 * 차트 데이터를 위한 Mapper 인터페이스
 */
@Mapper
public interface ChartMapper {

	/**
	 * 특정 사업장과 배출구의 측정 데이터를 가져오는 메서드
	 *
	 * @param business  사업장명
	 * @param stackCode 배출구 번호
	 * @return 측정 데이터 목록
	 */
	List<MeasurementResultDTO> getMeasurementsByBusinessAndStack(@Param("business") String business,
		@Param("stackCode") String stackCode);

	/**
	 * 데이터베이스에서 지역 목록을 가져오는 메서드
	 *
	 * @return 지역 목록
	 */
	List<String> getRegions();

	/**
	 * 특정 지역에 해당하는 사업장 목록을 가져오는 메서드
	 *
	 * @param region 지역명
	 * @return 사업장 목록
	 */
	List<String> getBusinessesByRegion(@Param("region") String region);

	/**
	 * 특정 사업장의 배출구 목록을 가져오는 메서드.
	 *
	 * @param business 사업장명.
	 * @return 배출구 목록.
	 */
	List<String> getStacksByBusiness(@Param("business") String business);
}
