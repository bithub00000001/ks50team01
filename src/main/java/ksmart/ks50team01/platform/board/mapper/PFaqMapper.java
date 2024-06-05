package ksmart.ks50team01.platform.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.platform.board.dto.PCategory;
import ksmart.ks50team01.platform.board.dto.PFaq;

@Mapper
public interface PFaqMapper {
	
	// 자주묻는질문 조회
	List<PFaq> getFaqList();
	
	// 제목으로 자주묻는질문 검색
    List<PFaq> searchFaqByTitle(String title);

    // 자주묻는질문 번호로 자주묻는질문 정보 조회
    PFaq getFaqInfoByNum(String faqNum);
    
	// 자주묻는질문 카테고리 조회
	List<PCategory> getfaqCateList();

	// 카테고리별 자주묻는질문 목록 조회
	List<PFaq> getFaqListByCategory(String category);

    // 자주묻는질문 등록
	void faqAdd(PFaq pFaq);
    
    // 자주묻는질문 수정
    void faqModify(PFaq pFaq);
	
	// 자주묻는질문 삭제
	void faqRemove(String faqNum);
    
}
