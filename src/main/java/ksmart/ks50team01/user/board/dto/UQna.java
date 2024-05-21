package ksmart.ks50team01.user.board.dto;

import lombok.Data;

@Data
public class UQna {
	private String qnaNum;
	private String qnaRegId;
	private String faqCateType;
	private String qnaProCate;
	private String qnaTitle;
	private String qnaContent;
	private String ansRegId;
	private String qnaRegDate;
	private String qnaMdfDate;
	private String qnartnDate;
	
	// 추가된 association을 위한 멤버 변수
	private UFaq faqCate;
	
	private UQnaAnswer qnaAnswer;
	
	

}
