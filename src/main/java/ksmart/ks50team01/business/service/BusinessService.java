package ksmart.ks50team01.business.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ksmart.ks50team01.business.dto.Business;
import ksmart.ks50team01.business.mapper.BusinessMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusinessService {

	private final BusinessMapper businessMapper;
	
	public List<Business> getBusinessList(){
		return businessMapper.getBusinessList();
	}
}
