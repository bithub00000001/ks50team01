package ksmart.ks50team01.user.board.dto;

import lombok.Data;

@Data
public class UNotice {
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
	private UNotice noticeCate;
	
	
    // 추가된 필드: 공지사항 상세 정보
    private String noticeDetail; // 공지사항 상세 정보

}
