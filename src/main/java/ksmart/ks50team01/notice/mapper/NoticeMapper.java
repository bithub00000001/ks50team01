package ksmart.ks50team01.notice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.notice.dto.Notice;

@Mapper
public interface NoticeMapper {
	//공지사항 리스트 조회
	List<Notice> getNoticeList();

}
