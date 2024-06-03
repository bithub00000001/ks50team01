package ksmart.ks50team01.platform.trip.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class PWebClientConfig {
	// Tour API baseURL 설정 메서드
	@Bean
	public WebClient tourApiWebClient() {
		return WebClient.builder()
			.baseUrl("https://apis.data.go.kr/B551011/KorService1")
			.build();
	}

	// T map API baseURL 설정 메서드
	@Bean
	public WebClient tMapApiWebClient() {
		return WebClient.builder()
			.baseUrl("https://apis.openapi.sk.com/tmap/routes")
			.build();
	}
}
