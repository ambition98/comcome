package com.gr.comcome.wishlist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gr.comcome.wishlist.model.WishListService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/event")
public class WishListController2 {
	
	@Autowired
	private WishListService wishlistService;
	
	@RequestMapping("/wish")
	public String wish(@RequestParam(value = "email", defaultValue = "1") String email, @RequestParam int saleProductNo, Model model) {
		
		if( email.equals("1")) {
			model.addAttribute("msg","장바구니 등록은 로그인 후에 가능합니다");
			model.addAttribute("url","/");
			return"/common/message";
		}
		log.info("관심 상품 등록 처리,email={},saleProductNo={}", email, saleProductNo);
		
		//관심 상품 등록
		int result = wishlistService.insertWishByNo(email,saleProductNo);
		
		if(result>0) {
			log.info("관심 상품 등록 성공");
			model.addAttribute("msg", "장바구니에 상품이 담겼습니다");
			model.addAttribute("url","/");
		} else {
			model.addAttribute("msg", "장바구니 상품 등록에 실패하였습니다");
			model.addAttribute("url","/");
		}
		
		return "/common/message";
	}
	
	@RequestMapping("/saleProduct/wish")
	public String saleProductWish(@RequestParam(value = "email", defaultValue = "1") String email, @RequestParam int saleProductNo, Model model) {
		
		if( email.equals("1")) {
			model.addAttribute("msg","장바구니 등록은 로그인 후에 가능합니다");
			model.addAttribute("url","/saleProduct/detail?saleProductNo=" + saleProductNo);
			return"/common/message";
		}
		log.info("관심 상품 등록 처리,email={},saleProductNo={}", email, saleProductNo);
		
		//관심 상품 등록
		int result = wishlistService.insertWishByNo(email,saleProductNo);
		
		if(result>0) {
			log.info("관심 상품 등록 성공");
			model.addAttribute("msg", "장바구니에 상품이 담겼습니다");
			model.addAttribute("url","/saleProduct/detail?saleProductNo=" + saleProductNo);
		} else {
			model.addAttribute("msg", "장바구니 상품 등록에 실패하였습니다");
			model.addAttribute("url","/saleProduct/detail?saleProductNo=" + saleProductNo);
		}
		
		return "/common/message";
	}


}
