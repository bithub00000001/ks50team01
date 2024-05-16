package ksmart.ks50team01.user.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.user.board.dto.UFaq;


@Mapper
public interface UFaqMapper {
	
	// 자주찾는 질문 조회
	List<UFaq> getFaqList();

}
