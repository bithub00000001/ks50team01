package ksmart.ks50team01.user.board.dto;

import lombok.Data;

@Data
public class UCategory {

	// 게시글 카테고리
	private String postCategoryNum;
	private String postCategoryRegId;
	private String postCategoryName;
	private String postCategoryMdfId;
	private String postCateActive;
	private String postCategoryRegDate;
	private String postCategoryMdfDate;
	
	
	// qna 카테고리 (= faq 카테고리)
	private String qnaCategoryNum;
	private String qnaCategoryRegId;
	private String qnaCategoryName;
	private String qnaCategoryMdfId;
	private String qnaCateActive;
	private String qnaCategoryRegDate;
	private String qnaCategoryMdfDate;
	
	
}
