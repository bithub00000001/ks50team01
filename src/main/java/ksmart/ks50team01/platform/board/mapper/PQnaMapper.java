package ksmart.ks50team01.platform.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ksmart.ks50team01.platform.board.dto.PAnswer;
import ksmart.ks50team01.platform.board.dto.PQna;

@Mapper
public interface PQnaMapper {
	
	// 1:1문의 목록 조회
	List<PQna> getQnaList();

	// 1:1문의 번호로 1:1문의 상세 조회
	PQna getQnaDetailByNum(String qnaNum);

	// 1:1문의 답변 저장
	void updateQnaWithAnswererId(@Param("qnaNum") String qnaNum, @Param("ansRegId") String ansRegId);
	void answerSave(PAnswer pAnswer);

	// 카테고리별 1:1문의 목록 조회
	List<PQna> getQnaListByCategory(String category);
	
	
}
