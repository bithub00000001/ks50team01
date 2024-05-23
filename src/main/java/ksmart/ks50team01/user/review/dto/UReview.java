package ksmart.ks50team01.user.review.dto;

import lombok.Data;

@Data
public class UReview {
	private String reviewCode; //리뷰 코드
	private String reviewId; //작성자 아이디
	private String reviewGoods; //리뷰 상품
	private String reviewStar; //별점 코드
	private String reviewContent; //리뷰 내용
	private String reviewDate; //리뷰 작성일
	private String reviewLike; //좋아요 수
	private String reviewDislike; //싫어요 수
	private String reviewApprove; //공개여부
	private String reviewApproveName; //공개여부이름
	private int reviewStarPoint; // 별점 내용
	private String reviewNum; // 리뷰 번호
	private String districtSubclassCode;//지역 소분류 코드
	
	

	private String tripLargeCode;//여행지 대분류 코드
	private String tripSubclassCode;//여행지 소분류 코드
	private String paymentCode;//결제 인증 첨부파일 코드
	private String reservationCode;//예약번호
	private String goodsOption;//상품옵션
	private String goodsInformation;//상품정보
	private String reportTotal;//신고합계
	private String reviewModifyDate;//수정일자

}
