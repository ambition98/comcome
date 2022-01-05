package com.gr.comcome.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gr.comcome.category.model.CategoryVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IndexController {
	@RequestMapping("/")
	public String index() {
		log.info("Enter index()");
		
		return "/index";
	}
	
	@RequestMapping("/message")
	public String message() {
		return "/common/message";
	}
	
	@RequestMapping("/test")
	public String testPage(Model model) {
		model.addAttribute("testpage", "test123");
		
		return "/test";
	}
	
	@ModelAttribute("map")
	public Map<String, String> testModel() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("testmodel", "test456");
		return map;
	}
}
