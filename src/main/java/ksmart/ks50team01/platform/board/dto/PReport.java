package ksmart.ks50team01.platform.board.dto;

import java.util.List;

import lombok.Data;

@Data
public class PReport {
	private String reportNum;
	private String reportRowNum;
	private String reportId;
	private String reportCateType;
	private String postCmntNum;
	private String reportContent;
	private String reportDate;
	private String reportedId;
	private String approveId;
	private String reportApprove;
	private String approveDate;
	
	private PCategory category;
	
	private List<PReport> reportList;
	

}
