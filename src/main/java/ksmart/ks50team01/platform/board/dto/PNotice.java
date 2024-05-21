package ksmart.ks50team01.platform.board.dto;

import java.util.List;

import lombok.Data;

@Data
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
	
	private PCategory category;
	private List<PNotice> noticeList;
	
	

}
