package ksmart.ks50team01.platform.reivew.dto;

import lombok.Data;

@Data
public class PReviewPublic {
	private String publicCode;//공개여부코드
	private String publicId;//등록플롯폼권한아이디
	private String publicContent;//내용
	private String publicActivity;//활성화여부
	private String publicDate;//등록일자
	private String publicModifyDate;//수정일자
	
	
}
