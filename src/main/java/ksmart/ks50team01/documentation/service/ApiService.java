package ksmart.ks50team01.documentation.service;

import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 법령정보 API 호출을 위한 서비스
 * 대기오염공정시험기준 문서의 최신 정보를 조회하는 기능 제공
 *
 * @author Hanbit Kang
 * @version 1.0
 * @since 2024-11-20
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ApiService {

	private final WebClient documentWebClient;

	/**
	 * 대기오염공정시험기준 최신 문서 정보 조회
	 * 법령정보 API를 호출하여 문서의 메타데이터와 첨부파일 정보를 가져옴
	 *
	 * @return Map<String, Object> API 응답 데이터
	 *         - AdmRulService: 행정규칙 서비스 정보
	 *         - 첨부파일: 문서 첨부파일 정보 (링크, 파일명 등)
	 * @throws WebClientResponseException API 호출 실패 시
	 * @throws RuntimeException 응답 데이터 처리 실패 시
	 */
	public Map<String, Object> fetchLatestDocumentInfo() {
		return documentWebClient.get()
			.uri("/DRF/lawService.do?OC=ryuha117&target=admrul&type=JSON&LM=대기오염공정시험기준")
			.retrieve()
			.bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
			.block();
	}
}
