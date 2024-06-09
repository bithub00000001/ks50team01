package ksmart.ks50team01.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackages = "ksmart.ks50team01.user.trip")
public class UserExceptionController {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleOtherExceptions(Exception ex, HttpServletRequest request, Model model) {
		// 현재 요청의 URL을 가져와서 모델에 추가
		String url = request.getRequestURI();
		model.addAttribute("exception", ex);
		model.addAttribute("url", url);
		return "user/error/error"; // 에러 페이지로 이동 혹은 다른 처리
	}
}
