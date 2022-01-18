package com.gr.comcome.comment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(value = "/write" , method=RequestMethod.POST)
	public String write(commentVO vo){
		log.info("댓글 작성");
		
		commentService.insertComment(vo);
		log.info("댓글 작성 결과 vo"+vo);
		
		return "redirect:/usedBoard/boardDetail?boardNo="+vo.getBoardNo();
	}
	
	@RequestMapping(value = "/edit" , method=RequestMethod.POST)
	public String edit(commentVO vo){
		log.info("댓글 수정");
		
		commentService.updateComment(vo);
		log.info("댓글 수정 결과 vo"+vo);
		
		return "redirect:/usedBoard/boardDetail?boardNo="+vo.getBoardNo();
	}
	
	@RequestMapping(value = "/delete" , method=RequestMethod.POST)
	public String delete(commentVO vo){
		log.info("댓글 삭제");
		
		commentService.deleteComment(vo);
		log.info("댓글 삭제 결과 vo"+vo);
		
		return "redirect:/usedBoard/boardDetail?boardNo="+vo.getBoardNo();
	}
	
	
	
	
	
}
