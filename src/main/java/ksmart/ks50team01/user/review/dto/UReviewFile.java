package ksmart.ks50team01.user.review.dto;

import lombok.Data;

@Data
public class UReviewFile {
	private String reviewFileCode;//이미지코드
	private String reviewId;//등록자 아이디
	private String reviewCode;//이미지가 존재하는 리뷰 코드
	private String reviewFileName;//이미지 이름
	private String reviewFilePath;//이미지주소
	private String reviewFileDate;//작성일자
	private String reviewFileNewName;//새로운 이미지 이름
	private Long reviewFileSize; //파일 크기
}
