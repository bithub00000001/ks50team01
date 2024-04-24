package ksmart.ks50team01.tripinfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.tripinfo.dto.TripInfo;

@Mapper
public interface TripInfoMapper {
	
	List<TripInfo> getTripInfo(); 

}
