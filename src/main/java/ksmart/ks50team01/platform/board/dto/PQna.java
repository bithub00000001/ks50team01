package ksmart.ks50team01.platform.board.dto;

import java.util.List;

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
	
	private List<PAnswer> answerList;



}
