package com.gr.comcome.search_pd.controller;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gr.comcome.category.model.CategoryService;
import com.gr.comcome.category.model.CategoryVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequestMapping("/searchpd")
public class SearchProductController {
	private final CategoryService categoryService;
	
	public SearchProductController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@RequestMapping("/list")
	public String list(Model model
			,@ModelAttribute() CategoryVO vo
			,@RequestParam(value = "keyword", required = false) String keyword) {
		
		if(vo == null && keyword == null) {
			
		}
		log.info("Enter list()");
		log.info(vo.toString());
		log.info(keyword);
		
		vo = categoryService.selectCategoryNo(vo);
		log.info(vo.toString());
		
		
		
		
		return "/search_pd/list";
	}
	
	@RequestMapping("/pd")
	public String pd(Model model) {
		log.info("Enter pd()");
		
		return "/search_pd/pd";
	}
	
}
