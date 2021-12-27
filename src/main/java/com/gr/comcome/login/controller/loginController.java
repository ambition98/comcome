package com.gr.comcome.login.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gr.comcome.login.model.AccountVO;
import com.gr.comcome.login.model.LoginService;

@Controller
@RequestMapping("/login")
public class loginController {

	private Logger logger  
	 = LoggerFactory.getLogger(loginController.class);
	
	private LoginService loginService;
	
	@Autowired
	public void loginController(LoginService loginService) {
		this.loginService = loginService;
	}
	
	//http://localhost:9091/comcome/login/login-form
	@GetMapping("/login-form")
	public String loginForm() {
		logger.info("로그인 화면");
		return "/login/loginForm";
	}
	
	//http://localhost:9091/comcome/login/sign-in
	@PostMapping("/sign-in")
	public String signIn(
			@RequestParam(required = false) String email, 
			@RequestParam(required = false) String password, 
			@RequestParam(required = false) String chkSave, 
			HttpServletRequest request, 
			HttpServletResponse response, 
			Model model
			) {
		
		if(email == null || password==null || email == "" || password == "") {
			model.addAttribute("msg", "이메일/비밀번호를 입력해주세요");
			model.addAttribute("url", "/login/login-form");
			return "common/message";
		}
		
		logger.info("로그인 처리, 파라미터 "
				+ "email={}, password={}, chkSave={}", email, password, chkSave);
		
		String msg ="로그인 처리 실패!", url ="/login/login-form";
		int result = loginService.loginCheck(email, password);
		if(result == loginService.LOGIN_OK) {
			AccountVO accVo = loginService.selectByEmail(email);
			
			//[1] 세션에 아이디 저장 
			HttpSession session = request.getSession();
			session.setAttribute("email", accVo.getEmail());
			session.setAttribute("name", accVo.getName());
			
			//[2] 쿠키에 저장 - 아이디 저장하기 체크된 경우
			Cookie ck = new Cookie("ck_email", accVo.getEmail());
			ck.setPath("/");
			
			if(chkSave !=null) {
				ck.setMaxAge(1000*24*60*60);
				response.addCookie(ck);
			}else {
				ck.setMaxAge(0);  //쿠키 제거
				response.addCookie(ck);	
			}
			
			msg = accVo.getName()+" 님 로그인 되었습니다";
			url ="/index";
		}else if(result == loginService.DISAGREE_PWD) {
			msg ="비밀번호가 일치하지 않습니다.";
		}else if(result == loginService.EMAIL_NONE) {
			msg = "해당 이메일이 존재하지 않습니다";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		
		
		
		return "common/message";
		
	}
}
