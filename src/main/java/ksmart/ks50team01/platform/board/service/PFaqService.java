package ksmart.ks50team01.platform.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.platform.board.dto.PFaq;
import ksmart.ks50team01.platform.board.mapper.PFaqMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PFaqService {
	
	private final PFaqMapper pFaqMapper;
	
	/**
	 * 자주찾는 질문 조회
	 * @return List<PFaq>
	 */
	public List<PFaq> getFaqList(){
		return pFaqMapper.getFaqList();
	}

}
