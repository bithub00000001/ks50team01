package ksmart.ks50team01.user.board.dto;

import java.util.List;

import lombok.Data;

@Data
public class UQna {
	private String qnaNum;
	private int qnaRowNum;
	private String qnaRegId;
	private String faqCateNum;
	private String faqCateType;
	private String qnaProNum;
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
	

	
	private UQnaAnswer qnaAnswer;
	
	

}
