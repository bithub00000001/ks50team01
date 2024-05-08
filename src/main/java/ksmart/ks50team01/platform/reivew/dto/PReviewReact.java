package ksmart.ks50team01.platform.reivew.dto;

public class PReviewReact {
	private String reviewReactCode; //좋아요싫어요 기록 코드
	private String reviewReactId; //클릭 아이디
	private String reactReviewCode; //클릭 리뷰 코드
	private String reviewReactCheck; //좋아요싫어요 여부
	private String reviewReactDate; //등록일자
	private String reviewReactModifyDate; //수정일자
	
	public String getReviewReactCode() {
		return reviewReactCode;
	}
	public void setReviewReactCode(String reviewReactCode) {
		this.reviewReactCode = reviewReactCode;
	}
	public String getReviewReactId() {
		return reviewReactId;
	}
	public void setReviewReactId(String reviewReactId) {
		this.reviewReactId = reviewReactId;
	}
	public String getReactReviewCode() {
		return reactReviewCode;
	}
	public void setReactReviewCode(String reactReviewCode) {
		this.reactReviewCode = reactReviewCode;
	}
	public String getReviewReactCheck() {
		return reviewReactCheck;
	}
	public void setReviewReactCheck(String reviewReactCheck) {
		this.reviewReactCheck = reviewReactCheck;
	}
	public String getReviewReactDate() {
		return reviewReactDate;
	}
	public void setReviewReactDate(String reviewReactDate) {
		this.reviewReactDate = reviewReactDate;
	}
	public String getReviewReactModifyDate() {
		return reviewReactModifyDate;
	}
	public void setReviewReactModifyDate(String reviewReactModifyDate) {
		this.reviewReactModifyDate = reviewReactModifyDate;
	}
	
	@Override
	public String toString() {
		return "PReviewReact [reviewReactCode=" + reviewReactCode + ", reviewReactId=" + reviewReactId
				+ ", reactReviewCode=" + reactReviewCode + ", reviewReactCheck=" + reviewReactCheck
				+ ", reviewReactDate=" + reviewReactDate + ", reviewReactModifyDate=" + reviewReactModifyDate + "]";
	}
}
