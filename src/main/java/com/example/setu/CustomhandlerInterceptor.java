package com.example.setu;

import java.nio.file.AccessDeniedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Customer interceptor to intercept all request for pre authentication before send request to controller
 * @author dpchn
 *
 */
@Component
public class CustomhandlerInterceptor implements HandlerInterceptor {
	
	@Autowired
	CustomAuthUsingDbOrThirdParty authorize;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(authorize.validate(request.getHeader("X-API-KEY"))) {
			return true;
		}
		throw new AccessDeniedException(null);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {


	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) throws Exception {
	}
}
