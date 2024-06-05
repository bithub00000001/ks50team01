package ksmart.ks50team01.platform.board.dto;

import lombok.Data;

@Data
public class PComment {
	
	private String commentNum;
	private String postNum;
	private String commentRegId;
	private String commentContent;
	private int commentlikeTotal;
	private int commentDislikeTotal;
	private int commentReportTotal;
	private String commentAct;
	private String commentRegDate;
	private String commentMdfDate;
	private String commentDelDate;

}
