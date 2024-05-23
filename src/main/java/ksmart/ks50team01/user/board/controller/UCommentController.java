package ksmart.ks50team01.user.board.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import ksmart.ks50team01.user.board.dto.UComment;
import ksmart.ks50team01.user.board.service.UCommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping(value = "/comment")
@RequiredArgsConstructor
@Slf4j
public class UCommentController {
	
	private final UCommentService uCommentService;
	
	@PostMapping("/save")
	public List<UComment> saveComment(@RequestBody UComment uComment) {
		log.info("uComment : {}", uComment);
		
		UComment saveResult = uCommentService.saveComment(uComment);
		if(saveResult != null) {
			// 작성 성공하면 댓글 목록을 가져와서 리턴
			List<UComment> commentList = uCommentService.findAllByPostNum(uComment.getPostNum());
			return commentList;
		} else {
			throw new RuntimeException("댓글 작성에 실패했습니다.");
		}
	}
}
