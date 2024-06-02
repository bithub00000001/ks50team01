package ksmart.ks50team01.platform.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.platform.board.dto.PReport;
import ksmart.ks50team01.platform.board.mapper.PReportMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PReportService {
	
	private final PReportMapper pReportMapper;
	
	/**
	 * 신고내역 조회
	 * @return List<PReport>
	 */
	public List<PReport> getReportList(){
		List<PReport> reportList = pReportMapper.getReportList();
		log.info("신고내역 조회 결과: {}", reportList);
		return reportList;
	}

	public List<PReport> getReportListByCategory(String category) {
		List<PReport> reportList = pReportMapper.getReportListByCategory(category);
		return reportList;
	}

	/**
	 * 신고 승인
	 * @param reportNum 승인할 게시물 번호
	 */
	public void approveReport(String reportNum) {
		pReportMapper.approveReport(reportNum);
		
	}
	
}
