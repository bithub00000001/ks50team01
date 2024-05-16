package ksmart.ks50team01.platform.reivew.dto;

import lombok.Data;

@Data
public class PReivewComment {
	private String commentCode;//답글 코드
	private String commentId;//작성자 아이디
	private String commentReviewCode;//상품 리뷰 코드
	private String commentApprove;//공개여부
	private String commentApproveName;//공개여부
	private String businessCode;//사업장 코드
	private String commentContent;//답글 내용
	private String commentDate;//답글 등록일자
	private String commentNum;//답글 번호
	
	private String tripLargeCode; //여행지 대분류 코드
	private String districtSubclass; //지역 소분류명
	
	private PReviewBusiness ReviewBusinessInfo;
	
}
