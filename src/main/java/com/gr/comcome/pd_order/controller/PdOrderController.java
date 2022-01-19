package com.gr.comcome.pd_order.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gr.comcome.account.model.AccountVO;
import com.gr.comcome.login.model.LoginService;
import com.gr.comcome.pd_order.model.PdOrderService;
import com.gr.comcome.saleproduct.model.SaleProductService;
import com.gr.comcome.wishlist.model.WishListService;


@Controller
@RequestMapping("mypage")
public class PdOrderController {
	private static final Logger logger
	=LoggerFactory.getLogger(PdOrderController.class);
	
	private final PdOrderService pdOrderService;
	
	@Autowired
	public WishListService wishlistService;
	
	@Autowired
	public SaleProductService saleProductService;
	
	@Autowired
	public LoginService loginService;
	
	@Autowired
	public PdOrderController(PdOrderService pdOrderService) {	
		this.pdOrderService = pdOrderService;
				
	}
//	//구매한 목록
//	@RequestMapping("order/orderList")
//	public String orderList(HttpSession session, Model model) {
//		String pdOrderNo = (String) session.getAttribute("pdOrderNo");
//		logger.info("구매목록, pdOrderNo={}",pdOrderNo);
//		
//		List<Map<String, Object>> list = pdOrderService.selectOrder(pdOrderNo);
//		logger.info("구매목록 조회 결과, list.size={}", list.size());
//		
//		model.addAttribute("list",list);
//		
//		return "mypage/order/orderList";
//		
//	}
	
	@RequestMapping("/order/orderList")
	public String OrderList(HttpSession session, Model model) {
		String email = (String) session.getAttribute("email");
		logger.info("email={}", email);
		
		if(email == null) {
			String msg = "로그인이 필요한 서비스입니다.";
			String url = "/login/login-form";
			
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			
			return "/common/message";
		}
		
		AccountVO accountVo =loginService.selectByEmail(email);
		logger.info("구매목록 , email={}, accountVo={}",email, accountVo);
		
		List<Map<String, Object>> list =pdOrderService.selectOrder(accountVo.getAccountNo());
		logger.info("구매목록 조회 결과, list.size={}",list.size());
		model.addAttribute("list",list);
		
		return "mypage/order/orderList";
		
	}
	
	
}
