package ksmart.ks50team01.user.board.dto;


import java.util.List;

import lombok.Data;

@Data
public class UCommunity {
	
	private String postNum;
	private int postRowNum;
	private String postRegId;
	private String postCateNum;
	private String postTitle;
	private String postContent;
	private int postInqCnt;
	private int commentTotal;
	private int postLikeTotal;
	private int postDislikeTotal;
	private int postReportTotal;
	private String postAct;
	private String postRegDate;
	private String postMdfDate;
	private String postDelDate;
	private boolean isNew;
	
	private UCategory category;
	
	private List<UComment> commentList;
	
	
	//private String postCateName;
	/*
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
	*/
	//private String postCategory;
	//private String postFile;
	//private int commentnt; // 댓글 수 필드 추가
	
	// 추가된 association을 위한 멤버 변수
	//private UCommunity postCate;
	
	// 추가된 association을 위한 멤버 변수
	//private UCommunity Comment;
	
	
	//private List<UComment> commentList;
    
    // 추가된 필드: 게시물 상세 정보
    //private String postDetail;
    
    //private String pstNoNumeric;


}
