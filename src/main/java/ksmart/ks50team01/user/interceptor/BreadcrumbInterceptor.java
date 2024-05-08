package ksmart.ks50team01.user.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ksmart.ks50team01.user.breadcrumb.Breadcrumb;

@Component
public class BreadcrumbInterceptor implements HandlerInterceptor {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
		ModelAndView modelAndView) throws Exception {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String[] paths = uri.split("/");
		List<Breadcrumb> breadcrumb = new ArrayList<>();
		String url = contextPath;

		Map<String, String> pageNames = Breadcrumb.getPageNames();  // 페이지 이름을 가져오는 함수 참고

		for (String path : paths) {
			if (!path.isEmpty()) {
				url += "/" + path;
				String pageName = pageNames.getOrDefault(path, path);  // 맵에서 페이지 이름 찾기, 없으면 uri 사용
				breadcrumb.add(new Breadcrumb(pageName, url));
			}
		}
		request.setAttribute("breadcrumb", breadcrumb);
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
}


