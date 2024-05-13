package ksmart.ks50team01.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ksmart.ks50team01.user.interceptor.BreadcrumbInterceptor;
import ksmart.ks50team01.user.interceptor.IndexInterceptor;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

	private final BreadcrumbInterceptor breadcrumbInterceptor;
	private final IndexInterceptor indexInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(breadcrumbInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/js/**")
				.excludePathPatterns("/css/**")
				.excludePathPatterns("/favicon.ico");

		registry.addInterceptor(indexInterceptor);
	}
}
