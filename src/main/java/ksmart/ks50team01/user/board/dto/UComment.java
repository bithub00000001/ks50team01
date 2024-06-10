package ksmart.ks50team01.user.board.dto;


import lombok.Data;

@Data
public class UComment {
	private String commentNum;
	private String postNum;
	private String commentRegId;
	private String commentContent;
	private int commentlikeTotal;
	private int commentDislikeTotal;
	private int commentReportTotal;
	private String commentMdfId;
	private String commentAct;
	private String commentRegDate;
	private String commentMdfDate;
	private String commentDelDate;

}
