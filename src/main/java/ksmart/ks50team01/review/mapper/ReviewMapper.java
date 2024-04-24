package ksmart.ks50team01.review.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.review.dto.Review;

@Mapper
public interface ReviewMapper {
	//리뷰 리스트 조회
	List<Review> getReivewList(); //추상메소드
}
