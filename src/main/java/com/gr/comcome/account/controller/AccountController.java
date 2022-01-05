package com.gr.comcome.account.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	@RequestMapping(value="/register.do", method = RequestMethod.GET)
	public String write_get() {
		logger.info("등록 화면");
		
		return "register/register";
	}
	
	@RequestMapping(value="/register.do", method = RequestMethod.POST)
	public String write_post(@ModelAttribute AccountVO accountVo,
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
		System.out.println("시바시바 시바견" + hashVo.toString());
		System.out.println("시바시바 시바견2" + pwd);
		cnt=accountService.insertPwd(hashVo);
		logger.info("비밀번호 등록 결과, cnt={}", cnt);
		
		//4
		return "redirect:/account/register.do";
	}
	
//	@RequestMapping("/list.do")
//	public String list(@ModelAttribute SearchVO searchVo, Model model) {
//		//1
//		logger.info("글목록, 파라미터 searchVo={}", searchVo);
//				
//		//2
//		//searchVo에 firstRecordIndex, recordCountPerPage 값을 넣어줘야 함
//		//=> PaginationInfo를 이용하여 firstRecordIndex값을 미리 구한다
//		
//		//[1] PaginationInfo 객체 생성 - 계산해줌
//		PaginationInfo pagingInfo = new PaginationInfo();
//		pagingInfo.setBlockSize(ConstUtil.BLOCK_SIZE);
//		pagingInfo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
//		pagingInfo.setCurrentPage(searchVo.getCurrentPage());
//		
//		//[2] searchVo에 값 셋팅
//		searchVo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
//		searchVo.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
//		logger.info("값 셋팅 후 searchVo={}", searchVo);
//		
//		List<BoardVO> list=boardService.selectAll(searchVo);
//		logger.info("전체조회 결과 list.size={}", list.size());
//		
//		//[3] totalRecord 구하기
//		int totalRecord=boardService.selectTotalRecord(searchVo);
//		pagingInfo.setTotalRecord(totalRecord);
//		
//		//3. model에 결과 저장
//		model.addAttribute("list", list);
//		model.addAttribute("pagingInfo", pagingInfo);
//		
//		//4. 뷰페이지 리턴
//		return "board/list";
//	}
//	
//	@RequestMapping("/detail.do")
//	public String detail(@RequestParam(defaultValue = "0") int no, Model model) {
//		logger.info("글 상세보기 파라미터 no={}", no);
//		
//		if(no==0) {
//			model.addAttribute("msg", "잘못된 url입니다.");
//			model.addAttribute("url", "/board/list.do");
//			
//			return "common/message";
//		}
//		
//		BoardVO vo=boardService.selectByNo(no);
//		logger.info("상세보기 결과 vo={}", vo);
//		
//		model.addAttribute("vo", vo);
//		
//		return "board/detail";
//	}
//	
//	@RequestMapping("/countUpdate.do")
//	public String countUpdate(@RequestParam(defaultValue = "0") int no, 
//			Model model) {
//		logger.info("조회수 증가 파라미터 no={}", no);
//		
//		if(no==0) {
//			model.addAttribute("msg", "잘못된 url입니다.");
//			model.addAttribute("url", "/board/list.do");
//			
//			return "common/message";
//		}
//		
//		int cnt=boardService.updateReadCount(no);
//		logger.info("조회수 증가 결과 cnt={}", cnt);
//		
//		return "redirect:/board/detail.do?no="+no;		
//	}
//	
//	@RequestMapping("/mainNotice")
//	public String mainNotice(Model model) {
//		logger.info("메인 공지사항 페이지");
//		
//		List<BoardVO> list =boardService.selectMainNotice();
//		logger.info("메인 공지사항 조회 결과, list.size={}", list.size());
//		
//		model.addAttribute("noticeList", list);
//		
//		return "inc/mainNotice";
//	}
	
	
}
