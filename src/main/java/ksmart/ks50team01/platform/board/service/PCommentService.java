package ksmart.ks50team01.platform.board.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.platform.board.mapper.PCommentMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PCommentService {
	
	private final PCommentMapper pCommentMapper;

}
