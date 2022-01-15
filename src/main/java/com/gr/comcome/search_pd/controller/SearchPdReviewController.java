package com.gr.comcome.search_pd.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gr.comcome.account.model.AccountService;
import com.gr.comcome.admin.model.AdminService;
import com.gr.comcome.search_pd.reply.model.SearchPdReviewReplyService;
import com.gr.comcome.search_pd.reply.model.SearchPdReviewReplyVO;
import com.gr.comcome.search_pd.review.model.SearchPdReviewService;
import com.gr.comcome.search_pd.review.model.SearchPdReviewVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/searchpd")
public class SearchPdReviewController {
	
	private final SearchPdReviewService searchPdReviewService;
	private final SearchPdReviewReplyService searchPdReviewReplyService;
	private final AccountService accountService;
	private final AdminService adminService;

	public SearchPdReviewController(SearchPdReviewService searchPdReviewService,
			SearchPdReviewReplyService searchPdReviewReplyService, AccountService accountService,
			AdminService adminService) {
		this.searchPdReviewService = searchPdReviewService;
		this.searchPdReviewReplyService = searchPdReviewReplyService;
		this.accountService = accountService;
		this.adminService = adminService;
	}

	@GetMapping("/review")
	public String selectReview(@RequestParam("no") int no, Model model) {
		log.info("Enter GET review()");
		Map<String, Integer> countMap = searchPdReviewService.selectReviewCount(no);
		
//		map.forEach((k, v) -> {
//			System.out.println("k: " + k);
//			System.out.println("v: " + v);
//		});
		
		model.addAttribute("countMap", countMap);
		
		return "/search_pd/review";
	}
	
	@PostMapping("/review")
	public String insertReview(SearchPdReviewVO vo, Model model) {
		log.info("Enter POST review()");
		log.info("vo: " + vo);
		
		String url = "/searchpd/detail?pdNo=" + vo.getSearchProductNo();
		String msg = "";
		
		if(searchPdReviewService.insertNewReview(vo))
			msg = "등록되었습니다.";
		else
			msg = "등록에 실패하였습니다.";
		
		model.addAttribute("url", url);
		model.addAttribute("msg", msg);
		
		return "forward:/message";
	}
	
	@ResponseBody
	@RequestMapping("/change_review")
	public List<SearchPdReviewVO> changeReview(@RequestBody String data) {
		log.info("Enter changeReview()");
		
		Map<String, Object> map = new HashMap<>();
		JSONObject jObject = new JSONObject(data);
		
		map.put("type", jObject.get("type"));
		map.put("searchProductNo", jObject.get("searchProductNo"));
		
		List<SearchPdReviewVO> voList = searchPdReviewService.selectByType(map);
		for(SearchPdReviewVO vo : voList) {
			vo.setAccountVo(accountService.selectAccountByNo(vo.getAccountNo()));
			vo.setSearchPdReviewReplyVo(searchPdReviewReplyService.selectByReviewNo(vo.getSearchPdReviewNo()));
			SearchPdReviewReplyVO replyVo = vo.getSearchPdReviewReplyVo(); 
			if(replyVo != null) {
				replyVo.setAdminVo(adminService.selectByNo(replyVo.getAdminNo()));
				vo.setSearchPdReviewReplyVo(replyVo);
			}
				
		}
		
//		for(SearchPdReviewVO vo : voList) {
//			System.out.println(vo.getAccountVo());
//			System.out.println(vo.getSearchPdReviewReplyVo());
//		}
		
		return voList;
	}
}
