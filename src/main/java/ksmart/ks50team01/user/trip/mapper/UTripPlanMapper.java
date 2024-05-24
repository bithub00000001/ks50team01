package ksmart.ks50team01.user.trip.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UTripPlanMapper {
	// JSON 객체를 하나의 String으로 합친 목록을 반환
	List<String> getTourInfoObject(String colName, String content);
}
