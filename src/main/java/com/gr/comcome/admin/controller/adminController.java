package com.gr.comcome.admin.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.gr.comcome.account.model.AccountVO;
import com.gr.comcome.admin.model.AdminService;
import com.gr.comcome.admin.model.NoticeVO;
import com.gr.comcome.common.ConstUtil;
import com.gr.comcome.common.PaginationInfo;
import com.gr.comcome.common.SearchVO;

@Controller
@RequestMapping("/admin")
public class adminController {

	private static final Logger logger 
	= LoggerFactory.getLogger(adminController.class);
	
	@Autowired
	private AdminService adminService;
	
	//http://localhost:9091/comcome/admin/member
	@RequestMapping("/member")
	public String member_get() {
		//1
		logger.info("회원 목록 화면");
	
		
		return "admin/member";
	}
	
	@RequestMapping("/detail")
	public String detail(@RequestParam(defaultValue = "0") int account_no, Model model) {
		logger.info("글 상세보기 파라미터 no={}", account_no);
		
		if(account_no==0) {
			model.addAttribute("msg", "잘못된 url입니다.");
			model.addAttribute("url", "/board/list.do");
			
			return "common/message";
		}
		
		AccountVO vo= adminService.selectByAccountNo(account_no);
		logger.info("상세보기 결과 vo={}", vo);
		
		model.addAttribute("vo", vo);
		
		return "login/detail";
	}
	
	
	//http://localhost:9091/comcome/admin/popup
	@RequestMapping("/popup")
	public String popup() {
		return "admin/popup";
	}
	
	//http://localhost:9091/comcome/admin/main
	@RequestMapping("/main")
	public String main_get(
		@ModelAttribute SearchVO searchVO,
		Model model) {
		//1
		logger.info("회원 목록 화면, searchVo={}", searchVO);
		//2
		PaginationInfo pagingInfo = new PaginationInfo();
		pagingInfo.setBlockSize(ConstUtil.BLOCK_SIZE);
		pagingInfo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
		pagingInfo.setCurrentPage(searchVO.getCurrentPage());
		
		//3
		searchVO.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
		searchVO.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
		logger.info("값 셋팅 후 searchVo={}", searchVO);
		
		
		List<AccountVO> list = adminService.selectAllMember(searchVO);
		logger.info("전체조회 결과 list.size={}", list.size());
		for(AccountVO vo : list) {
			logger.info(vo.toString());
		}
		//4
		int totalRecord=adminService.selectTotalRecord(searchVO);
		pagingInfo.setTotalRecord(totalRecord);
		
		model.addAttribute("list", list);
		model.addAttribute("pagingInfo", pagingInfo);
		
		return "admin/adminmain";
	}
	
	@GetMapping("/popupregi")
	public String popupregi() {
		return "admin/popupregi";
	}
	
    //localhost:9091/comcome/admin/logout
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		logger.info("로그아웃 처리");
		
		//session.invalidate();
		session.removeAttribute("email");
		session.removeAttribute("adminNo");
		
		return "redirect:/login/index";
	}
	
	//localhost:9091/comcome/admin/popup-regi
	@GetMapping("/popup-regi")
	public String popupRegi() {
		logger.info("팝업창 등록 화면 메인 없이");
		return "admin/popupregi";
	}
	
	//localhost:9091/comcome/admin/popup-regi-with-main
	@RequestMapping("/popup-regi-with-main")
	public String popupRegiWithMain() {
		
		return "admin/popupwithmain";
	}
	
	//localhost:9091/comcome/admin/popup-regi
	@RequestMapping("/popup-regi")
	public String popupRegi_post(
			@RequestParam(required = false) String title,
			@RequestParam(required = false) String content,
			@RequestParam String email,
			 Model model) {
		
		if(title == null || content==null || title == "" || content == "") {
			model.addAttribute("msg", "제목/내용을 입력해주세요");
			model.addAttribute("url", "/admin/popup-regi-with-main");
			return "common/message";
		}
		
		
		logger.info("팝업 등록 처리, title={}, content={}, email={}",title, content,email);
		
		
		int result = adminService.insertNotice(email,title,content);
		String msg = "공지 등록에 실패하였습니다", url = "/admin/popup-regi-with-main";
		if(result==adminService.INSERT_OK) {
			msg = "공지 등록이 완료되었습니다";
			url = "/admin/main";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url",url);
		
		return "common/message";	
		
	}
	
	
	//localhost:9091/comcome/admin/popup-view
	@GetMapping("/popup-view")
	public String popup_get(Model model) {
		
		NoticeVO vo = adminService.selectRecentNotice();
		
		model.addAttribute("vo", vo);
		logger.info("팝업 화면 처리 ,vo={}",vo.toString());
		
		return "admin/popup";
	}
	
	
	
	
	
	
}
