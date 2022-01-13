package com.gr.comcome.aboutcomcome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/aboutcomcome")
public class AboutComcomeContorller {
	
	
	//localhost:9091/comcome/aboutcomcome/intro
	@GetMapping("/intro")
	public String aboutcomcome() {
		return "/aboutcomcomeview/aboutcomcome";
	}

}
