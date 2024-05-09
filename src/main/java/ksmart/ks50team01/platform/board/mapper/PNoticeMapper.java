package ksmart.ks50team01.platform.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.platform.board.dto.PNotice;

@Mapper
public interface PNoticeMapper {
	
	// 공지사항 상세목록 조회
	List<PNotice> getNoticeDetailList();

	// 공지사항 목록조회
	List<PNotice> getNoticeList();

}
