package ksmart.ks50team01.platform.trip.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.platform.trip.dto.PTripPlan;

@Mapper
public interface PTripPlanMapper {

	List<PTripPlan> getPlanList();
}
