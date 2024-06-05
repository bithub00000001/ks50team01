package ksmart.ks50team01.user.destination.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.platform.trip.dto.PTourDetail;


@Mapper
public interface UDestinationMapper {

	int getTourListRowCnt();

	List<Map<String, Object>> getTourListByPage(Map<String, Object> paramMap);

	PTourDetail getDestinationDetail(String contentId);

}
