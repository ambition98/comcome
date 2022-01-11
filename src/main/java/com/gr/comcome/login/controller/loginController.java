package com.gr.comcome.login.controller;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gr.comcome.account.model.AccountVO;
import com.gr.comcome.account.model.HashVO;
import com.gr.comcome.admin.model.AdminService;
import com.gr.comcome.admin.model.AdminServiceImpl;
import com.gr.comcome.admin.model.AdminVO;
import com.gr.comcome.admin.model.NoticeVO;
import com.gr.comcome.common.HashingUtil;
import com.gr.comcome.login.model.KakaoProfile;
import com.gr.comcome.login.model.LoginService;
import com.gr.comcome.login.model.OauthToken;

@Controller
@RequestMapping("/login")
public class loginController {

	private Logger logger = LoggerFactory.getLogger(loginController.class);

	private AdminService adminService;
	private LoginService loginService;
	private HashingUtil hashingUtil;

	@Autowired
	public loginController(AdminService adminService, LoginService loginService, HashingUtil hashingUtil) {
		this.adminService = adminService;
		this.loginService = loginService;
		this.hashingUtil = hashingUtil;
	}

	// http://localhost:9091/comcome/login/popup3
	@GetMapping("/popup3")
	public String popup3(Model model) {
		NoticeVO vo = adminService.selectRecentNotice();
		String content = vo.getContent();
		String contentbr = content.replaceAll("\n", "<br />");
		String title = vo.getTitle();
		String titlebr = title.replaceAll("\n", "<br />");
		model.addAttribute("content", contentbr);
		model.addAttribute("title", titlebr);
		model.addAttribute("vo", vo);
		return "/admin/popup3";
	}

	/*
	 * // http://localhost:9091/comcome/login/index
	 * 
	 * @GetMapping("/index") public String index(Model model) {
	 * logger.info("인덱스 화면");
	 * 
	 * NoticeVO vo = adminService.selectRecentNotice(); String content =
	 * vo.getContent(); String contentbr = content.replaceAll("\n", "<br />");
	 * String title = vo.getTitle(); String titlebr = title.replaceAll("\n",
	 * "<br />"); model.addAttribute("content", contentbr);
	 * model.addAttribute("title", titlebr); model.addAttribute("vo", vo);
	 * logger.info("팝업 화면 처리 ,vo={}", vo.toString()); return "/index"; }
	 */

	// http://localhost:9091/comcome/login/login-form
	@GetMapping("/login-form")
	public String loginForm() {
		logger.info("로그인 화면");
		return "/login/loginForm";
	}

	// localhost:9091/comcome/login/logout
	@RequestMapping("/logout")
	public String logout(HttpSession session, Model model) {
		logger.info("로그아웃 처리");

		// session.invalidate();
		session.removeAttribute("email");
		session.removeAttribute("accountNo");
		session.removeAttribute("name");
		session.removeAttribute("address");
		session.removeAttribute("tel");
		session.removeAttribute("cardNo");

		model.addAttribute("msg", "로그아웃 되었습니다.");
		model.addAttribute("url", "/");
		return "/common/message";

	}

