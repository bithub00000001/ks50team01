package ksmart.ks50team01.platform.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.platform.board.dto.PCategory;
import ksmart.ks50team01.platform.board.mapper.PCategoryMapper;
import ksmart.ks50team01.platform.destination.dto.Destination;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PCategoryService {
	
	private final PCategoryMapper pCategoryMapper;
	
	/**
	 * 게시판 종류에 해당하는 카테고리 조회
	 * @param boardType 게시판 종류
	 * @return List<PCategory>
	 */
	public List<PCategory> getCategoryListByBoardType(String boardType) {
	    return pCategoryMapper.getCategoryListByBoardType(boardType);
	}
	
	
	/**
	 * 자주찾는질문 카테고리 조회
	 * @return List<PCategory>
	 */
	public List<PCategory> getFaqCategoryList() {
		List<PCategory> faqCategoryList = pCategoryMapper.getFaqCategoryList();
		return faqCategoryList;
	}
	
	/**
	 * 카테고리 수정
	 * @param pCategory
	 */
	public void categoryModify(PCategory pCategory) {
		pCategoryMapper.categoryModify(pCategory);
	}
	
	
	/**
	 * 카테고리 추가
	 * @param pCategory
	 */
	public void categoryAdd(PCategory pCategory) {
		pCategoryMapper.categoryAdd(pCategory);
	}
	




}
