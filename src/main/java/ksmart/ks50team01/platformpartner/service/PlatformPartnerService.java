package ksmart.ks50team01.platformpartner.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.platformpartner.dto.PlatformPartner;
import ksmart.ks50team01.platformpartner.mapper.PlatformPartnerMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PlatformPartnerService {
	
	private final PlatformPartnerMapper platformPartnerMapper;
	
	public List<PlatformPartner> getPlatformPartnerList(){
		return platformPartnerMapper.getPlatformPartnerList();
	}

}
