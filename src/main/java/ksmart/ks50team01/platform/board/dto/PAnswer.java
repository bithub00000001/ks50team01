package ksmart.ks50team01.platform.board.dto;

import lombok.Data;

@Data
public class PAnswer {
	
	private String ansNum;
	private String ansRegId;
	private String qnaNum;
	private String qnacode;
	private String ansContent;
	private String ansMdfId;
	private String ansRegDate;
	private String ansMdfDate;
	
	
	private PQna pQna;
}
