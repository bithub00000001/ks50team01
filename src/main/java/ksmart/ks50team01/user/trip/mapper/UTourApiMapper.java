package ksmart.ks50team01.user.trip.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.user.trip.dto.UArea;
import ksmart.ks50team01.user.trip.dto.USigungu;

@Mapper
public interface UTourApiMapper {
	void upsertArea(UArea uArea);

	List<UArea> findAllAreas();

	void upsertSigungu(USigungu uSigungu);
}
