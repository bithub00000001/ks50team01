package ksmart.ks50team01.platform.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.platform.board.dto.PCategory;
import ksmart.ks50team01.platform.board.dto.PNotice;

@Mapper
public interface PNoticeMapper {
	
	// 공지사항 목록조회
	List<PNotice> getNoticeList();
	
	// 공지사항 상세목록 조회
	List<PNotice> getNoticeDetailList();
	
	// 공지사항 번호로 공지사항 조회
	PNotice getNoticeInfoByNum(String noticeNum);
	
	// 공지사항 카테고리 조회
	List<PCategory> getNoticeCateList();

	// 카테고리별 공지사항 목록 조회 
	List<PNotice> getNoticeListByCategory(String category);
	
	// 공지사항 작성
	void noticeAdd(PNotice pNotice);
	
	// 공지사항 수정
	void noticeModify(PNotice pNotice);
	
	// 공지사항 삭제
	void noticeRemove(String noticeNum);

	
}


