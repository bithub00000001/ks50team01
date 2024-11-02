package ksmart.ks50team01.chimney.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * WebClient 설정 클래스
 */
@Configuration
public class CleanWebClientConfig {

	/**
	 * WebClient 빌더를 빈으로 등록
	 *
	 * @return WebClient 인스턴스
	 */
	@Bean("cleanWebClient")  // Bean 이름 지정
	public WebClient webClient() {
		return WebClient.builder()
			.baseUrl("http://apis.data.go.kr")
			.build();
	}
}
