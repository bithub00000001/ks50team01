package ksmart.ks50team01.documentation.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

/**
 * WebClient 설정
 */
@Configuration
public class DocumentWebClientConfig {

	@Value("${document.api.base-url}")
	private String baseUrl;

	@Bean
	public WebClient documentWebClient() {
		HttpClient httpClient = HttpClient.create()
			.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 60000)  // 연결 타임아웃 60초
			.responseTimeout(Duration.ofSeconds(300))  // 응답 타임아웃 300초
			.doOnConnected(conn -> conn
				.addHandlerLast(new ReadTimeoutHandler(300))  // 읽기 타임아웃 300초
				.addHandlerLast(new WriteTimeoutHandler(300))); // 쓰기 타임아웃 300초

		return WebClient.builder()
			.baseUrl(baseUrl)
			.clientConnector(new ReactorClientHttpConnector(httpClient))
			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.codecs(configurer -> configurer
				.defaultCodecs()
				.maxInMemorySize(100 * 1024 * 1024)) // 100MB로 증가
			.build();
	}
}
