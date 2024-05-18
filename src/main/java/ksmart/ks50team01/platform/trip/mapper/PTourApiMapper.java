package ksmart.ks50team01.platform.trip.mapper;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.platform.trip.dto.PTourApi;

@Mapper
public interface PTourApiMapper {
	// 지역 코드 업서트
	int upsertAreaCode(PTourApi pTourApi);

	// 시군 코드 업서트
	int upsertSigunguCode(PTourApi pTourApi);

	// 여행지 정보 업서트
	void upsertTourInfo(PTourApi tourInfo);

	// 여행지 세부 정보 업서트
}
