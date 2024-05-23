package ksmart.ks50team01.platform.board.dto;

import lombok.Data;

@Data
public class PQna {
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
	
	private PCategory category;
	
	
	// 추가된 association을 위한 멤버 변수
	private PFaq faqCate;
	
	private PAnswer answer;
	

}
