package com.gr.comcome;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/")
	public String index() {
		return "/index";
	}
	
	@RequestMapping("/header")
	public String header() {
		return "/inc/header";
	}
	
	@RequestMapping("/footer")
	public String footer() {
		return "/inc/footer";
	}
	
	/*
	 * @RequestMapping("/searchForm") public String searchForm() { return
	 * "/inc/searchform"; }
	 */
}
