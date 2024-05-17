package ksmart.ks50team01.user.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.user.board.dto.UQna;

@Mapper
public interface UQnaMapper {

	// 1:1문의 조회
	List<UQna> getQnaList();

}
