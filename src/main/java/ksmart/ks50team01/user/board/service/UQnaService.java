package ksmart.ks50team01.user.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.user.board.dto.UQna;
import ksmart.ks50team01.user.board.mapper.UQnaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UQnaService {
	
	private final UQnaMapper uQnaMapper;
	
	/**
	 * 1:1문의 조회
	 * @return List<UQna>
	 */
	public List<UQna> getQnaList(){
		List<UQna> qnaList = uQnaMapper.getQnaList();
		log.info("1:1문의 조회: {}", qnaList);
		return qnaList;
	}

	
	/**
	 * 1:1문의 상세 조회
	 * @return UQna
	 */
	public UQna getQnaByQnaNum(String qnaNum) {
		UQna qnaDetail = uQnaMapper.getQnaByQnaNum(qnaNum);
		log.info("getQnaByQnaNum: {}", qnaDetail);
		return qnaDetail;
	}


	
}
