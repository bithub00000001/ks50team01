package ksmart.ks50team01.user.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.user.board.dto.UNotice;
import ksmart.ks50team01.user.board.mapper.UNoticeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UNoticeService {
	
	private final UNoticeMapper uNoticeMapper;
	
	/**
	 * 공지사항 상세 조회
	 * @return UNotice
	 */
	public UNotice getNoticeByNoticeNum(String noticeNum){
		UNotice noticeDetail = uNoticeMapper.getNoticeByNoticeNum(noticeNum);
		log.info("getNoticeByNoticeNum : {}", noticeDetail);
		return noticeDetail;
	}
	
	
	
	/**
	 * 공지사항 조회
	 * @return List<UNotice>
	 */
	public List<UNotice> getNoticeList(){
		return uNoticeMapper.getNoticeList();
	}
	
	

}
