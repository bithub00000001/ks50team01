package ksmart.ks50team01.user.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.user.board.dto.UComment;
import ksmart.ks50team01.user.board.mapper.UCommentMapper;
import ksmart.ks50team01.user.board.mapper.UCommunityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UCommentService {
	private final UCommentMapper uCommentMapper;
	private final UCommunityMapper uCommunityMapper;

	public UComment saveComment(UComment uComment) {
		uCommentMapper.saveComment(uComment);
		return uComment;  // 댓글 저장 후 저장된 객체 반환
	}

	public List<UComment> findAllByPostNum(String postNum) {
		return uCommentMapper.findAllByPostNum(postNum);  // 댓글 목록 반환
	}
}
