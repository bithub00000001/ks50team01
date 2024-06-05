package ksmart.ks50team01.user.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.user.board.dto.UFaq;
import ksmart.ks50team01.user.board.mapper.UFaqMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UFaqService {
	
	private final UFaqMapper uFaqMapper;

	
	/**
	 * 자주찾는 질문 조회
	 * @return List<UFaq>
	 */
	public List<UFaq> getFaqList() {
		return uFaqMapper.getFaqList();
	}

}
