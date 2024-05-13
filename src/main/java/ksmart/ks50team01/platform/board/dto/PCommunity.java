package ksmart.ks50team01.platform.board.dto;

import lombok.Data;

@Data
public class PCommunity {
	
	private String postNum;
	private String postRegId;
	private String postCateNum;
	private String postTitle;
	private String postContent;
	private int postInqCnt;
	private int commentTotal;
	private int likeTotal;
	private int dislikeTotal;
	private int reportTotal;
	private String postAct;
	private String postRegDate;
	private String postMdfDate;
	private String postDelDate;
	

}
