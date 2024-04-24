package ksmart.ks50team01.business.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.business.dto.Business;

@Mapper
public interface BusinessMapper {

	List<Business> getBusinessList();
}
