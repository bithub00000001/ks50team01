package ksmart.ks50team01.user.destination.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.user.destination.dto.UDestination;

@Mapper
public interface UDestinationMapper {

	List<UDestination> getTourInfoList();

}
