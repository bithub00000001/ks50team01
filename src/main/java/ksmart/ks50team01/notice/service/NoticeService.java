package ksmart.ks50team01.notice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ksmart.ks50team01.notice.dto.Notice;
import ksmart.ks50team01.notice.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {
	private final NoticeMapper noticeMapper;
	
	public List<Notice> getNoticeList(){
		return noticeMapper.getNoticeList();
		
	}
	
	

}
