package ksmart.ks50team01.platform.trip.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class PWebClientConfig {
	@Bean
	public WebClient tourApiWebClient() {
		return WebClient.builder()
			.baseUrl("https://apis.data.go.kr/B551011/KorService1")
			.build();
	}

	@Bean
	public WebClient tMapApiWebClient() {
		return WebClient.builder()
			.baseUrl("https://apis.openapi.sk.com/tmap/routes")
			.build();
	}
}
