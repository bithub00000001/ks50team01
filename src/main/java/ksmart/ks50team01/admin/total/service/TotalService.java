package ksmart.ks50team01.admin.total.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.admin.total.mapper.TotalMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TotalService {

	private final TotalMapper totalMapper;
	
	public int getDailyLogin() {
		
		return totalMapper.getDailyLogin();
	}
	
	public int getNewMemeberTotal() {
		
		return totalMapper.getNewMemeberTotal();
	}
	
	public int getWithdrawnMemeberTotal() {
		
		return totalMapper.getWithdrawnMemeberTotal();
	}
	
}
