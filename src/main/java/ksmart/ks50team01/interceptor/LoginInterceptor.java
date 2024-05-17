package ksmart.ks50team01.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("preHandle() 메소드 호출");

        HttpSession session = request.getSession();
        String loginLevel = (String) session.getAttribute("loginLevel");

        if (loginLevel == null || !loginLevel.equals("uln_001")) {
            log.info("로그인되어 있지 않거나 관리자가 아닙니다.");
            response.sendRedirect("/platform"); // 로그인 페이지로 리다이렉트
            return false;
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        log.info("postHandle() 메소드 호출");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        log.info("afterCompletion() 메소드 호출");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}