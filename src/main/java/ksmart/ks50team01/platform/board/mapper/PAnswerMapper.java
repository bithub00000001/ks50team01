package ksmart.ks50team01.platform.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ksmart.ks50team01.platform.board.dto.PAnswer;

@Mapper
public interface PAnswerMapper {
	

	List<PAnswer> getAnswerListByQnaNum(@Param("qnaNum") String qnaNum);
}
