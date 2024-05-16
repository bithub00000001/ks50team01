package ksmart.ks50team01.platform.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import ksmart.ks50team01.platform.board.dto.PFaq;

@Mapper
public interface PFaqMapper {
	
	// 자주찾는 질문 조회
	List<PFaq> getFaqList();
	
	// 제목으로 자주찾는 질문 검색
    List<PFaq> searchFaqByTitle(String title);

    // 
    PFaq getFaqInfoByNum(String faqNum);

    // 수정
	int faqModify(PFaq pFaq);
	
	
	// 자주찾는 질문 추가
	void faqInsert(PFaq faq);
	// 자주찾는 질문 삭제
	void faqDelete(String faqNum);
	
    
}
