package ksmart.ks50team01.platform.board.dto;

import lombok.Data;

@Data
public class PNotice {
	
	private String noticeNum;
	private String noticeRegId;
	private String noticeCateType;
	private String noticeTitle;
	private String noticeContent;
	private int noticeInqCnt;
	private String noticeMdfId;
	private String noticeAct;
	private String noticeRegDate;
	private String noticeMdfDate;
	
	// 추가된 association을 위한 멤버 변수
	private PNotice noticeCate;
	

}
