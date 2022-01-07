package com.gr.comcome.comment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gr.comcome.comment.model.commentService;
import com.gr.comcome.comment.model.commentVO;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/comment")
public class commentController {
	
	private final commentService commentService;
	
	@Autowired
	public commentController(commentService commentService) {
		this.commentService=commentService;
		log.info("생성자주입!");
	}
	
	@RequestMapping("/list")
	public String list(@RequestParam(defaultValue = "0")int boardNo,Model model ) {
		log.info("댓글 목록 파라미터 boardNo="+boardNo);
		
		List<commentVO> list=commentService.selectByNo(boardNo);
		log.info("조회 결과 list.size="+list.size());
		
		model.addAttribute("commentList",list);
		return "/comment/list";	
	}
}
