package ksmart.ks50team01.user.review.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.user.review.dto.UReviewComment;
import ksmart.ks50team01.user.review.dto.UReview;

@Mapper
public interface UReviewMapper {
	
	//리뷰작성페이지
	void reivewSave(UReview review);
	
	//리뷰상세페이지
	UReview getUReviewDetail(String reviewId);
	
	//리뷰수정
	void reviewUpdate(UReview review);
	
	
	
	
	
	//리뷰 조회
	List<UReview> getUReview();
	
	//댓글 조회
	List<UReviewComment> getUReviewComment();

}
