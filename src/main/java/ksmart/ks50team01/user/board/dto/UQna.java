package ksmart.ks50team01.user.board.dto;

import java.util.List;

import lombok.Data;

@Data
public class UQna {
	private String qnaNum;
	private int qnaRowNum;
	private String qnaRegId;
	private String faqCateType;
	private String qnaProCate;
	private String qnaTitle;
	private String qnaContent;
	private String ansRegId;
	private String qnaRegDate;
	private String qnaMdfDate;
	private String qnartnDate;
	private boolean isNew;
	
	private UCategory category;
	
	private List<UQna> qnaList;
	
	// 추가된 association을 위한 멤버 변수
	//private UFaq faqCate;
	
	private UQnaAnswer qnaAnswer;
	
	

}
