package ksmart.ks50team01.platform.reivew.dto;

import ksmart.ks50team01.platform.board.dto.PReport;
import lombok.Data;

@Data
public class PReviewReact {
	private String reviewReactCode; //좋아요싫어요 기록 코드
	private String reviewReactId; //클릭 아이디
	private String reactReviewCode; //클릭 리뷰 코드
	private String reviewReactCheck; //좋아요싫어요 여부
	private String reviewReactDate; //등록일자
	private String reviewReactModifyDate; //수정일자
	private String reviewReactNum; //좋아요싫어요 기록 번호
	
	private PReport reportCate;
}
