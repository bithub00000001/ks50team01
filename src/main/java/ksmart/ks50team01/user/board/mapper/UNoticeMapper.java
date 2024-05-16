package ksmart.ks50team01.user.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.user.board.dto.UCommunity;
import ksmart.ks50team01.user.board.dto.UNotice;


@Mapper
public interface UNoticeMapper {
	
	// 공지사항 상세목록 조회
	UNotice getNoticeByNoticeNum(String noticeNum);
	//List<UNotice> getNoticeDetailList();

	// 공지사항 목록조회
	List<UNotice> getNoticeList();
	
	

}
