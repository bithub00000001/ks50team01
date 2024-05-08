package ksmart.ks50team01.platform.reivew.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.platform.reivew.dto.POpen;
import ksmart.ks50team01.platform.reivew.dto.PReivewComment;
import ksmart.ks50team01.platform.reivew.dto.PReview;
import ksmart.ks50team01.platform.reivew.dto.PReviewReact;
import ksmart.ks50team01.platform.reivew.dto.PReviewReport;

@Mapper
public interface PReviewMapper {
	
	
	//좋아요싫어요기록목록조회
	List<PReviewReact> getPReviewReact();
	
	//댓글목록조회
	List<PReivewComment> getPReivewComment();
	
	//신고목록조회
	List<PReviewReport> getPReviewReport();
	
	//리뷰정보수정 05.08
	int modifyPReview(PReview review);
	
	//리뷰정보조회 05.08
	PReview getPReviewInfoById(String reviewCode);
	
	//공개 조회 05.08
	List<POpen> getPOpenList();
	
	//리뷰목록조회
	List<PReview> getPReviewList();

}
