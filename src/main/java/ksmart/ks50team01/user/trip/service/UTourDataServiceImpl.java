package ksmart.ks50team01.user.trip.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.user.trip.dto.UArea;
import ksmart.ks50team01.user.trip.dto.USigungu;
import ksmart.ks50team01.user.trip.mapper.UTourApiMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UTourDataServiceImpl implements UTourDataService {

	private final UTourApiService uTourApiService;
	private final UTourApiMapper uTourApiMapper;

	/**
	 * Tour API 에서 지역 코드 데이터를 가져와 데이터베이스에 업서트
	 * @param apiKey Tour API 디코딩 키
	 */
	@Override
	public void upsertAreaData(String apiKey){
		log.info("upsertAreaData Method called");

		// Tour API에서 지역 코드 데이터를 반환
		Mono<List<UArea>> areaDataMono = uTourApiService.getRegionData(apiKey);
		areaDataMono.subscribe(areaData -> {
			log.info("Tour API 에서 받아온 지역 코드 데이터: {}",areaData);

			// 받아온 데이터를 UArea 객체 리스트로 변환
			List<UArea> uAreas = areaData.stream()
				.map(item -> {
					UArea uArea = new UArea();
					uArea.setCode(item.getCode());
					uArea.setName(item.getName());
					return uArea;
				})
				.toList();
			log.info("변환된 UArea 객체 리스트: {}",uAreas);

			// UArea 객체 리스트를 DB에 업서트
			uAreas.forEach(uTourApiMapper::upsertArea);

			log.info("upsertAreaData Method exit");
		});
	}

	/**
	 * DB에 저장된 지역 코드를 이용하여 Tour API에서 시군구 코드 데이터를 가져와 데이터베이스에 업서트
	 * @param apiKey Tour API 디코딩 키
	 */
	@Override
	public void upsertSigunguData(String apiKey){
		log.info("upsertSigunguData Method called");

		// DB에서 모든 지역 코드를 조회
		List<UArea> uAreas = uTourApiMapper.findAllAreas();
		log.info("DB에서 조회한 지역 코드 목록: {}",uAreas);

		uAreas.forEach(area -> {
			log.info("처리 중인 지역 코드: {}",area);

			// 해당 지역 코드로 Tour API 에서 시군구 코드 데이터를 반환
			Mono<List<UArea>> sigunguDataMono = uTourApiService.getRegionData(apiKey, Optional.of(area.getCode()));
			sigunguDataMono.subscribe(sigunguDataList -> {
				log.info("Tour API에서 받아온 시군구 코드 데이터: {}",sigunguDataList);

				// 받아온 데이터를 USigungu 객체 리스트로 변환
				List<USigungu> uSigungus = sigunguDataList.stream()
					.map(item -> {
						USigungu uSigungu = new USigungu();
						// 시군구 코드
						uSigungu.setCode(item.getCode());
						uSigungu.setName(item.getName());
						// 여기에 시군구 dto에 areaCode를 설정
						uSigungu.setAreaCode(Integer.parseInt(area.getCode()));
						return uSigungu;
					})
					.toList();
				log.info("변환된 USigungu 객체 리스트: {}",uSigungus);

				// USigungu 객체 리스트를 DB에 업서트
				uSigungus.forEach(uTourApiMapper::upsertSigungu);
			});
		});
	}

}
