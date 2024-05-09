package ksmart.ks50team01.platform.board.dto;


public class PNotice {
	
	private String noticeNum;
	private String noticeRegId;
	private String noticeCateNum;
	private String noticeTitle;
	private String noticeContent;
	private int noticeInqCnt;
	private String noticeMdfId;
	private String noticeAct;
	private String noticeRegDate;
	private String noticeMdfDate;
	
	public String getNoticeNum() {
		return noticeNum;
	}
	public void setNoticeNum(String noticeNum) {
		this.noticeNum = noticeNum;
	}
	public String getNoticeRegId() {
		return noticeRegId;
	}
	public void setNoticeRegId(String noticeRegId) {
		this.noticeRegId = noticeRegId;
	}
	public String getNoticeCateNum() {
		return noticeCateNum;
	}
	public void setNoticeCateNum(String noticeCateNum) {
		this.noticeCateNum = noticeCateNum;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public int getNoticeInqCnt() {
		return noticeInqCnt;
	}
	public void setNoticeInqCnt(int noticeInqCnt) {
		this.noticeInqCnt = noticeInqCnt;
	}
	public String getNoticeMdfId() {
		return noticeMdfId;
	}
	public void setNoticeMdfId(String noticeMdfId) {
		this.noticeMdfId = noticeMdfId;
	}
	public String getNoticeAct() {
		return noticeAct;
	}
	public void setNoticeAct(String noticeAct) {
		this.noticeAct = noticeAct;
	}
	public String getNoticeRegDate() {
		return noticeRegDate;
	}
	public void setNoticeRegDate(String noticeRegDate) {
		this.noticeRegDate = noticeRegDate;
	}
	public String getNoticeMdfDate() {
		return noticeMdfDate;
	}
	public void setNoticeMdfDate(String noticeMdfDate) {
		this.noticeMdfDate = noticeMdfDate;
	}
	
	@Override
	public String toString() {
		return "PNotice [noticeNum=" + noticeNum + ", noticeRegId=" + noticeRegId + ", noticeCateNum=" + noticeCateNum
				+ ", noticeTitle=" + noticeTitle + ", noticeContent=" + noticeContent + ", noticeInqCnt=" + noticeInqCnt
				+ ", noticeMdfId=" + noticeMdfId + ", noticeAct=" + noticeAct + ", noticeRegDate=" + noticeRegDate
				+ ", noticeMdfDate=" + noticeMdfDate + "]";
	}
	

}
