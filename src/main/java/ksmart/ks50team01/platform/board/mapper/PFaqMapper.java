package ksmart.ks50team01.platform.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.platform.board.dto.PCategory;
import ksmart.ks50team01.platform.board.dto.PFaq;

@Mapper
public interface PFaqMapper {
	
	// 자주찾는질문 조회
	List<PFaq> getFaqList();
	
	// 제목으로 자주찾는질문 검색
    List<PFaq> searchFaqByTitle(String title);

    // 
    PFaq getFaqInfoByNum(String faqNum);

    // 자주찾는질문 수정
    void faqModify(PFaq pFaq);
	
	
	// 자주찾는질문 등록
	void faqWrite(String faqNum);
	
	
	// 자주찾는질문 삭제
	void faqDelete(String faqNum);

	// 자주찾는질문 카테고리 조회
	List<PCategory> getfaqCateList();
	
	// 자주찾는질문 DB에 저장
	void insertFaq(PFaq pFaq);

	
    
}
