package ksmart.ks50team01.platform.board.dto;

import lombok.Data;

@Data
public class PReport {
	private String reportNum;
	private String reportId;
	private String reportCateType;
	private String postCmntNum;
	private String reportContent;
	private String reportDate;
	private String reportedId;
	private String approveId;
	private String reportApprove;
	private String approveDate;
	
	
	// 추가된 association을 위한 멤버 변수
	private PReport reportCate;

}
