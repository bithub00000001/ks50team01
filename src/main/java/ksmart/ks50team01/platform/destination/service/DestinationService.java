package ksmart.ks50team01.platform.destination.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.platform.destination.dto.Destination;
import ksmart.ks50team01.platform.destination.mapper.DestinationMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class DestinationService {
	
	private final DestinationMapper destinationMapper;
	
	public List<Destination> getTourInfoList(){
		
		List<Destination> tourInfoList = destinationMapper.getTourInfoList();
		
		return destinationMapper.getTourInfoList();
	}
	
	public int updateTourInfo(String tourName, String tourAuthorityId) {
		
		int updateTourInfo = destinationMapper.updateTourInfo(tourName, tourAuthorityId);
		
		return updateTourInfo;
	}
	
	

}
