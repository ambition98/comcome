package com.gr.comcome.admin.controller;

import java.util.List;

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

import com.gr.comcome.admin.model.AdminService;
import com.gr.comcome.common.ConstUtil;
import com.gr.comcome.common.PaginationInfo;
import com.gr.comcome.common.SearchVO;
import com.gr.comcome.login.model.AccountVO;

@Controller
@RequestMapping("/admin")
public class adminController {

	private static final Logger logger 
	= LoggerFactory.getLogger(adminController.class);
	
	@Autowired
	private AdminService adminService;
	
	//http://localhost:9091/comcome/admin/member
	@RequestMapping("/member")
	public String member_get(
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
		
		//4
		int totalRecord=adminService.selectTotalRecord(searchVO);
		pagingInfo.setTotalRecord(totalRecord);
		
		model.addAttribute("list", list);
		model.addAttribute("pagingInfo", pagingInfo);
		
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
	
	
}
