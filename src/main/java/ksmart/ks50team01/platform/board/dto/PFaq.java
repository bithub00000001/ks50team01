package ksmart.ks50team01.platform.board.dto;

import java.util.List;

import lombok.Data;

@Data
public class PFaq {
	
	private String faqNum;
	private int faqRowNum;
	private String faqRegId;
	private String faqCateNum;
	private String faqCateType;
	private String faqTitle;
	private String faqContent;
	private String faqMdfId;
	private String faqAct;
	private String faqRegDate;
	private String faqMdfDate;
	
	private PCategory category;
	private List<PFaq> faqList;
	//private PFaq faqInfo;
	
	
}
