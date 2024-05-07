package ksmart.ks50team01.platform.reivew.dto;

public class PReviewReport {
	private String reportNum; //신고번호
	private String reportId; //신고자 아이디
	private String reportCategories; //신고 카테고리
	private String reportReviewCode; //신고 리뷰 코드
	private String reportContent; //신고 내용
	private String reportDate; //신고 일자
	private String reportApprove; //신고승인여부
	private String reportApproveId; //신고승인자
	private String reportApproveDate; //신고승인자
	
	public String getReportNum() {
		return reportNum;
	}
	public void setReportNum(String reportNum) {
		this.reportNum = reportNum;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getReportCategories() {
		return reportCategories;
	}
	public void setReportCategories(String reportCategories) {
		this.reportCategories = reportCategories;
	}
	public String getReportReviewCode() {
		return reportReviewCode;
	}
	public void setReportReviewCode(String reportReviewCode) {
		this.reportReviewCode = reportReviewCode;
	}
	public String getReportContent() {
		return reportContent;
	}
	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public String getReportApprove() {
		return reportApprove;
	}
	public void setReportApprove(String reportApprove) {
		this.reportApprove = reportApprove;
	}
	public String getReportApproveId() {
		return reportApproveId;
	}
	public void setReportApproveId(String reportApproveId) {
		this.reportApproveId = reportApproveId;
	}
	public String getReportApproveDate() {
		return reportApproveDate;
	}
	public void setReportApproveDate(String reportApproveDate) {
		this.reportApproveDate = reportApproveDate;
	}
	@Override
	public String toString() {
		return "PReviewReport [reportNum=" + reportNum + ", reportId=" + reportId + ", reportCategories="
				+ reportCategories + ", reportReviewCode=" + reportReviewCode + ", reportContent=" + reportContent
				+ ", reportDate=" + reportDate + ", reportApprove=" + reportApprove + ", reportApproveId="
				+ reportApproveId + ", reportApproveDate=" + reportApproveDate + "]";
	}
	
	
}
