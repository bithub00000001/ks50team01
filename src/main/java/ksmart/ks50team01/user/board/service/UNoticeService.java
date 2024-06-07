package ksmart.ks50team01.user.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	 * 공지사항 조회
	 * @return List<UNotice>
	 */
	public List<UNotice> getNoticeList(){
		return uNoticeMapper.getNoticeList();
	}
	
	
	/**
	 * 공지사항 상세 조회
	 * @return UNotice
	 */
	public UNotice getNoticeDetail(String noticeNum){
		UNotice noticeDetail = uNoticeMapper.getNoticeDetail(noticeNum);
		log.info("getNoticeByNoticeNum : {}", noticeDetail);
		return noticeDetail;
	}
	

	// 조회수 증가
	public int increaseViewCount(String noticeNum) {
		return uNoticeMapper.increaseViewCount(noticeNum);
		
	}


	public Map<String, Object> getNoticeList(int currentPage) {
	    int rowPerPage = 10;

	    int startRow = (currentPage - 1) * rowPerPage;

	    Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("startRow", startRow);
	    paramMap.put("rowPerPage", rowPerPage);

	    List<Map<String, Object>> noticeList = uNoticeMapper.getNoticeListByPage(paramMap);
	    int rowCnt = uNoticeMapper.getNoticeListRowCnt();
	    
	    // 나눗셈 결과를 올바르게 처리하기 위해 double 형으로 형변환 후 올림
	    int lastPage = (int)Math.ceil((double)rowCnt / rowPerPage); 

	    int startPageNum = 1;
	    int lastPageNum = 5;

	    // 마지막 페이지가 5보다 작으면 lastPage로 설정
	    lastPageNum = Math.min(lastPage, lastPageNum); 

	    if (currentPage > 6 && lastPage > 9) {
	        startPageNum = currentPage - 5;
	        lastPageNum = currentPage + 4;
	        if (lastPageNum >= lastPage) {
	            startPageNum = lastPage - 9;
	            lastPageNum = lastPage;
	        }
	    }

	    Map<String, Object> resultMap = new HashMap<>();
	    resultMap.put("noticeList", noticeList);
	    resultMap.put("lastPage", lastPage);
	    resultMap.put("startPageNum", startPageNum);
	    resultMap.put("lastPageNum", lastPageNum);

	    return resultMap;
	}



}
