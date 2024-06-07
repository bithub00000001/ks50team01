package ksmart.ks50team01.user.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.user.board.dto.UCategory;
import ksmart.ks50team01.user.board.dto.UQna;
import ksmart.ks50team01.user.board.mapper.UQnaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UQnaService {
	
	private final UQnaMapper uQnaMapper;
	
	/**
	 * 1:1문의 조회
	 * @return List<UQna>
	 */
	public List<UQna> getQnaList(){
		List<UQna> qnaList = uQnaMapper.getQnaList();
		log.info("1:1문의 조회: {}", qnaList);
		return qnaList;
	}

	
	/**
	 * 1:1문의 카테고리 조회
	 * @return qnaCateList
	 */
	public List<UCategory> getQnaCateList() {
		List<UCategory> qnaCateList = uQnaMapper.getQnaCateList();
		
		log.info("QNA 카테고리 조회 결과: {}", qnaCateList);
		return qnaCateList;
	}

	
	/**
	 * 1:1문의 상세 조회
	 * @return UQna
	 */
	public UQna getQnaDetail(String qnaNum) {
		UQna qnaDetail = uQnaMapper.getQnaDetail(qnaNum);
		log.info("getQnaByQnaNum: {}", qnaDetail);
		return qnaDetail;
	}
	
	
	/**
	 * 해당 번호의 1:1문의 조회
	 * @param qnaNum 조회할 1:1문의 번호
	 * @return 조회된 uQna 객체, 없을 경우 null
	 */
	public UQna getQnaInfoByNum(String qnaNum) {
		return uQnaMapper.getQnaInfoByNum(qnaNum);
	}
	
	

    /**
     * 1:1문의 등록
     */
	public void qnaAdd(UQna uQna) {
		uQnaMapper.qnaAdd(uQna);
		
	}


    /**
     * 1:1문의 수정
     * @param uQna 수정된 1:1문의 정보
     */
	public void qnaModify(UQna uQna) {
		uQnaMapper.qnaModify(uQna);
		
	}
	
	
    /**
     * 1:1문의 삭제
     * @param qnaNum 삭제할 1:1문의 번호
     */
	public void qnaRemove(String qnaNum) {
		uQnaMapper.qnaRemove(qnaNum);
		
	}


	public Map<String, Object> getQnaList(int currentPage) {
	    int rowPerPage = 3;

	    int startRow = (currentPage - 1) * rowPerPage;

	    Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("startRow", startRow);
	    paramMap.put("rowPerPage", rowPerPage);

	    List<Map<String, Object>> qnaList = uQnaMapper.getQnaListByPage(paramMap);
	    int rowCnt = uQnaMapper.getQnaListRowCnt();
	    
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
	    resultMap.put("qnaList", qnaList);
	    resultMap.put("lastPage", lastPage);
	    resultMap.put("startPageNum", startPageNum);
	    resultMap.put("lastPageNum", lastPageNum);

	    return resultMap;
	}

	
}
