package ksmart.ks50team01.user.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.user.board.dto.UNotice;


@Mapper
public interface UNoticeMapper {
	
	// 공지사항 목록 조회
	List<UNotice> getNoticeList();
	
	// 공지사항 상세 조회
	UNotice getNoticeDetail(String noticeNum);

	// 조회수 증가
	int increaseViewCount(String noticeNum);
	

}
