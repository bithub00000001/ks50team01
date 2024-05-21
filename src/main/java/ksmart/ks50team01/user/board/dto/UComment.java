package ksmart.ks50team01.user.board.dto;


import lombok.Data;

@Data
public class UComment {
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
