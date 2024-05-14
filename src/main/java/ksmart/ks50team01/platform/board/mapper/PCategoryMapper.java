package ksmart.ks50team01.platform.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.platform.board.dto.PCategory;

@Mapper
public interface PCategoryMapper {
	
	// 카테고리 추가
	String categoryAdd(PCategory pCategory);
	
	// 카테고리 수정
	String categoryModify(PCategory pCategory);
	
	// 자주찾는질문 및 1:1문의 카테고리 조회
	List<PCategory> getFaqCategoryList();
	
	// 공지사항 카테고리 조회
	List<PCategory> getNoticeCategoryList();
	

	// 신고 카테고리 조회
	List<PCategory> getReportCategoryList();
}
