package com.gr.comcome.account.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gr.comcome.account.model.AccountService;
import com.gr.comcome.account.model.AccountVO;
import com.gr.comcome.account.model.HashVO;
import com.gr.comcome.common.HashingUtil;
import com.gr.comcome.login.model.LoginService;

@Controller
@RequestMapping("/account")
public class AccountController {	
	private static final Logger logger
	=LoggerFactory.getLogger(AccountController.class);
	
	private final AccountService accountService;
	private HashingUtil hashingUtil;
	
	
	
	//DI - 생성자에 의한 종속객체 주입 
	@Autowired
	public AccountController(AccountService accountService, HashingUtil hashingUtil) {
		this.accountService = accountService;
		this.hashingUtil = hashingUtil;
		logger.info("생성자주입!!");
	}

	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String write_get() {
		logger.info("등록 화면");
		
		return "account/register";
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String register_post(@ModelAttribute AccountVO accountVo,
							@ModelAttribute HashVO hashVo,
							@RequestParam String pwd) {
		//1
		logger.info("등록 처리, 파라미터 vo={}", accountVo);
		
		//2
		int cnt=accountService.insertAccount(accountVo);
		logger.info("계정 등록 결과, cnt={}", cnt);
		
		//비밀번호 암호화
		String salt = hashingUtil.makeNewSalt();
		//HashVO hashVo = null;
		hashVo.setSalt(salt);
		try {
			String digest = hashingUtil.hashing(pwd, salt);
			hashVo.setDigest(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		cnt=accountService.insertPwd(hashVo);
		logger.info("비밀번호 등록 결과, cnt={}", cnt);
		
		//4
		return "redirect:/account/register";
	}
	
	@RequestMapping(value="/checkUserEmail", method = RequestMethod.GET)
	public String checkemail_get(@RequestParam(required = false) String email){
		//request 세션, response 쿠키	
		logger.info("이메일 중복 체크 화면");
		logger.info("이메일 파라미터, email={}", email);
		return "account/checkUserEmail";
	}
	
//	@RequestMapping(value="/register", method = RequestMethod.GET)
//	public void emailConfirm_post(@ModelAttribute AccountVO accountVo) {
//		
//	}
	
//	@RequestMapping(value="/checkUserEmail", method = RequestMethod.POST)
//	public String checkemail_post(@RequestParam String email,Model model) {
//		
//		logger.info("이메일 파라미터, email={}", email);
//		
//		int cnt=accountService.checkEmail(email);
//		logger.info("이메일 중복체크 결과, cnt={}", cnt);	 
//		model.addAttribute("result", cnt);
//		model.addAttribute("EXIST_EMAIL", accountService.EXIST_EMAIL);
//		model.addAttribute("NON_EXIST_EMAIL", accountService.NON_EXIST_EMAIL);
//		
//		return "account/checkUserEmail";
//		
//	}
	
	@RequestMapping(value="/checkUserEmail", method = RequestMethod.POST)
	public String checkemail_post(@RequestParam String email,Model model) {
		
		logger.info("이메일 파라미터, email={}", email);
		
		int cnt=accountService.checkEmail(email);
		logger.info("이메일 중복체크 결과, cnt={}", cnt);	 
		model.addAttribute("result", cnt);
		model.addAttribute("EXIST_EMAIL", accountService.EXIST_EMAIL);
		model.addAttribute("NON_EXIST_EMAIL", accountService.NON_EXIST_EMAIL);
		
		return "account/checkUserEmail";
		
	}

}
