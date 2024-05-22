package ksmart.ks50team01.platform.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.platform.board.dto.PCategory;
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
	 * 공지사항 수정
	 * @param pNotice
	 */
	public void noticeModify(PNotice pNotice) {
		pNoticeMapper.noticeModify(pNotice);
	}
	
	/**
	 * 공지사항 카테고리 조회
	 * @return List<PCategory>
	 */
	
	public List<PCategory> getNoticeCateList() {
		List<PCategory> noticeCateList = pNoticeMapper.getNoticeCateList();
		
		log.info("공지사항 카테고리 조회 결과: {}", noticeCateList);
		return noticeCateList;
	}

	
	/**
	 * 주어진 번호에 해당하는 공지사항 조회
	 * @param noticeNum
	 */
	public PNotice getNoticeInfoByNum(String noticeNum) {
		return pNoticeMapper.getNoticeInfoByNum(noticeNum);
	}
	


	// db에 저장
	public void insertNotice(PNotice pNotice) {
		pNoticeMapper.insertNotice(pNotice);
		
	}
	
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



	/**
	 * 공지사항 삭제
	 */
	public void noticeDelete(String noticeNum) {
		pNoticeMapper.noticeDelete(noticeNum);
	}






}
