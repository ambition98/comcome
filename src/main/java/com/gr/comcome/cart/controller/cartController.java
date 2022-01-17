package com.gr.comcome.cart.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gr.comcome.account.model.AccountService;
import com.gr.comcome.account.model.AccountVO;
import com.gr.comcome.login.model.LoginService;
import com.gr.comcome.saleproduct.model.SaleProductService;
import com.gr.comcome.wishlist.model.WishListService;
import com.gr.comcome.wishlist.model.WishListVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/mypage")
public class cartController {

	@Autowired
	public WishListService wishlistService;

	@Autowired
	public SaleProductService saleProductService;

	@Autowired
	public AccountService accountService;

	@Autowired
	public LoginService loginService;

	// localhost:9091/comcome/mypage/cart
	@RequestMapping("/cart")
	public String cartList(HttpSession session, Model model) {
		String email = (String) session.getAttribute("email");
		log.info("장바구니 목록, email={}", email);

		AccountVO accountVo = loginService.selectByEmail(email);
		log.info("장바구니 목록, email={}, accountVo={}", email, accountVo);

		List<Map<String, Object>> list = wishlistService.selectAll(accountVo.getAccountNo());
		log.info("장바구니 목록 조회 결과, list.size={}", list.size());
		for(Map<String, Object> map : list) {
			map.forEach((k, v) -> {
				System.out.println(k + ": " + v);
			});
			System.out.println("--------------");
		}
		model.addAttribute("list", list);

		return "/mypractice/view/cart3";
	}

	@RequestMapping("/delete-cart")
	public String delete_cart(@RequestParam(defaultValue = "0") int accountNo,
			@RequestParam(defaultValue = "0") int saleProductNo, Model model) {
		log.info("장바구니 삭제 처리, no={}", saleProductNo);
		if (accountNo == 0 || saleProductNo == 0) {
			model.addAttribute("msg", "잘못된 url입니다.");
			model.addAttribute("url", "/mypage/cart");

			return "/common/message";
		}

		// boardNo에 해당하는 UseBoard 삭제
		int result = wishlistService.deleteWishByNo(accountNo, saleProductNo);
		log.info("글 삭제 처리, result={}", result);
		String msg = "장바구니 상품  삭제에 실패하였습니다", url = "/mypage/cart";
		if (result > 0) {// 삭제에 성공하면
			msg = "장바구니 상품 삭제에 성공하였습니다";
		} else if (result < 0) {
			msg = "장바구니 상품  삭제에 실패하였습니다.";
		}

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		return "/common/message";
	}

	// localhost:9091/comcome/mypage/cartEdit
	@RequestMapping("/cartEdit")
	public String cartedit(@ModelAttribute WishListVO wishListVo, Model model) {
		log.info("장바구니 수량 수정 처리, wishListVo={}", wishListVo.toString());

		/*
		 * WishListVO wishListVo= new WishListVO();
		 * wishListVo.setWishlistNo(wishlistNo); wishListVo.setQuantity(quantity);
		 */

		int result = wishlistService.updateQuantity(wishListVo);

		String msg = "장바구니 수량 수정에 실패하였습니다", url = "/mypage/cart";
		if (result > 0) {// 삭제에 성공하면
			log.info("장바구니 수량 수정 성공 result={}", result);
			msg = "장바구니 상품 수정에 성공하였습니다";
		} else if (result < 0) {
			log.info("장바구니 수량 수정 실패 result={}", result);
			msg = "장바구니 상품  수정에 실패하였습니다.";
		}

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		return "/common/message";

	}

}
