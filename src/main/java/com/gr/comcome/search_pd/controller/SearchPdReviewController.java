package com.gr.comcome.search_pd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/searchpd")
public class SearchPdReviewController {
	
	@GetMapping("/review")
	public String selectReview(@RequestParam("no") int no) {
		log.info("Enter GET review()");
		
		return "/search_pd/review";
	}
	
	@PostMapping("/review")
	public String insertReview(@RequestParam("no") int no) {
		log.info("Enter POST review()");
		
		return "";
	}
}
