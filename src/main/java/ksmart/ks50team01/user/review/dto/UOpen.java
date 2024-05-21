package ksmart.ks50team01.user.review.dto;

import lombok.Data;

@Data
public class UOpen {
	private String uOpenCode;//공개여부코드
	private String uOpenId;//등록 아이디
	private String uOpenContent;//내용
	private String uOpenActivty;//활성화여부
	private String uOpenDate;//등록일자
	private String uOpenModifyDate;//수정일자

}
