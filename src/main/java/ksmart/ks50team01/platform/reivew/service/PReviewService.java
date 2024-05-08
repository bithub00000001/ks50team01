package ksmart.ks50team01.platform.reivew.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.platform.reivew.dto.PPublic;
import ksmart.ks50team01.platform.reivew.dto.PReivewComment;
import ksmart.ks50team01.platform.reivew.dto.PReview;
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
	 * 05.08 작성
	 * 리뷰정보수정
	 */
	public void modifyPReview(PReview review) {
		pReviewMapper.modifyPReview(review);
	}
	
	/**
	 * 05.08 작성
	 * 리뷰정보조회
	 */
	public PReview getPReviewInfoById(String reviewCode) {
		PReview reviewInfo = pReviewMapper.getPReviewInfoById(reviewCode);
		return reviewInfo;
	}
	
	/**
	 * 05.08 작성
	 * 공개 조회
	 */
	public List<PPublic> getPPublicList(){
		return pReviewMapper.getPPublicList();
	}
	
	/**
	 * 05.08 수정
	 * 리뷰 목록 리스트 
	 * @return
	 */
	public List<PReview> getPReviewList(){
		List<PReview> pReviewList = pReviewMapper.getPReviewList();
		
		if(pReviewList != null) {
			pReviewList.forEach(review -> {
				String reviewApprove = review.getReviewApprove();
				String reviewApproveName = "";
				switch (reviewApprove) {
	            case "DISCLOSURE_001":reviewApproveName = "전체공개";
	                break;
	            case "DISCLOSURE_002":reviewApproveName = "나만공개";
	                break;
				}
				review.setReviewApproveName(reviewApproveName);
			});
		}
		return pReviewList;
	}
}