	// http://localhost:9091/comcome/login/sign-in
	@PostMapping("/sign-in")
	public String signIn(@RequestParam(required = false) String email, @RequestParam(required = false) String password,
			@RequestParam(required = false) String chkSave, HttpServletRequest request, HttpServletResponse response,
			Model model) throws NoSuchAlgorithmException {

		if (email == null || password == null || email == "" || password == "") {
			model.addAttribute("msg", "이메일/비밀번호를 입력해주세요");
			model.addAttribute("url", "/login/login-form");
			return "/common/message";
		}

		logger.info("로그인 처리, 파라미터 " + "email={}, password={}, chkSave={}", email, password, chkSave);

		String msg = "로그인 처리 실패!", url = "/login/login-form";

		logger.info("일반 계정 처리");
		int result = loginService.loginCheck(email, password);
		if (result == loginService.LOGIN_OK) {

			AccountVO accVo = loginService.selectByEmail(email);

			// [1] 세션에 아이디 저장
			HttpSession session = request.getSession();
			session.setAttribute("email", accVo.getEmail());
			session.setAttribute("name", accVo.getName());
			session.setAttribute("accountNo", accVo.getAccountNo());
			session.setAttribute("address", accVo.getAddress());
			session.setAttribute("tel", accVo.getTel());
			session.setAttribute("cardNo", accVo.getCardNo());

			// [2] 쿠키에 저장 - 아이디 저장하기 체크된 경우
			Cookie ck = new Cookie("ck_email", accVo.getEmail());
			ck.setPath("/");

			if (chkSave != null) {
				ck.setMaxAge(1000 * 24 * 60 * 60);
				response.addCookie(ck);
			} else {
				ck.setMaxAge(0); // 쿠키 제거
				response.addCookie(ck);
			}

			msg = accVo.getName() + " 님 로그인 되었습니다";
			url = "/";
		} else if (result == loginService.DISAGREE_PWD) {
			msg = "비밀번호가 일치하지 않습니다.";
		} else if (result == loginService.EMAIL_NONE) {
			msg = "해당 이메일이 존재하지 않습니다";
		}

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		return "/common/message";

	}

	// http://localhost:9091/comcome/login/find-email
	// 아이디 찾기 페이지 뷰
	@GetMapping("/find-email")
	public String findEmail_get() {
		logger.info("이메일 찾기 화면");
		return "login/findEmail2";
	}

	@PostMapping("/find-email")
	public String findEmail_post(@RequestParam(required = false) String name,
			@RequestParam(required = false) String tel, Model model) {

		if (name == null || tel == null || name == "" || tel == "") {
			model.addAttribute("msg", "이름/전화번호를 입력해주세요");
			model.addAttribute("url", "/login/find-email");
			return "common/message";
		}

		logger.info("이메일 찾기 처리, name={}, tel={}", name, tel);

		int result = loginService.FindEmailCheck(name, tel);
		String msg = " ", url = "/login/find-email";
		if (result == loginService.LOGIN_OK) {
			// 올바른 회원 정보이면, 이메일 알려주기

			String dbEmail = loginService.selectEmailByName(name);
			if (dbEmail.length() > 17) {
				// @의 index 찾기
				int index = dbEmail.indexOf('@');
				// 뒷자리 추출
				String secretion = dbEmail.substring(index - 4, index);
				// ****
				String star = "";
				for (int i = 0; i < secretion.length(); i++) {
					star += "*";
				}
				// 뒷자리만 *로 바꾸기
				String dbFinalEmail = dbEmail.replace(secretion, star);

				logger.info("index={}, secretion={}, star={}, dbFinalEmail={}", index, secretion, star, dbFinalEmail);

				msg = "당신의 Email은 " + dbFinalEmail + " 입니다.";
				url = "/login/login-form";
			}else {
				msg = "당신의 Email은 " + dbEmail + " 입니다.";
				url = "/login/login-form";
			}
		} else if (result == loginService.DISAGREE_TEL) {
			// 잘못된 전화번호이면
			msg = "전화번호가 일치하지 않습니다.";
		} else if (result == loginService.EMAIL_NONE) {
			msg = "회원정보가 존재하지 않습니다.";
		}

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		return "common/message";
	}

	// http://localhost:9091/comcome/login/find-password
	// 비밀번호 찾기 화면
	@GetMapping("/find-password")
	public String findPassword_get() {
		logger.info("비밀번호 찾기 화면");
		return "/login/findPassword2";
	}

