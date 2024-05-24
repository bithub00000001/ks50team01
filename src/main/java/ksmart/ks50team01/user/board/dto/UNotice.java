package ksmart.ks50team01.user.board.dto;

import lombok.Data;

@Data
public class UNotice {
	private String noticeNum;
	private int noticeRowNum;
	private String noticeRegId;
	private String noticeCateType;
	private String noticeTitle;
	private String noticeContent;
	private int noticeInqCnt;
	private String noticeMdfId;
	private String noticeAct;
	private String noticeRegDate;
	private String noticeMdfDate;
	private boolean isNew;
	
	private UCategory category;
	
	
    // 추가된 필드: 공지사항 상세 정보
    private String noticeDetail; // 공지사항 상세 정보

}
