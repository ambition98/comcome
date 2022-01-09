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

import com.gr.comcome.messagebox.controller.MessageBoxController;
import com.gr.comcome.mypage.model.MypageService;
import com.gr.comcome.mypage.model.MypageVO;


@Controller
@RequestMapping("mypage")
public class MypageController {
	private static final
	Logger logger =LoggerFactory.getLogger(MessageBoxController.class);
	//http//localhost:9091/comcome/mypage/index
	@GetMapping("/index")
	public String index () {
		return "mypageinc/mypageindex";
	}
	
	@GetMapping("/mypageMain")
	public String mypagemain () {
		return "mypageinc/mypageMain";
	}
	

	
}
