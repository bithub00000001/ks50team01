package ksmart.ks50team01.user.review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ch.qos.logback.core.util.FileUtil;
import ksmart.ks50team01.user.review.dto.UOpen;
import ksmart.ks50team01.user.review.dto.UReview;
import ksmart.ks50team01.user.review.dto.UReviewComment;
import ksmart.ks50team01.user.review.dto.UReviewFile;
import ksmart.ks50team01.user.review.mapper.UReviewMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UReviewService {
	
	private final UReviewMapper uReviewMapper;

	
	
	/**
	 * 리뷰 수정
	 */
	public void modifyReview(UReview review) {
		uReviewMapper.modifyReview(review);
	}
	
	/**
	 * 리뷰정보조회
	 */
	public UReview getReviewDetail(String reviewId) {
		UReview reviewDetail = uReviewMapper.getReviewDetail(reviewId);
		return reviewDetail;
	}
	
	/**
	 * 리뷰등록
	 */
	public void reviewWrite(UReview review) {
        // 가장 큰 PRCHS_REV_CD 값을 조회
        String maxPrchsRevCd = uReviewMapper.getMaxPrchsRevCd();
        String newPrchsRevCd;

        if (maxPrchsRevCd != null) {
            // 숫자 부분만 추출해서 1을 더하고 다시 문자열로 변환
            int maxNumber = Integer.parseInt(maxPrchsRevCd.substring(10));
            newPrchsRevCd = "PRCHS_REV_" + String.format("%03d", maxNumber + 1);
        } else {
            // 테이블이 비어있는 경우 첫 번째 값 설정
            newPrchsRevCd = "PRCHS_REV_001";
        }

        // 새 PRCHS_REV_CD 값을 review 객체에 설정
        review.setReviewCode(newPrchsRevCd);
        
		uReviewMapper.reviewWrite(review);
	}
	
	
	/**
	 * 공개여부 조회
	 */
	public List<UOpen> getUOpen(){
		return uReviewMapper.getUOpen();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 댓글 목록
	 */
	public List<UReview> getUReviewList(){
		List<UReview> uReviewList = uReviewMapper.getUReview();
		
		if (uReviewList != null) {
			uReviewList.forEach(review -> {
				String reviewApprove = review.getReviewApprove();
				String reviewApproveName = "";
				String reviewStar = review.getReviewStar();
				int reviewStarPoint = 0;
				switch (reviewApprove) {
				case "DISCLOSURE_001":
					reviewApproveName = "전체공개";
					break;
				case "DISCLOSURE_002":
					reviewApproveName = "나만공개";
					break;
				}
				review.setReviewApproveName(reviewApproveName);
				switch (reviewStar) {
				case "RAT_CTGRY_01":
					reviewStarPoint = 1;
					break;
				case "RAT_CTGRY_02":
					reviewStarPoint = 2;
					break;
				case "RAT_CTGRY_03":
					reviewStarPoint = 3;
					break;
				case "RAT_CTGRY_04":
					reviewStarPoint = 4;
					break;
				case "RAT_CTGRY_05":
					reviewStarPoint = 5;
					break;
				}
				review.setReviewStarPoint(reviewStarPoint);
			});
		}
		
		return uReviewList;
	}
	
	
	/**
	 * 답글 목록
	 */
	public List<UReviewComment> getUReveiwComment(){
		return uReviewMapper.getUReviewComment();
	}
	
	
}
