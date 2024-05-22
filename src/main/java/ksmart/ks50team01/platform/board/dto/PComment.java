package ksmart.ks50team01.platform.board.dto;

import lombok.Data;

@Data
public class PComment {
	
	private String CommentNum;
	private String CommentRegId;
	private String CommentContent;
	private int CommentlikeTotal;
	private int CommentDislikeTotal;
	private int CommentReportTotal;
	private String CommentAct;
	private String CommentRegDate;
	private String CommentMdfDate;
	private String CommentDelDate;

}
