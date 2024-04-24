package ksmart.ks50team01.tripinfo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.tripinfo.dto.TripInfo;
import ksmart.ks50team01.tripinfo.mapper.TripInfoMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TripInfoService {
	
	private final TripInfoMapper tripInfoMapper;
	
	public List<TripInfo> getTripInfo(){
		
		return tripInfoMapper.getTripInfo();
	}

}
