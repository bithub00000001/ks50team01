package ksmart.ks50team01.platform.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.platform.board.dto.PCategory;

@Mapper
public interface PCategoryMapper {
	
	// 카테고리 조회
	List<PCategory> getCategoryList();

}
