package ksmart.ks50team01.platform.board.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.platform.board.dto.PFaq;
import ksmart.ks50team01.platform.board.mapper.PFaqMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PFaqService {
	
	private final PFaqMapper pFaqMapper;
	
	/**
	 * 자주찾는 질문 조회
	 * @return List<PFaq>
	 */
	public List<PFaq> getFaqList(){
		return pFaqMapper.getFaqList();
	}

	
	/**
	 * 주어진 번호에 해당하는 자주 찾는 질문 조회
	 * @param faqNum 조회할 자주 찾는 질문 번호
	 * @return 조회된 PFaq 객체, 없을 경우 null
	 */
		public PFaq getFaqInfoByNum(String faqNum) {
			PFaq faqInfo;
			try {
				faqInfo = pFaqMapper.getFaqInfoByNum(faqNum);
			} catch (Exception e) {
				log.error("FAQ 정보를 조회하는 동안 예외 발생: {}", e.getMessage());
				// 예외 처리 로직 추가: 예외에 따른 대응 또는 기본값 설정 등
				faqInfo = null; // 예외 발생 시 기본값으로 null 설정
			}
			log.info("getFaqInfoByNum - faqNum: {}", faqNum);
			return faqInfo;
		}


	/**
	 * 자주찾는 질문 수정
	 * @param faqNum
	 * @return 수정된 행의 수
	 */
	public int faqModify(PFaq pFaq) {
		try {
			return pFaqMapper.faqModify(pFaq);
		} catch (Exception e) {
			log.error("FAQ 정보를 수정하는 동안 예외 발생: {}", e.getMessage());
			return -1;
		}
		
	}
	
	
	
    // 자주 찾는 질문 등록
    public void faqWrite(String faqNum) {
        pFaqMapper.faqWrite(faqNum); 
	
	
    }
	
	
	/**
	 * 자주찾는 질문 삭제
	 */
    public void faqDelete(String faqNum) {
    	pFaqMapper.faqDelete(faqNum);
    }
	
	



}
