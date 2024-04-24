package ksmart.ks50team01.platformpartner.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.platformpartner.dto.PlatformPartner;

@Mapper
public interface PlatformPartnerMapper {
	
	List<PlatformPartner> getPlatformPartnerList();

}
