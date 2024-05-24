package ksmart.ks50team01.platform.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.platform.board.dto.PCategory;

@Mapper
public interface PCategoryMapper {
	
	// 게시판 종류에 해당하는 카테고리 조회
	List<PCategory> getCategoryListByBoardType(String boardType);
	
	// 공지사항 카테고리 조회
	List<PCategory> getNoticeCategoryList();
	
	// 자주찾는 질문 및 1:1문의 카테고리 조회
	List<PCategory> getFaqCategoryList();
	
	// 신고 카테고리 조회
	List<PCategory> getReportCategoryList();
	
	// 커뮤니티 카테고리 조회
	List<PCategory> getCommunityCategoryList();
	
	
	
	
	
	
	
	// 카테고리 추가
	String categoryAdd(PCategory pCategory);
	
	// 카테고리 수정
	String categoryModify(PCategory pCategory);

	

	
}
