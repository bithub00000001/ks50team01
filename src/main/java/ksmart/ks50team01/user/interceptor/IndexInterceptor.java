package ksmart.ks50team01.user.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class IndexInterceptor implements HandlerInterceptor {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	    if (modelAndView != null) { // ModelAndView가 null인 경우 처리
	        String viewName = modelAndView.getViewName();
	        if (viewName != null && !viewName.startsWith("redirect:")) {
			modelAndView.addObject("isMainPage", request.getRequestURI().matches("^/trip/?$"));
	        }

		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	    }
	}
}
