package ksmart.ks50team01.platform.reivew.dto;

import lombok.Data;

@Data
public class PReviewReport {
	private String reportCode; //신고번호
	private String reportNum; //신고번호
	private String reportId; //신고자 아이디
	private String reportCategories; //신고 카테고리
	private String reportReviewCode; //신고 리뷰 코드
	private String reportContent; //신고 내용
	private String reportDate; //신고 일자
	private String reportApprove; //신고승인여부
	private String reportApproveName; //신고승인여부
	private String reportApproveId; //신고승인자
	private String reportApproveDate; //신고승인일
	private int reportTotal; //누적갯수
	
	
}
