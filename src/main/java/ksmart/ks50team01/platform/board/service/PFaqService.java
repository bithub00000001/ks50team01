package ksmart.ks50team01.platform.board.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.platform.board.dto.PCategory;
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
	 * 주어진 번호에 해당하는 자주찾는질문 정보 조회
	 * @param faqNum 조회할 자주찾는질문 번호
	 * @return 조회된 PFaq 객체, 없을 경우 null
	 */
		public PFaq getFaqInfoByNum(String faqNum) {
			return pFaqMapper.getFaqInfoByNum(faqNum);
		}


	/**
	 * 자주찾는 질문 수정
	 * @param pFaq
	 */
	public void faqModify(PFaq pFaq) {
		pFaqMapper.faqModify(pFaq);
		
	}
	
	
	
    // 자주 찾는 질문 등록
    public void faqWrite(String faqNum) {
        pFaqMapper.faqWrite(faqNum); 
	
	
    }
	
	
	/**
	 * 자주찾는질문 삭제
	 */
    public void faqDelete(String faqNum) {
    	pFaqMapper.faqDelete(faqNum);
    }


	/**
	 * 자주찾는질문 카테고리 조회
	 * @return List<PCategory>
	 */
	public List<PCategory> getfaqCateList() {
		List<PCategory> faqCateList = pFaqMapper.getfaqCateList();
		
		log.info("자주찾는질문 카테고리 조회 결과: {}", faqCateList);
		return faqCateList;
	}


	// db에 저장
	public void insertFaq(PFaq pFaq) {
		pFaqMapper.insertFaq(pFaq);
		
	}


	/**
	 * 해당 카테고리의 자주 묻는 질문 목록 조회
	 * @param category
	 * @return 카테고리에 해당하는 자주 묻는 질문 목록
	 */
	public List<PFaq> getFaqListByCategory(String category) {
		List<PFaq> faqList = pFaqMapper.getFaqListByCategory(category);
		return faqList;
	}
	
	



}
