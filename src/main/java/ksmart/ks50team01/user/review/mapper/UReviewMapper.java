package ksmart.ks50team01.user.review.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.user.review.dto.UComment;
import ksmart.ks50team01.user.review.dto.UReview;

@Mapper
public interface UReviewMapper {
	//리뷰 조회
	List<UReview> getUReview();
	
	//댓글 조회
	List<UComment> getUComment();

}
