package com.gr.comcome.practice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gr.comcome.messagebox.controller.MessageBoxController;

@Controller
@RequestMapping("mypage")
public class PracticeController {
	private static final
	Logger logger =LoggerFactory.getLogger(MessageBoxController.class);
	//http//localhost:9091/comcome/mypage/index
	@GetMapping("/index")
	public String index () {
		return "practice/index";
	}
	
	@GetMapping("/mypagemain")
	public String mypagemain () {
		return "practice/mypagemain";
	}
	
	//localhost:9091/comcome/mypage/mymessage
	@GetMapping("/mymessage")
	public String mymessage() {
		logger.info("쪽지함 화면");
		return "practice/messagebox";
	}
	
}
