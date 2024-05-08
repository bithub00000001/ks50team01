package ksmart.ks50team01.user.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class IndexInterceptor implements HandlerInterceptor {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
		ModelAndView modelAndView) throws Exception {
		if (modelAndView != null && !modelAndView.getViewName().startsWith("redirect:")) {
			modelAndView.addObject("isMainPage", request.getRequestURI().equals("/trip"));
		}

		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
}
