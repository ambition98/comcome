package com.gr.comcome.checkout.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gr.comcome.account.model.AccountService;
import com.gr.comcome.account.model.AccountVO;
import com.gr.comcome.account.model.PaymentEncrypt;
import com.gr.comcome.wishlist.model.WishListService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CheckoutController {
	private final AccountService accountService;
	private final WishListService wishListService;
	private final PaymentEncrypt paymentEncrypt;

	public CheckoutController(AccountService accountService, WishListService wishListService,
			PaymentEncrypt paymentEncrypt) {
		this.accountService = accountService;
		this.wishListService = wishListService;
		this.paymentEncrypt = paymentEncrypt;
	}
	
	@GetMapping("/cart/checkout")
	public String cartCheckout(HttpServletRequest request, Model model
			,@RequestParam("wishNoList") String wishNoList) {
		
		log.info("Enter cartCheckout()");
		log.info("wishNoList: " + wishNoList);
		
		String[] wishNoArr = wishNoList.split(",");
		
		Object accountNo = request.getSession().getAttribute("accountNo");
		
		if(accountNo == null) {
			String msg = "잘못된 접근입니다.";
			String url = "/";
			
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			
			return "/common/message";
		}
		
		AccountVO accountVo = accountService.selectAccountByNo((int)accountNo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountNo", accountNo);
		map.put("wishNoArr", wishNoArr);
		
		
		List<Map<String, Object>> cartMapList = wishListService.selectByWishlistNoArr(map);
		
//		for(Map<String, Object> m : cartMapList) {
//			m.forEach((k, v) -> {
//				log.info("k: " + k);
//				log.info("v: " + v);
//			});
//			log.info("--------------");
//		}
		
		log.info("size: " + cartMapList.size());
		model.addAttribute("accountVo", accountVo);
		model.addAttribute("cartMapList", cartMapList);
		
		String merchantKey = "EYzu8jGGMfqaDEp76gSckuvnaHHu+bC4opsSN6lHv3b2lurNYkVXrZ7Z1AoqQnXI3eLuaUFyoRNC6FkrzVjceg=="; // 상점키
		String merchantID = "nicepay00m";
		String goodsName = "테스트 노트북";
		String price = "100";
		String moid = "order_1234567890";
		String ediDate = getyyyyMMddHHmmss();
		String hashString = paymentEncrypt.encrypt(ediDate + merchantID + price + merchantKey);
		
		model.addAttribute("merchantKey", merchantKey);
		model.addAttribute("merchantID", merchantID);
		model.addAttribute("goodsName", goodsName);
		model.addAttribute("price", price);
		model.addAttribute("moid", moid);
		model.addAttribute("ediDate", ediDate);
		model.addAttribute("hashString", hashString);
		
		return "/checkout/checkout";
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
		
		
		return "/checkout/checkout";
	}
	
	@RequestMapping("/checkout/result")
	public String resultPage(Model model) {
		
		return "/checkout/result";
	}
	
	public final synchronized String getyyyyMMddHHmmss(){
		SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
		return yyyyMMddHHmmss.format(new Date());
	}
}
