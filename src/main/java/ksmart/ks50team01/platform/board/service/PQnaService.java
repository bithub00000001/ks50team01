package ksmart.ks50team01.platform.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.platform.board.dto.PAnswer;
import ksmart.ks50team01.platform.board.dto.PQna;
import ksmart.ks50team01.platform.board.mapper.PAnswerMapper;
import ksmart.ks50team01.platform.board.mapper.PQnaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PQnaService {
	
	private final PQnaMapper pQnaMapper;
	private final PAnswerMapper pAnswerMapper;
	
	

	/**
	 * 1:1문의 조회
	 * @return List<PQna>
	 */
	public List<PQna> getQnaList(){
		List<PQna> qnaList = pQnaMapper.getQnaList();
		
		log.info("1:1문의 조회 결과: {}", qnaList);
		
	    // 각 1:1문의에 대해 답변 리스트를 조회하고 설정
	    for (PQna pQna : qnaList) {
	        List<PAnswer> answerList = pAnswerMapper.getAnswerListByQnaNum(pQna.getQnaNum());
	        pQna.setAnswerList(answerList);
	        log.info("문의 번호 {}의 답변 조회 결과: {}", pQna.getQnaNum(), answerList);
	    }
		
		return qnaList;
	}



	// 1:1문의 번호로 1:1문의 상세 조회																
	public PQna getQnaDetailByNum(String qnaNum) {
		PQna qnaDetail = pQnaMapper.getQnaDetailByNum(qnaNum);
		return qnaDetail;
	}

	
	// 1:1문의 답변 작성
    public void answerSave(String qnaNum, String ansRegId, String ansContent) {
        // 질문 테이블 업데이트
        pQnaMapper.updateQnaWithAnswererId(qnaNum, ansRegId);

        // 답변 테이블 삽입
        PAnswer pAnswer = new PAnswer();
        pAnswer.setQnaNum(qnaNum);
        pAnswer.setAnsContent(ansContent);
        pQnaMapper.answerSave(pAnswer);
    }


    // 카테고리별 1:1문의 목록 조회	
	public List<PQna> getQnaListByCategory(String category) {
		List<PQna> qnaList = pQnaMapper.getQnaListByCategory(category);
		return qnaList;
	}
}
