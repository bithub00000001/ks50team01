package ksmart.ks50team01.platform.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.platform.board.dto.PCategory;
import ksmart.ks50team01.platform.board.mapper.PCategoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PCategoryService {
	
	private final PCategoryMapper pCategoryMapper;
	
	/**
	 * 자주찾는질문 카테고리 조회
	 * @return List<PCategory>
	 */
	public List<PCategory> getFaqCategoryList() {
		
		List<PCategory> faqCategoryList = pCategoryMapper.getFaqCategoryList();
		return faqCategoryList;
	}




}
