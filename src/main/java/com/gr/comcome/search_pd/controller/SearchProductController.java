package com.gr.comcome.search_pd.controller;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gr.comcome.category.model.CategoryService;
import com.gr.comcome.category.model.CategoryVO;
import com.gr.comcome.search_pd.model.SearchProductDAO;
import com.gr.comcome.search_pd.model.SearchProductService;
import com.gr.comcome.search_pd.model.SearchProductVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequestMapping("/searchpd")
public class SearchProductController {
	private final CategoryService categoryService;
	private final SearchProductService searchProductService;

	public SearchProductController(CategoryService categoryService, SearchProductService searchProductService) {
		this.categoryService = categoryService;
		this.searchProductService = searchProductService;
	}

	@RequestMapping("/list")
	public String list(Model model
			,@ModelAttribute() CategoryVO vo
			,@RequestParam(value = "keyword", required = false) String keyword) {
		
		log.info("Enter list()");
		log.info(keyword);
		
		String view = "/search_pd/list";
		List<SearchProductVO> voList = null;

		//노트북 전체 카테고리 클릭 or url 직접 접근
		if(vo == null && keyword == null) {
			voList = searchProductService.selectAll(); 
			model.addAttribute("voList", voList);
			
			return view;
		}
		
		
		//vo = categoryService.selectCategoryNo(vo);
		log.info(vo.toString());
		
		
		
		
		
		return view;
	}
	
	@RequestMapping("/pd")
	public String pd(Model model) {
		log.info("Enter pd()");
		
		return "/search_pd/pd";
	}
	
}
