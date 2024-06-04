package ksmart.ks50team01.user.destination.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.user.destination.dto.UDestination;
import ksmart.ks50team01.user.destination.mapper.UDestinationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UDestinationService {
	
	private final UDestinationMapper udestinationMapper;
	
	public List<UDestination> getTourInfoList() {
		List<UDestination> tourInfoList = udestinationMapper.getTourInfoList();
		
		return udestinationMapper.getTourInfoList();
	}

}
