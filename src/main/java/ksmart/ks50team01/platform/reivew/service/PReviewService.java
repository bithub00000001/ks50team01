package ksmart.ks50team01.platform.reivew.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.platform.reivew.dto.PReview;
import ksmart.ks50team01.platform.reivew.dto.PReviewReport;
import ksmart.ks50team01.platform.reivew.mapper.PReviewMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PReviewService {
	
	private final PReviewMapper pReviewMapper;
	
	
	public List<PReviewReport> getPReviewReports(){
		return pReviewMapper.getPReviewReport();
	}
	
	/**
	 * 리뷰 목록 리스트
	 * @return
	 */
	public List<PReview> getPReviewList(){
		return pReviewMapper.getPReviewList();
	}
}
