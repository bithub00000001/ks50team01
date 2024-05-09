package ksmart.ks50team01.platform.reivew.dto;

public class PReivewComment {
	private String commenCode;//댓글 코드
	private String commentId;//작성자 아이디
	private String commentReviewCode;//상품 리뷰 코드
	private String commentApprove;//공개여부
	private String businessCode;//사업장 코드
	private String commentContent;//답글 내용
	private String commentDate;//답글 등록일자
	
	private String tripLargeCode; //여행지 대분류 코드
	private String districtSubclass; //지역 소분류명
	
	
	
	
	public String getCommenCode() {
		return commenCode;
	}
	public void setCommenCode(String commenCode) {
		this.commenCode = commenCode;
	}
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getCommentReviewCode() {
		return commentReviewCode;
	}
	public void setCommentReviewCode(String commentReviewCode) {
		this.commentReviewCode = commentReviewCode;
	}
	public String getCommentApprove() {
		return commentApprove;
	}
	public void setCommentApprove(String commentApprove) {
		this.commentApprove = commentApprove;
	}
	public String getBusinessCode() {
		return businessCode;
	}
	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}
	public String getTripLargeCode() {
		return tripLargeCode;
	}
	public void setTripLargeCode(String tripLargeCode) {
		this.tripLargeCode = tripLargeCode;
	}
	public String getDistrictSubclass() {
		return districtSubclass;
	}
	public void setDistrictSubclass(String districtSubclass) {
		this.districtSubclass = districtSubclass;
	}
	
	@Override
	public String toString() {
		return "PReivewComment [commenCode=" + commenCode + ", commentId=" + commentId + ", commentReviewCode="
				+ commentReviewCode + ", commentApprove=" + commentApprove + ", businessCode=" + businessCode
				+ ", commentContent=" + commentContent + ", commentDate=" + commentDate + ", tripLargeCode="
				+ tripLargeCode + ", districtSubclass=" + districtSubclass + "]";
	}
}
