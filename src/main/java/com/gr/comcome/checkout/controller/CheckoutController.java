package com.gr.comcome.checkout.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gr.comcome.account.model.AccountService;
import com.gr.comcome.account.model.AccountVO;
import com.gr.comcome.wishlist.model.WishListService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CheckoutController {
	private final AccountService accountService;
	private final WishListService wishListService;

	public CheckoutController(AccountService accountService, WishListService wishListService) {
		this.accountService = accountService;
		this.wishListService = wishListService;
	}

	/*
	 * 장바구니에서 체크한 상품만 구매
	 */
	@GetMapping("/cart/checkout")
	public String cartCheckout(HttpServletRequest request, Model model) {
		
		log.info("Enter cartCheckout()");
		Object accountNo = request.getSession().getAttribute("accountNo");
		
		if(accountNo == null) {
			String msg = "잘못된 접근입니다.";
			String url = "/";
			
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			
			return "/common/message";
		}
		/*
		 * 장바구니에서 체크한 상품만 구매
		 */
//		AccountVO accountVo =  accountService.selectAccountByNo((int)accountNo);
//		log.info(accountVo.toString());
//		
//		List<Map<String, Object>> mapList = wishListService.selectAll((int)accountNo);
//		
//		model.addAttribute("accountVo", accountVo);
//		model.addAttribute("mapList", mapList);
		
		return "checkout/checkout";
	}
	
	@GetMapping("/direct/checkout")
	public String directCheckout(
			@RequestParam(value = "saleProductNo"
						, required = false
						, defaultValue = "-1") int saleProductNo, Model model) {
		
		if(saleProductNo == -1) {
			String msg = "잘못된 접근입니다.";
			String url = "/";
			
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			
			return "/common/message";
		}
		
		//SaleProductVO vo = saleProductService.selectByNo(saleProductNo);
		//log.info("saleProductVo: " + vo);
		
		//model.addAttribute("vo", vo);
		
		return "checkout/checkout";
	}
}
