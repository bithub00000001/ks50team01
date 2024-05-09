package ksmart.ks50team01.platform.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.platform.board.dto.PNotice;
import ksmart.ks50team01.platform.board.mapper.PNoticeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PNoticeService {
	
	private final PNoticeMapper pNoticeMapper;
	

	
	/**
	 * 공지사항 상세 조회
	 * @return List<PNotice>
	 */
	public List<PNotice> getNoticeDetailList(){
		return pNoticeMapper.getNoticeDetailList();
	}
	
	
	
	/**
	 * 공지사항 조회
	 * @return List<PNotice>
	 */
	public List<PNotice> getNoticeList(){
		return pNoticeMapper.getNoticeList();
	}

}
