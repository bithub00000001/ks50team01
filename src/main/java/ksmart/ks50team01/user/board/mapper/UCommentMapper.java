package ksmart.ks50team01.user.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ksmart.ks50team01.user.board.dto.UComment;

@Mapper
public interface UCommentMapper {

	void saveComment(UComment uComment);

	List<UComment> findAllByPostNum(@Param("postNum") String postNum);
	


}
