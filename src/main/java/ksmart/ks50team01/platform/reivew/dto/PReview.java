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
	
	@Override
	public String toString() {
		return "PReview [reviewCode=" + reviewCode + ", reviewId=" + reviewId + ", reviewGoods=" + reviewGoods
				+ ", reviewStar=" + reviewStar + ", reviewContent=" + reviewContent + ", reviewDate=" + reviewDate
				+ ", reviewLike=" + reviewLike + ", reviewDislike=" + reviewDislike + ", reviewApprove=" + reviewApprove
				+ "]";
	}
}