	// http://localhost:9091/comcome/login/find-password?email=123@naver.com
	@PostMapping("/find-password")
	public String findPassword_post(@RequestParam(required = false) String email, Model model) {

		if (email == null || email == "" || email.isEmpty()) {
			model.addAttribute("msg", "이메일을 입력해주세요");
			model.addAttribute("url", "/login/find-password");
			return "common/message";
		}

		logger.info("비밀번호 찾기 처리 , email={}", email);

		// 인증번호 불러오기
		String veriCode = getVerifiedCode();
		int result = loginService.sendPassword(email, veriCode);

		String msg = "이메일 전송이 실패하였습니다", url = "/login/find-password";
		// 이메일 전송 성공시에
		if (result == loginService.SEND_EMAIL) {
			model.addAttribute("msg", "해당 이메일로 인증번호가 전송되었습니다.");
			model.addAttribute("veriCode", veriCode);
			model.addAttribute("email", email);
			return "login/verifiedCodeForm2";
		}

		if (result == loginService.EMAIL_NONE) {
			msg = "존재하지 않는 이메일입니다";
		} else if (result == loginService.FAIL_TO_SEND_EMAIL) {
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

	// http://localhost:9091/comcome/login/verified
	// 비밀번호 인증번호 화면처리 
	@GetMapping("/verified")
	public String verified_get() {
		return "login/verifiedCodeForm2";
	}

	@PostMapping("/verified")
	public String verified_post(@RequestParam(required = false) String yourveriCode, @RequestParam String veriCode,
			@RequestParam String email, Model model) {
		if (yourveriCode == null || yourveriCode.equals("") || yourveriCode.isEmpty()) {
			model.addAttribute("msg", "인증번호를 입력해주세요");
			model.addAttribute("url", "/login/verified");
			return "common/message";
		}
		logger.info("인증번호 확인 처리, yourveriCode={}, veriCode={}", yourveriCode, veriCode);

		if (yourveriCode.equals(veriCode)) {
			model.addAttribute("msg", "본인 인증에 성공하였습니다");
			model.addAttribute("email", email);
			return "login/updatePwd2";
		}
		String msg = "본인 인증에 실패하였습니다", url = "/login/find-password";

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		return "common/message";

	}


	// http://localhost:9091/comcome/login/update-pwd
	@GetMapping("/update-pwd")
	public String updatePwd_get() {
		logger.info("비밀번호 재설정 화면");
		return "login/updatePwd2";
	}

	@PostMapping("/update-pwd")
	public String updatePwd(@RequestParam String email, @RequestParam(required = false) String password,
			@RequestParam(required = false) String passwordCk, Model model) throws NoSuchAlgorithmException {
		if (password == null || password.isEmpty() || passwordCk == null || passwordCk.isEmpty()) {
			model.addAttribute("msg", "비밀번호/비밀번호 확인을 입력해주세요");
			model.addAttribute("url", "/login/update-pwd");
			return "common/message";
		} else if (!password.equals(passwordCk)) {
			model.addAttribute("msg", "비밀번호가 일치하지 않습니다. 다시 입력하세요");
			model.addAttribute("url", "/login/update-pwd");
			return "common/message";
	  
		} else if (password.equals(passwordCk)) {
			logger.info("비밀번호 재설정 처리");
			// 비밀번호 재설정 !
			// 이메일을 통해서 account_no를 가져온다!
			AccountVO accountVO = loginService.selectByEmail(email);

			logger.info("비밀번호 재설정 처리, accountVO={}", accountVO.toString());
			// 비밀번호 재설정 !
			// salt 만들기 ..salt
			String salt = hashingUtil.makeNewSalt();
			// 암호화 된 digest .. update hash set salt = salt , digest = saltPwd where
			// account_no =?
			String digest = hashingUtil.hashing(password, salt);

			HashVO hashvo = new HashVO();
			hashvo.setAccountNo(accountVO.getAccountNo());
			hashvo.setDigest(digest);
			hashvo.setSalt(salt);

			logger.info("비밀번호 재설정 처리, hashvo={}", hashvo.toString());
			String msg = "비밀번호 재설정이 실패하였습니다", url = "/login/find-password";

			int result = loginService.updatePassword(hashvo);
			logger.info("비밀번호 재설정 처리, result={}", result);
			if (result > 0) {
				msg = "비밀번호가 성공적으로 변경되었습니다";
				url = "/login/login-form";
			}
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			return "common/message";

		}

		return "common/message";
	}

	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(String code, HttpServletRequest request, Model model) { // Data를 리턴 해주는 컨트롤러 함수

		// POST방식으로 key = value 데이터를 요청(카카오쪽으로)

		RestTemplate rt = new RestTemplate();

		// HttpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "228cb11c8786e83ab545482f006fefe1");
		params.add("redirect_uri", "http://localhost:9091/comcome/login/auth/kakao/callback");
		params.add("code", code);

		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

		// Http 요청하기 -post 방식으로 그리고 response 변수의응답 받음
		ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST,
				kakaoTokenRequest, String.class);

		// Gson, Json Simple , ObjectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		OauthToken oauthToken = null;
		try {
			oauthToken = objectMapper.readValue(response.getBody(), OauthToken.class);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		System.out.println("카카오톡  액세스 토큰 : " + oauthToken.getAccess_token());

		RestTemplate rt2 = new RestTemplate();

		// HttpHeader 오브젝트 생성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);

		// Http 요청하기 -post 방식으로 그리고 response 변수의응답 받음
		ResponseEntity<String> response2 = rt2.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST,
				kakaoProfileRequest2, String.class);

		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;

		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		// account 오브젝트 : name, email
		System.out.println("카카오 아이디(번호) : " + kakaoProfile.getId());
		System.out.println("카카오 이메일(번호) : " + kakaoProfile.getKakao_account().getEmail());

		System.out.println("컴컴 서버 유저네임 : " + kakaoProfile.getProperties().nickname);
		System.out.println("컴컴 서버 이메일 : " + kakaoProfile.getKakao_account().getEmail());

		// 가입자 혹은 비가입자 체크해서 처리

		String kakaoUsername = kakaoProfile.getProperties().nickname;
		String kakaoEmail = kakaoProfile.getKakao_account().getEmail();

		// eamil 로 회원 찾기
		int ExistingKEmail = loginService.countEmail(kakaoEmail);
		AccountVO accountVO = new AccountVO();
		// 이메일이 있으면
		String msg = "로그인에 실패하였습니다", url = "/login/login-form";
		if (ExistingKEmail > 0) {
			logger.info("이미 존재하는 유저");
			// 이메일 통해서 회원 정보 가지고 오기
			accountVO = loginService.selectByEmail(kakaoEmail);
			msg = accountVO.getName() + "님 환영합니다.";
			url = "/";

			// 세션에 저장
			HttpSession session = request.getSession();
			session.setAttribute("email", accountVO.getEmail());
			session.setAttribute("name", accountVO.getName());
			session.setAttribute("accountNo", accountVO.getAccountNo());
			session.setAttribute("tel", accountVO.getTel());
			session.setAttribute("cardNo", accountVO.getCardNo());
			session.setAttribute("address", accountVO.getAddress());

		} else {
			// name, email을 vo에 넣고
			accountVO.setName(kakaoUsername);
			accountVO.setEmail(kakaoEmail);
			// 회원가입 시킨다.
			int result = loginService.insertAccountForKako(accountVO);
			// 회원가입 성공하면
			if (result > 0) {
				logger.info("회원가입 성공");
				// 세션에 저장할 정보 가져오고
				accountVO = loginService.selectByEmail(kakaoEmail);
				msg = accountVO.getName() + "님 환영합니다.";
				url = "/";
				// 세션에 저장
				HttpSession session = request.getSession();
				session.setAttribute("email", accountVO.getEmail());
				session.setAttribute("name", accountVO.getName());
				session.setAttribute("accountNo", accountVO.getAccountNo());

			} else {
				logger.info("로그인 처리 실패 ");
				msg = "로그인에 실패하였습니다";
				url = "/login/login-form";
			}

		}

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		logger.info("처리 완료");
		return "/common/message";

	}

}
