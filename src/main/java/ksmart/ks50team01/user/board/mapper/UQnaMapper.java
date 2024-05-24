package ksmart.ks50team01.user.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.user.board.dto.UCategory;
import ksmart.ks50team01.user.board.dto.UQna;

@Mapper
public interface UQnaMapper {

	// 1:1문의 조회
	List<UQna> getQnaList();

	// 1:1문의 상세목록 조회
	UQna getQnaByQnaNum(String qnaNum);

	// 1:1문의 DB에 저장
	void insertQna(UQna qna);

	// QNA 카테고리 조회
	List<UCategory> getQnaCateList();

	// 1:1문의 수정
	void qnaModify(UQna uQna);

	// 1:1문의 삭제
	void qnaDelete(String qnaNum);

	// 주어진 번호에 해당하는 게시글 정보 조회
	UQna getQnaInfoByNum(String qnaNum);

}
