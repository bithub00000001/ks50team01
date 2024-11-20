package ksmart.ks50team01.common.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

/**
 * web MVC 설정
 * 문서 관련 웹 리소스 설정
 */
public class WebDocumentConfig implements WebMvcConfigurer {

	@Value("${document.storage.path}")
	private String documentStoragePath;

	/**
	 * 문서 파일 접근을 위한 리소스 핸들러 설정
	 * 실제 파일 시스템의 경로를 웹 경로에 매핑
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/document/**")
			.addResourceLocations("file:" + documentStoragePath)
			.setCacheControl(CacheControl.maxAge(1, TimeUnit.HOURS))
			.resourceChain(true)
			.addResolver(new PathResourceResolver());
	}

	/**
	 * CORS 설정
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
			.allowedOrigins("*")
			.allowedMethods("GET", "POST")
			.maxAge(3600);
	}

	/**
	 * 파일 다운로드를 위한 Content Negotiation 설정
	 */
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.mediaType("pdf", MediaType.APPLICATION_PDF)
			.mediaType("zip", MediaType.APPLICATION_OCTET_STREAM);
	}
}
