package ksmart.ks50team01.user.destination.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.platform.trip.dto.PTourDetail;
import ksmart.ks50team01.platform.trip.mapper.PTripPlanMapper;
import ksmart.ks50team01.platform.trip.service.PTripPlanServiceImpl;
import ksmart.ks50team01.user.destination.mapper.UDestinationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UDestinationService {
	
	private final UDestinationMapper uDestinationMapper;

	public Map<String, Object> getTourList(int currentPage) {
		// 1. 보여줄 행의 갯수
				int rowPerPage = 12;
				
				// 2. 행의 시작 번호
				int startRow = (currentPage - 1) * rowPerPage;
				
				// 3. 보여질 시작페이지, 마지막페이지 번호 설정
				int startPageNum = 1;
				int lastPageNum = 10;
				
				// 로그인이력 조회의 파라미터 설정
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("startRow", startRow);
				paramMap.put("rowPerPage", rowPerPage);
				
				List<Map<String, Object>> tourList = uDestinationMapper.getTourListByPage(paramMap);
				int rowCnt = uDestinationMapper.getTourListRowCnt();
				int lastPage = (int)Math.ceil(rowCnt/rowPerPage);
				
				// 마지막 번호 페이지 설정 : 마지막페이지가 10보다 작다면 마지막페이지로 설정
				lastPageNum = lastPage < 10 ? lastPage : lastPageNum;
				
				
				
				//동적 페이지 설정
				if(currentPage > 6 && lastPage > 9) {
					startPageNum = currentPage - 5;
					lastPageNum = currentPage +4;
					if(lastPageNum >= lastPage) {
						startPageNum = lastPage -9;
						lastPageNum = lastPage;
					}
				}
				
				paramMap.clear();
				paramMap.put("tourList", tourList);
				paramMap.put("lastPage", lastPage);
				paramMap.put("startPageNum", startPageNum);
				paramMap.put("lastPageNum", lastPageNum);
				
				
				return paramMap;
	}

	public PTourDetail getDestinationDetail(String contentId) {
		// TODO Auto-generated method stub
		return uDestinationMapper.getDestinationDetail(contentId);
	}

}
