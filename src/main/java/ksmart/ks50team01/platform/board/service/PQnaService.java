package ksmart.ks50team01.platform.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.platform.board.dto.PQna;
import ksmart.ks50team01.platform.board.mapper.PQnaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PQnaService {
	
	private final PQnaMapper pQnaMapper;
	
	/**
	 * 1:1문의 조회
	 * @return List<PQna>
	 */
	public List<PQna> getQnaList(){
		List<PQna> qnaList = pQnaMapper.getQnaList();
		log.info("1:1문의 조회 결과: {}", qnaList);
		return qnaList;
	}
}
