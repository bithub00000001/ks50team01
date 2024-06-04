package ksmart.ks50team01.user.trip.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.user.trip.dto.UArea;
import ksmart.ks50team01.user.trip.dto.USigungu;

@Mapper
public interface UTourApiMapper {
	// 지역 코드 업서트 메서드
	void upsertArea(UArea uArea);

	// 모든 지역 코드 조회
	List<UArea> findAllAreas();

	// 시군구 코드 업서트 메서드
	void upsertSigungu(USigungu uSigungu);
}
