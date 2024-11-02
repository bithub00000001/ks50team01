package ksmart.ks50team01.chimney.chart.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.chimney.chart.mapper.ChartMapper;
import ksmart.ks50team01.chimney.cleansys.dto.MeasurementResultDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 차트 데이터를 위한 서비스 구현 클래스
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ChartServiceImpl implements ChartService {

	private final ChartMapper chartMapper;

	@Override
	public List<MeasurementResultDTO> getMeasurementsByBusinessAndStack(String business, String stackCode) {
		List<MeasurementResultDTO> results = chartMapper.getMeasurementsByBusinessAndStack(business, stackCode);
		// 로그를 통해 데이터 확인
		if (results == null || results.isEmpty()) {
			log.warn("데이터가 없습니다. 사업장: {}, 배출구: {}", business, stackCode);
		} else {
			results.forEach(result -> log.info("가져온 데이터: {}", result));
		}

		return results;
	}

	@Override
	public List<String> getBusinessesByRegion(String region) {
		return chartMapper.getBusinessesByRegion(region);
	}

	@Override
	public List<String> getStacksByBusiness(String business) {
		return chartMapper.getStacksByBusiness(business);
	}
}
