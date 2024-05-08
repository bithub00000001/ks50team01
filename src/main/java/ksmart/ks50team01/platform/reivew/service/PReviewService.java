package ksmart.ks50team01.platform.reivew.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.platform.reivew.dto.PReivewComment;
import ksmart.ks50team01.platform.reivew.dto.PReview;
import ksmart.ks50team01.platform.reivew.dto.PReviewPublic;
import ksmart.ks50team01.platform.reivew.dto.PReviewReact;
import ksmart.ks50team01.platform.reivew.dto.PReviewReport;
import ksmart.ks50team01.platform.reivew.mapper.PReviewMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PReviewService {
	
	private final PReviewMapper pReviewMapper;
	
	/**
	 * 좋아요 싫어요 기록 목록 리스트
	 */
	public List<PReviewReact> getPReviewReact(){
		return pReviewMapper.getPReviewReact();
	}
	
	/**
	 * 댓글 목록 리스트
	 */
	public List<PReivewComment> getPReivewComment(){
		return pReviewMapper.getPReivewComment();
	}
	
	/**
	 * 신고 목록 리스트
	 * @return
	 */
	public List<PReviewReport> getPReviewReports(){
		return pReviewMapper.getPReviewReport();
	}
	
	/**
	 * 공개여부수정
	 */
	public void modifyPReviewPublic(PReview review) {
		pReviewMapper.modifyPReviewPublic(review);
	}
	
	/**
	 * 공개여부조회
	 */
	public List<PReviewPublic> getPReviewPublic(){
		return pReviewMapper.getPReviewPublic();
		
	}
	
	/**
	 * 리뷰 목록 리스트
	 * @return
	 */
	public List<PReview> getPReviewList(){
		return pReviewMapper.getPReviewList();
	}
}
