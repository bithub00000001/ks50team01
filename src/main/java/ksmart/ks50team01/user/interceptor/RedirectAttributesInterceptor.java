package ksmart.ks50team01.user.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

@Component
public class RedirectAttributesInterceptor implements WebRequestInterceptor {

	@Override
	public void preHandle(WebRequest request) {
		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
		request.setAttribute("REDIRECT_ATTRIBUTES",redirectAttributes, RequestAttributes.SCOPE_REQUEST);
	}

	@Override
	public void postHandle(WebRequest request, ModelMap model) throws Exception {

	}

	@Override
	public void afterCompletion(WebRequest request, Exception ex) throws Exception {

	}
}
