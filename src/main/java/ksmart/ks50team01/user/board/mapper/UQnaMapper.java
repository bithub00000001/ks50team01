package ksmart.ks50team01.user.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.user.board.dto.UCategory;
import ksmart.ks50team01.user.board.dto.UQna;

@Mapper
public interface UQnaMapper {

	// 1:1문의 조회
	List<UQna> getQnaList();
	
	// QNA 카테고리 조회
	List<UCategory> getQnaCateList();

	// 1:1문의 상세목록 조회
	UQna getQnaDetail(String qnaNum);
	
	// 해당 번호의 1:1문의 조회
	UQna getQnaInfoByNum(String qnaNum);

	// 1:1문의 작성
	void qnaAdd(UQna qna);

	// 1:1문의 수정
	void qnaModify(UQna uQna);

	// 1:1문의 삭제
	void qnaRemove(String qnaNum);

	List<Map<String, Object>> getQnaListByPage(Map<String, Object> paramMap);

	int getQnaListRowCnt();


}
