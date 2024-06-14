package ksmart.ks50team01.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ksmart.ks50team01.user.interceptor.AuthenticationInterceptor;
import ksmart.ks50team01.user.interceptor.BreadcrumbInterceptor;
import ksmart.ks50team01.user.interceptor.IndexInterceptor;
import ksmart.ks50team01.user.interceptor.RedirectAttributesInterceptor;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

	private final BreadcrumbInterceptor breadcrumbInterceptor;
	private final IndexInterceptor indexInterceptor;

	private final RedirectAttributesInterceptor redirectAttributesInterceptor;
	private final AuthenticationInterceptor authenticationInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 동적으로 브레드크럼을 생성하도록 등록
		registry.addInterceptor(breadcrumbInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/js/**")
				.excludePathPatterns("/css/**")
				.excludePathPatterns("/favicon.ico");

		// 첫 페이지에 배너를 제외하도록 등록
		registry.addInterceptor(indexInterceptor);

		// 로그인 상태일때만 여행 계획 메뉴 접근이 가능하도록 조정
		registry.addInterceptor(authenticationInterceptor)
			.addPathPatterns(
				"/trip/detail/**",
				"/trip/list/**",
				"/trip/schedule/**"
			)
			.excludePathPatterns(
				"/trip/login",
				"/trip/register",
				"/trip/logout",
				"/trip", "/trip/",
				"/trip/tourInfo/**",
				"/trip/board",
				"/tourapi/**"
			);
	}
}
