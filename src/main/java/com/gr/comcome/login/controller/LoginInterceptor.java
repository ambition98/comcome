package com.gr.comcome.login.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

	

	private static final Logger logger 
	 = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//컨트롤러 수행 이전에 먼저 수행되는 메서드
		//클라이언트의 요청을 컨트롤ㄹ에 전달하기 전에 호출 
			HttpSession session = request.getSession();
			String email = (String)session.getAttribute("email");
			
			logger.info("preHandle() 호출, email ={}", email);

			if(email == null || email.isEmpty()) {
			 request.setAttribute("msg", "로그인이 필요한 페이지입니다.");
			 request.setAttribute("url", "/admin/login-with-main");
					
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/message");
			dispatcher.forward(request, response);
			logger.info("preHandle() 처리 완료");	
				return false;
				
		    }else {
				return true; //다음 컨트롤러가 수행됨
		    }
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("postHandle() 호출");
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("afterCompletion() 호출");

	}
}
