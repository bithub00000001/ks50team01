package ksmart.ks50team01.platform.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.platform.board.dto.PCategory;
import ksmart.ks50team01.platform.board.mapper.PCategoryMapper;
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

	//public List<PCategory> getCategoryListByBoardType(String boardType) {
		//List<PCategory> categorListByBoardType = pCategoryMapper.getCategoryListByBoardType(boardType);
	    //return categorListByBoardType;
	//}
	
	

	
	/**
	 * 커뮤니티 카테고리 조회
	 * @return List<PCategory>
	 */
	public List<PCategory> getCommunityCategoryList() {
		List<PCategory> communityCateList = pCategoryMapper.getCommunityCategoryList();
		return communityCateList;
	}
	
	/**
	 * 공지사항 카테고리 조회
	 * @return List<PCategory>
	 */
	public List<PCategory> getNoticeCategoryList(){
		List<PCategory> noticeCateList = pCategoryMapper.getNoticeCategoryList();
		return noticeCateList;
	}
	
	
	/**
	 * 자주찾는질문 및 1:1문의 카테고리 조회
	 * @return List<PCategory>
	 */
	public List<PCategory> getFaqCategoryList() {
		List<PCategory> faqCateList = pCategoryMapper.getFaqCategoryList();
		return faqCateList;
	}
	

	/**
	 * 신고 카테고리 조회
	 * @return List<PCategory>
	 */
	public List<PCategory> getReportCategoryList() {
		List<PCategory> reportCateList = pCategoryMapper.getReportCategoryList();
		return reportCateList;
	}
	
	
	/**
	 * 카테고리 등록
	 * @param pCategory
	 */
	public void categoryAdd(PCategory pCategory) {
		pCategoryMapper.categoryAdd(pCategory);
	}
	

	
	/**
	 * 카테고리 수정
	 * @param pCategory
	 */
	public void categoryModify(PCategory pCategory) {
		pCategoryMapper.categoryModify(pCategory);
	}
	
}
