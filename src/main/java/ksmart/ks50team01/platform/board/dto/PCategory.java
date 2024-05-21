package ksmart.ks50team01.platform.board.dto;

import lombok.Data;

@Data
public class PCategory {
	
	// 게시판 종류
	private String boardType;
	
	// 커뮤니티 카테고리
	private String postCateNum;
	private String postCateRegId;
	private String postCateType;
	private String postCateMdfId;
	private String postAct;
	private String postRegDate;
	private String postMdfDate;
	
	// 공지사항 카테고리
	private String noticeCateNum;
	private String noticeRegId;
	private String noticeCateType;
	private String noticeMdfId;
	private String noticeAct;
	private String noticeRegDate;
	private String noticeMdfDate;
	
	// 자주찾는질문 및 1:1문의 카테고리
	private String faqCateNum;
	private String faqRegId;
	private String faqCateType;
	private String faqMdfId;
	private String faqAct;
	private String faqRegDate;
	private String faqMdfDate;
	
	// 신고 카테고리
	private String reportCateNum;
	private String reportRegId;
	private String reportCateType;
	private String reportMdfId;
	private String reportAct;
	private String reportRegDate;
	private String reportMdfDate;
	
}
