package ksmart.ks50team01.platform.trip.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.platform.trip.dto.PTourApi;

@Mapper
public interface PTourApiMapper {

	int upsertAreaCode(PTourApi pTourApi);

	List<PTourApi> getAreaCodeList();

	List<PTourApi> getSigunList();

	int upsertSigunguCode(PTourApi pTourApi);
}
