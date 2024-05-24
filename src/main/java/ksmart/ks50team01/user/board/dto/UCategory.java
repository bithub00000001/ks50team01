package ksmart.ks50team01.user.board.dto;

import lombok.Data;

@Data
public class UCategory {

	// 커뮤니티 게시글 카테고리
	private String postCategoryNum;
	private String postCategoryRegId;
	private String postCategoryName;
	private String postCategoryMdfId;
	private String postCateActive;
	private String postCategoryRegDate;
	private String postCategoryMdfDate;
	
	// 공지사항 카테고리
	private String noticeCategoryNum;
	private String noticeCategoryRegId;
	private String noticeCategoryName;
	private String noticeCategoryMdfId;
	private String noticeCateActive;
	private String noticeCategoryRegDate;
	private String noticeCategoryMdfDate;
	
	// 1:1문의 카테고리
	private String qnaCategoryNum;
	private String qnaCategoryRegId;
	private String qnaCategoryName;
	private String qnaCategoryMdfId;
	private String qnaCateActive;
	private String qnaCategoryRegDate;
	private String qnaCategoryMdfDate;
	
	// 자주찾는질문 카테고리
	private String faqCategoryNum;
	private String faqCategoryRegId;
	private String faqCategoryName;
	private String faqCategoryMdfId;
	private String faqCateActive;
	private String faqCategoryRegDate;
	private String faqCategoryMdfDate;
	
	
}
