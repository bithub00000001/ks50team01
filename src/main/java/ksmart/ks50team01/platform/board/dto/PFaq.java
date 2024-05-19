package ksmart.ks50team01.platform.board.dto;

import lombok.Data;

@Data
public class PFaq {
	
	private String faqNum;
	private String faqRegId;
	private String faqCateType;
	private String faqTitle;
	private String faqContent;
	private String faqMdfId;
	private String faqAct;
	private String faqRegDate;
	private String faqMdfDate;
	
	// 추가된 association을 위한 멤버 변수
	private PFaq faqCate;
	
	private String faqCateNum;
	
	
}
