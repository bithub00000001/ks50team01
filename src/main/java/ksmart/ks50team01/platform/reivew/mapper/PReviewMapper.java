package ksmart.ks50team01.platform.reivew.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.platform.reivew.dto.PReview;
import ksmart.ks50team01.platform.reivew.dto.PReviewReport;

@Mapper
public interface PReviewMapper {
	
	//신고목록조회
	List<PReviewReport> getPReviewReport();
	
	//리뷰목록조회
	List<PReview> getPReviewList();

}
