package com.gr.comcome.admin.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gr.comcome.account.model.AccountVO;
import com.gr.comcome.admin.model.AdminService;
import com.gr.comcome.admin.model.AdminVO;
import com.gr.comcome.admin.model.NoticeVO;
import com.gr.comcome.common.ConstUtil;
import com.gr.comcome.common.PaginationInfo;
import com.gr.comcome.common.SearchVO;

@Controller
@RequestMapping("/admin")
public class adminController {

	private static final Logger logger = LoggerFactory.getLogger(adminController.class);

	@Autowired
	private AdminService adminService;

	
	  // http://localhost:9091/comcome/admin/member
	  
	  @RequestMapping("/member") public String member_get() { // 1
	  logger.info("회원 목록 화면");
	  
	  return "/admin/member"; 
	  }
	 

	@RequestMapping("/detail")
	public String detail(@RequestParam(defaultValue = "0") int account_no, Model model) {
		logger.info("글 상세보기 파라미터 no={}", account_no);

		if (account_no == 0) {
			model.addAttribute("msg", "잘못된 url입니다.");
			model.addAttribute("url", "/board/list.do");

			return "/common/message";
		}

		AccountVO vo = adminService.selectByAccountNo(account_no);
		logger.info("상세보기 결과 vo={}", vo);

		model.addAttribute("vo", vo);

		return "/login/detail";
	}



	@GetMapping("/popupregi")
	public String popupregi() {
		return "/admin/popupregi";
	}

	// localhost:9091/comcome/admin/logout
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		logger.info("로그아웃 처리");

		// session.invalidate();
		session.removeAttribute("emailadmin");
		session.removeAttribute("adminNo");

		return "redirect:/login/index";
	}

	

	//사이드바 + 팝업 공지 등록 화면 
	// localhost:9091/comcome/admin/popup-regi-with-main
	@RequestMapping("/popup-regi-with-main")
	public String popupRegiWithMain() {

		return "/adminview/popupwithmain";
	}

	// localhost:9091/comcome/admin/popup-regi
	@RequestMapping("/popup-regi")
	public String popupRegi_post(@RequestParam(required = false) String title,
			@RequestParam(required = false) String content, @RequestParam String email, Model model) {

		if (title == null || content == null || title == "" || content == "") {
			model.addAttribute("msg", "제목/내용을 입력해주세요");
			model.addAttribute("url", "/admin/popup-regi-with-main");
			return "common/message";
		}

		logger.info("팝업 등록 처리, title={}, content={}, email={}", title, content, email);

		int result = adminService.insertNotice(email, title, content);
		String msg = "공지 등록에 실패하였습니다", url = "/admin/popup-regi-with-main";
		if (result == adminService.INSERT_OK) {
			msg = "공지 등록이 완료되었습니다";
			url = "/admin/allmemberview";
		}

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		return "/common/message";

	}

	// localhost:9091/comcome/admin/popup
	@GetMapping("/popup")
	public String popup_get(Model model) {
		NoticeVO vo = adminService.selectRecentNotice();
		String content = vo.getContent();
		String contentbr = content.replaceAll("\n", "<br />");
		String title = vo.getTitle();
		String titlebr = title.replaceAll("\n", "<br />");

		model.addAttribute("content", contentbr);
		model.addAttribute("title", titlebr);
		model.addAttribute("vo", vo);

		return "/admin/popup";
	}

	// localhost:9091/comcome/admin/login
	@GetMapping("/login")
	public String adminlogin() {
		return "/admin/adminlogin";
	}

	//사이드바 + 로그인 화면 
	// localhost:9091/comcome/admin/login-with-main
	@GetMapping("/login-with-main")
	public String adminwithmain() {
		return "/adminview/adminloginwithmain";
	}

	//관리자 로그인 
	// localhost:9091/comcome/admin/login
	@PostMapping("/login")
	public String adminlogin_post(@RequestParam(required = false) String email,
			@RequestParam(required = false) String password, @RequestParam(required = false) String chkSave,
			HttpServletRequest request, HttpServletResponse response, Model model) {

		if (email == null || password == null || email == "" || password == "") {
			model.addAttribute("msg", "이메일/비밀번호를 입력해주세요");
			model.addAttribute("url", "/admin/login-with-main");
			return "common/message";
		}

		logger.info("로그인 처리, 파라미터 " + "email={}, password={}, chkSave={}", email, password, chkSave);

		

		logger.info("관리자 계정 처리 ");
		
		int result = adminService.loginCheck(email, password);
	
		String msg = "로그인 처리 실패!", url = "/admin/login-with-main";
		if (result == adminService.LOGIN_OK) {
			AdminVO adminVO = adminService.selectByEmail(email);
			logger.info("adminVO={}", adminVO);
			HttpSession session = request.getSession();
			session.setAttribute("emailadmin", adminVO.getEmail());
			session.setAttribute("adminNo", adminVO.getAdminNo());

			// [2] 쿠키에 저장 - 아이디 저장하기 체크된 경우
			Cookie ck = new Cookie("ck_email", adminVO.getEmail());
			ck.setPath("/");

			if (chkSave != null) {
				ck.setMaxAge(1000 * 24 * 60 * 60);
				response.addCookie(ck);
			} else {
				ck.setMaxAge(0); // 쿠키 제거
				response.addCookie(ck);
			}

			msg = "관리자님 환영합니다";
			url = "/admin/allmemberview";

		} else if (result == adminService.DISAGREE_PWD) {
			msg = "비밀번호가 일치하지 않습니다.";
		} else if (result == adminService.EMAIL_NONE) {
			msg = "해당 이메일이 존재하지 않습니다";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "/common/message";

	}
	
	
    //회원 목록 화면 get, post
	//localhost:9091/comcome/admin/allmemberview
		@RequestMapping("/allmemberview")
		public String main_Post(@ModelAttribute SearchVO searchVO, Model model) {
			// 1
			logger.info("회원 목록 화면, searchVo={}", searchVO);
			// 2
			PaginationInfo pagingInfo = new PaginationInfo();
			pagingInfo.setBlockSize(ConstUtil.BLOCK_SIZE);
			pagingInfo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
			pagingInfo.setCurrentPage(searchVO.getCurrentPage());

			// 3
			searchVO.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
			searchVO.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
			logger.info("값 셋팅 후 searchVo={}", searchVO);

			List<AccountVO> list = adminService.selectAllMember(searchVO);
			logger.info("전체조회 결과 list.size={}", list.size());
			for (AccountVO vo : list) {
				logger.info(vo.toString());
			}
			// 4
			int totalRecord = adminService.selectTotalRecord(searchVO);
			pagingInfo.setTotalRecord(totalRecord);

			model.addAttribute("list", list);
			model.addAttribute("pagingInfo", pagingInfo);

			return "/adminview/allmember";
		}
		
		
	
	
	

}
