package ksmart.ks50team01.platform.reivew.dto;

public class PReview {
	private String reviewCode; //리뷰번호
	private String reviewId; //작성자 아이디
	private String reviewGoods; //리뷰 상품
	private String reviewStar; //별점
	private String reviewContent; //리뷰 내용
	private String reviewDate; //리뷰 작성일
	private String reviewLike; //좋아요 수
	private String reviewDislike; //싫어요 수
	private String reviewApprove; //공개여부
	
	private String tripLargeCode;//여행지 대분류 코드
	private String districtSubclassCode;//지역 소분류 코드
	private String tripSubclassCode;//여행지 소분류 코드
	private String paymentCode;//결제 인증 첨부파일 코드
	private String reservationCode;//예약번호
	private String goodsOption;//상품옵션
	private String goodsInformation;//상품정보
	private String reportTotal;//신고합계
	private String reviewModifyDate;//수정일자
	
	
	
	public String getReviewCode() {
		return reviewCode;
	}
	public void setReviewCode(String reviewCode) {
		this.reviewCode = reviewCode;
	}
	public String getReviewId() {
		return reviewId;
	}
	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}
	public String getReviewGoods() {
		return reviewGoods;
	}
	public void setReviewGoods(String reviewGoods) {
		this.reviewGoods = reviewGoods;
	}
	public String getReviewStar() {
		return reviewStar;
	}
	public void setReviewStar(String reviewStar) {
		this.reviewStar = reviewStar;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	public String getReviewLike() {
		return reviewLike;
	}
	public void setReviewLike(String reviewLike) {
		this.reviewLike = reviewLike;
	}
	public String getReviewDislike() {
		return reviewDislike;
	}
	public void setReviewDislike(String reviewDislike) {
		this.reviewDislike = reviewDislike;
	}
	public String getReviewApprove() {
		return reviewApprove;
	}
	public void setReviewApprove(String reviewApprove) {
		this.reviewApprove = reviewApprove;
	}
	
	public String getTripLargeCode() {
		return tripLargeCode;
	}
	public void setTripLargeCode(String tripLargeCode) {
		this.tripLargeCode = tripLargeCode;
	}
	public String getDistrictSubclassCode() {
		return districtSubclassCode;
	}
	public void setDistrictSubclassCode(String districtSubclassCode) {
		this.districtSubclassCode = districtSubclassCode;
	}
	public String getTripSubclassCode() {
		return tripSubclassCode;
	}
	public void setTripSubclassCode(String tripSubclassCode) {
		this.tripSubclassCode = tripSubclassCode;
	}
	public String getPaymentCode() {
		return paymentCode;
	}
	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}
	public String getReservationCode() {
		return reservationCode;
	}
	public void setReservationCode(String reservationCode) {
		this.reservationCode = reservationCode;
	}
	public String getGoodsOption() {
		return goodsOption;
	}
	public void setGoodsOption(String goodsOption) {
		this.goodsOption = goodsOption;
	}
	public String getGoodsInformation() {
		return goodsInformation;
	}
	public void setGoodsInformation(String goodsInformation) {
		this.goodsInformation = goodsInformation;
	}
	public String getReportTotal() {
		return reportTotal;
	}
	public void setReportTotal(String reportTotal) {
		this.reportTotal = reportTotal;
	}
	public String getReviewModifyDate() {
		return reviewModifyDate;
	}
	public void setReviewModifyDate(String reviewModifyDate) {
		this.reviewModifyDate = reviewModifyDate;
	}
	
	@Override
	public String toString() {
		return "PReview [reviewCode=" + reviewCode + ", reviewId=" + reviewId + ", reviewGoods=" + reviewGoods
				+ ", reviewStar=" + reviewStar + ", reviewContent=" + reviewContent + ", reviewDate=" + reviewDate
				+ ", reviewLike=" + reviewLike + ", reviewDislike=" + reviewDislike + ", reviewApprove=" + reviewApprove
				+ ", tripLargeCode=" + tripLargeCode + ", districtSubclassCode=" + districtSubclassCode
				+ ", tripSubclassCode=" + tripSubclassCode + ", paymentCode=" + paymentCode + ", reservationCode="
				+ reservationCode + ", goodsOption=" + goodsOption + ", goodsInformation=" + goodsInformation
				+ ", reportTotal=" + reportTotal + ", reviewModifyDate=" + reviewModifyDate + "]";
	}
}
