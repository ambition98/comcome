package com.gr.comcome.wishlist.controller;

import java.util.List; 
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.ui.ModelMap; 
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gr.comcome.wishlist.controller.WishListController;
import com.gr.comcome.wishlist.model.WishListService; 
import com.gr.comcome.wishlist.model.WishListVO;
import  com.gr.comcome.common.ConstUtil;

@Controller
@RequestMapping("mypage/wishlist")
public class WishListController { 
	private static final Logger logger
	=LoggerFactory.getLogger(WishListController.class);

	private WishListService wishlistService;

@Autowired 
public WishListController(WishListService wishlistService) {
	this.wishlistService = wishlistService; 
	
}
@RequestMapping("/wishlistList")
public String cartList(HttpSession session, ModelMap model) {
	String wisilistNo=(String) session.getAttribute("wisilistNo");
	logger.info("장바구니 목록, wisilistNo={}", wisilistNo);
	
	List<Map<String, Object>> list=wishlistService.selectWishList(wisilistNo);
	logger.info("장바구니 목록 조회 결과, list.size={}", list.size());
	
	model.addAttribute("list", list);
	
	 
	
	return "mypage/wishlist/wishlistList";
}
 

}
