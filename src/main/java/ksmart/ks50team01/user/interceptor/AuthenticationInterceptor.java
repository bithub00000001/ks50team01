package ksmart.ks50team01.user.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
		Exception {
		HttpSession session = request.getSession();
		if (session.getAttribute("loginId") == null) {
			// Flash attribute를 사용하여 메시지 설정
			SessionFlashMapManager flashMapManager = new SessionFlashMapManager();
			FlashMap flashMap = new FlashMap();
			flashMap.put("message", "로그인이 필요합니다.");
			flashMapManager.saveOutputFlashMap(flashMap, request, response);
			response.sendRedirect("/trip");
			return false;

		}
		return true;
	}
}
