package ksmart.ks50team01.platform.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.platform.board.dto.PReport;

@Mapper
public interface PReportMapper {
	
	// 신고내역 조회
	List<PReport> getReportList();

}
