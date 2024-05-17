package ksmart.ks50team01.user.review.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import ksmart.ks50team01.user.review.dto.UOpen;
import ksmart.ks50team01.user.review.dto.UReview;
import ksmart.ks50team01.user.review.dto.UReviewComment;
import ksmart.ks50team01.user.review.dto.UReviewFile;

@Mapper
public interface UReviewMapper {
	
	//파일을 추가하는 기능
	public int addFile(List<UReviewFile> fileList); 
	//모든 파일 정보를 가져오는 기능
	public List<UReviewFile> getFileList();
	//특정 파일의 정보를 가져오는 기능
	public UReviewFile getFileInfoByIdx(String fileIdx);
	
	

	
	// 가장 큰 PRCHS_REV_CD 값을 조회하는 메서드 추가
    String getMaxPrchsRevCd();
	
	//리뷰작성
	void reviewWrite(UReview review);
	
	//리뷰상세페이지
	UReview getReviewDetail(String reviewId);
	
	//리뷰수정
	void modifyReview(UReview review);
	
	
	//공개여부조회
	List<UOpen> getUOpen();
	
	
	//리뷰 조회
	List<UReview> getUReview();
	
	//댓글 조회
	List<UReviewComment> getUReviewComment();
	
	public List<UReviewFile> parseFileInfo(MultipartFile[] uploadfile);

}
