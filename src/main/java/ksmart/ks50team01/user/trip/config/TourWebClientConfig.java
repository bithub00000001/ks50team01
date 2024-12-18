package ksmart.ks50team01.user.trip.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class TourWebClientConfig {

	@Bean("tourWebClient")  // Bean 이름 지정
	public WebClient webClient() {
		return WebClient.builder()
			.baseUrl("https://apis.data.go.kr/B551011/KorService1")
			.build();
	}
}
