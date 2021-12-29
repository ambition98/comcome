package com.gr.comcome.login.controller;

import java.security.NoSuchAlgorithmException;

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
import com.gr.comcome.login.model.HashVO;
import com.gr.comcome.login.model.LoginService;
import com.gr.comcome.util.HashingUtil;

@Controller
@RequestMapping("/login") 
public class loginController {

	private Logger logger  
	 = LoggerFactory.getLogger(loginController.class);
	
	
	private LoginService loginService;
	private HashingUtil hashingUtil;
	
	
	@Autowired
    public loginController( LoginService loginService, HashingUtil hashingUtil) {
		this.loginService = loginService;
		this.hashingUtil = hashingUtil;
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
	
	//http://localhost:9091/comcome/login/find-email
	//아이디 찾기 페이지 뷰
	@GetMapping("/find-email")
	public String findEmail_get() {
		logger.info("이메일 찾기 화면");
		return "login/findEmail";
	}
	
	@PostMapping("/find-email")
	public String findEmail_post(
			@RequestParam(required = false) String name, 
			@RequestParam(required = false) String tel, 
			Model model) {
		
		if(name == null || tel == null || name == ""|| tel == "") {
			model.addAttribute("msg", "이름/전화번호를 입력해주세요");
			model.addAttribute("url","/login/find-email" );
			return "common/message";
		}
		
		logger.info("이메일 찾기 처리, name={}, tel={}",name, tel);
		
		int result = loginService.FindEmailCheck(name, tel);
		String msg = " ", url ="/login/find-email";
		if(result == loginService.LOGIN_OK) {
			//올바른 회원 정보이면, 이메일 알려주기
			String dbEmail = loginService.selectEmailByName(name);
			msg = "당신의 Email은 "+dbEmail+" 입니다.";
			url = "/login/login-form";
			
		}else if(result == loginService.DISAGREE_TEL) {
			//잘못된 전화번호이면 
			msg = "전화번호가 일치하지 않습니다.";
		}else if(result == loginService.EMAIL_NONE) {
			msg = "회원정보가 존재하지 않습니다.";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		
		
		return "common/message";
	}
	
	//http://localhost:9091/comcome/login/find-password
	//비밀번호 찾기 화면 
	@GetMapping("/find-password")
	public String findPassword_get() {
		logger.info("비밀번호 찾기 화면");
		return "/login/findPassword";
	}
	
	
	//http://localhost:9091/comcome/login/find-password?email=123@naver.com
	@PostMapping("/find-password")
	public String findPassword_post(
			@RequestParam(required = false) String email
			,Model model
			) {
		
		if(email ==null||email =="" ||email.isEmpty()) {
			model.addAttribute("msg", "이메일을 입력해주세요");
			model.addAttribute("url", "/login/find-password");
			return "common/message";
		}
		
		
		logger.info("비밀번호 찾기 처리 , email={}",email);
		
		//인증번호 불러오기 
		String veriCode = getVerifiedCode();
		int result = loginService.sendPassword(email, veriCode);
		
		String msg = "이메일 전송이 실패하였습니다", url ="/login/find-password";
		//이메일 전송 성공시에 
		if(result == loginService.SEND_EMAIL) {
			model.addAttribute("msg", "해당 이메일로 인증번호가 전송되었습니다.");
			model.addAttribute("veriCode", veriCode);
			model.addAttribute("email", email);
			return "login/verifiedCodeForm";
		}
		
		if(result == loginService.EMAIL_NONE) {
			msg ="존재하지 않는 이메일입니다";
		}else if (result == loginService.FAIL_TO_SEND_EMAIL) {
			msg = "이메일 전송이 실패하였습니다.";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		
		return "common/message";
	}
	
	// 인증번호 생성
		public String getVerifiedCode() {
			char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
					'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

			String str = "";

			int idx = 0;
			for (int i = 0; i < 10; i++) {
				idx = (int) (charSet.length * Math.random());
				str += charSet[idx];
			}
			return str;
		}
		
		@GetMapping("/verified")
		public String verified_get() {
			return "login/verifiedCodeForm";
		}
		
		@PostMapping("/verified")
		public String verified_post(
				@RequestParam(required = false) String yourveriCode,
				@RequestParam String veriCode,
				@RequestParam String email
				,Model model
				) {
			if(yourveriCode == null || yourveriCode.equals("") ||yourveriCode.isEmpty()) {
				model.addAttribute("msg", "인증번호를 입력해주세요");
				model.addAttribute("url", "/login/verified");
				return "common/message";
			}
			logger.info("인증번호 확인 처리, yourveriCode={}, veriCode={}",yourveriCode, veriCode);
			
			
			if(yourveriCode.equals(veriCode)) {
				model.addAttribute("msg", "본인 인증에 성공하였습니다");
				model.addAttribute("email", email);
				return "login/updatePwd";
			}
			String msg = "본인 인증에 실패하였습니다",url = "/login/find-password";
			
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			
			return "common/message";
			
		}
		
		@GetMapping("/update-pwd")
		public String updatePwd_get() {
			logger.info("비밀번호 재설정 화면");
			return "login/updatePwd";
		}
		
		@PostMapping("/update-pwd")
		public String updatePwd(
				@RequestParam String email,
				@RequestParam(required = false) String password,
				@RequestParam(required = false) String passwordCk,
				Model model
				) throws NoSuchAlgorithmException {
			if(password == null || password.isEmpty() || passwordCk == null || passwordCk.isEmpty()) {
				model.addAttribute("msg", "비밀번호/비밀번호 확인을 입력해주세요");
				model.addAttribute("url", "/login/update-pwd");
				return "common/message";
			}else if(!password.equals(passwordCk)) {
				model.addAttribute("msg", "비밀번호가 일치하지 않습니다. 다시 입력하세요");
				model.addAttribute("url", "/login/update-pwd");
				return "common/message";
			}else if(password.equals(passwordCk)) {
				//비밀번호 재설정 ! 
				//이메일을 통해서 account_no를 가져온다! 
				AccountVO accountVO = loginService.selectByEmail(email);
				
				//비밀번호 재설정 ! 
				//salt 만들기 ..salt 
				String salt = hashingUtil.makeNewSalt();
				//암호화 된 digest .. update hash set salt = salt , digest = saltPwd where account_no =?
			    String digest = hashingUtil.hashing(password, salt);
			    
			    HashVO hashvo = new HashVO();
			    hashvo.setAccount_no(accountVO.getAccountNo());
			    hashvo.setDigest(digest);
			    hashvo.setSalt(salt);
			    
			    String msg = "비밀번호 재설정이 실패하였습니다", url ="/login/find-password";
			    
			    int result = loginService.updatePassword(hashvo);
			    if(result>0) {
			    	msg = "비밀번호가 성공적으로 변경되었습니다";
			    	url = "/login/login-form";
			    }
				model.addAttribute("msg", msg);
				model.addAttribute("url", url);
				return "common/message";
				
			}
			
			
			
			return "common/message";
		}
	
	
}
