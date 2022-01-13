package com.gr.comcome.mypage.controller;

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
import org.springframework.web.context.annotation.RequestScope;

import com.gr.comcome.mypage.model.MypageService;
import com.gr.comcome.mypage.model.MypageVO;

@Controller
@RequestMapping("/mypage")
public class MypageController {
	private static final Logger logger 
	= LoggerFactory.getLogger(MypageController.class);
	
	private  final MypageService mypageService;
	
	@Autowired
	public MypageController(MypageService mypageService) {
		this.mypageService = mypageService;
	}
	
	@GetMapping("/mypageMain")
	public String mypageMain() {
		return "mypageinc/mypageMain";
		
	}
	
	//프로필
	@GetMapping("/list")
	public List<MypageVO> selectList(String name){
		return null;
		
	}
	
	
	@GetMapping("/mypageEdit")
	public String profileEdit(HttpSession session, Model model) {
		String name=(String) session.getAttribute("name");
		logger.info("프로필 수정 화면, 파라미터 name={}", name);
		
		
		MypageVO vo=mypageService.selectByName(name);
		logger.info("프로필 수정 - 조회 결과 vo ={}",vo);
		
		model.addAttribute("vo",vo);
		return "mypage/mypageEdit";
		
		
	}
	
	@PostMapping("/mypageEdit")
	public String edit_post(@ModelAttribute MypageVO vo, 
			@RequestParam int Account_no , HttpSession session,
			Model model) {
		String name=(String) session.getAttribute("name");
		vo.setNAME(name);
		logger.info("프로필 수정처리 , 파라미터 vo={}",vo);
		
		if(vo.getNAME()==null || vo.getNAME().isEmpty()) {
			vo.setNAME("");
		}
		

		return null;
		
		/* int result=mypageService. */ // membercontroller .94쪽
	}
	
	/*
	 * //http://localhost:9091/comcome/index
	 * 
	 * @GetMapping("/mypageDetail") public String profile(@RequestParam
	 * (defaultValue = "0")int ACCOUNT_NO, Model model) {
	 * logger.info("프로필 수정 화면 , 파라미터 ACCOUNT_NO={}", ACCOUNT_NO);
	 * 
	 * if(ACCOUNT_NO==0) { model.addAttribute("msg" ,"잘못된 url입니다");
	 * model.addAttribute("url" , "/index"); return "common/message";
	 * 
	 * }
	 * 
	 * MypageVO vo =mypageService.selectByNo(ACCOUNT_NO); logger.info("vo",vo);
	 * 
	 * return "mypage/mypageDetail"; }
	 */
		
	
	
}
