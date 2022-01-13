package com.gr.comcome.usedBoard.controller;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gr.comcome.comment.model.commentService;
import com.gr.comcome.comment.model.commentVO;
import com.gr.comcome.common.ConstUtil;
import com.gr.comcome.common.PaginationInfo;
import com.gr.comcome.common.SearchVO;
import com.gr.comcome.usedBoard.model.usedBoardService;
import com.gr.comcome.usedBoard.model.usedBoardVO;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/usedBoard")
public class usedBoardController {
	
	private static final Logger logger
	=LoggerFactory.getLogger(usedBoardController.class);
	
	private final usedBoardService usedBoardService;
	
	private final commentService commentService;
	
	@Autowired
	public usedBoardController(usedBoardService boardService,commentService commentService) {
		this.usedBoardService=boardService;
		this.commentService=commentService;
		logger.info("생성자주입!");
	}

	@RequestMapping("/list")
	public String list(@ModelAttribute SearchVO searchVo,Model model) {
		//1
		logger.info("글목록, 파라미터 searchVo={},", searchVo);
		
		
		//[1]paginnationInfo 객체 생성 - 계산해줌
		PaginationInfo paginationInfo=new PaginationInfo();
		paginationInfo.setBlockSize(ConstUtil.BLOCK_SIZE);
		paginationInfo.setRecordCountPerPage(ConstUtil.RECORD_COUNT2);
		paginationInfo.setCurrentPage(searchVo.getCurrentPage());
		
		//[2]searchVo에 값 세팅
		searchVo.setRecordCountPerPage(ConstUtil.RECORD_COUNT2);
		searchVo.setFirstRecordIndex(paginationInfo.getFirstRecordIndex());
		logger.info("값 세팅 후 searchVo={}",searchVo);
		
		List<usedBoardVO> list=usedBoardService.selectAll(searchVo);
		logger.info("전체조회 결과 list.size={}",list.size());
		
		//[3]
		int totalRecord=usedBoardService.selectTotalRecord(searchVo);
		paginationInfo.setTotalRecord(totalRecord);
		
		//3.model에 결과 저장
		model.addAttribute("list",list);
		model.addAttribute("pagingInfo",paginationInfo);
		
		
		return "/usedBoard/board";
	}
	/*
	@RequestMapping("/category2")
	public String categotyList(Model model) {
		//1
		logger.info("중고게시판 카테고리 조회");
		
		//[1]paginnationInfo 객체 생성 - 계산해줌
		PaginationInfo paginationInfo=new PaginationInfo();
		paginationInfo.setBlockSize(ConstUtil.BLOCK_SIZE);
		paginationInfo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
		
		
		//[2]searchVo에 값 세팅
	
		
		
		logger.info("전체조회 결과 list.size={}",list.size());
		
		//3.model에 결과 저장
		model.addAttribute("list",list);
		model.addAttribute("pagingInfo",paginationInfo);
		
		
		return "/usedBoard/category2";
	}
	*/
	@RequestMapping("/boardDetail")
	public String detail(@RequestParam(defaultValue = "0") int boardNo, Model model) {
		logger.info("글 상세보기 파라미터 boardNo={}", boardNo);
		
		if(boardNo==0) {
			model.addAttribute("msg", "잘못된 url입니다.");
			model.addAttribute("url", "/usedBoard/board");
			
			return "common/message";
		}
		
		usedBoardVO vo=usedBoardService.selectByNo(boardNo);
		logger.info("상세보기 결과 vo={}", vo);
		
		List<commentVO> list2=commentService.selectByNo(boardNo);
		log.info("상세보기 결과 댓글list="+list2);
		
		model.addAttribute("vo", vo);
		model.addAttribute("list2",list2);
		
		return "usedBoard/boardDetail";
	}
	
	@RequestMapping("/countUpdate")
	public String countUpdate(@RequestParam(defaultValue = "0") int boardNo, 
			Model model) {
		logger.info("조회수 증가 파라미터 no={}", boardNo);
		
		if(boardNo==0) {
			model.addAttribute("msg", "잘못된 url입니다.");
			model.addAttribute("url", "/usedBoard/board");
			
			return "common/message";
		}
		
		int cnt=usedBoardService.updateReadCount(boardNo);
		logger.info("조회수 증가 결과 cnt={}", cnt);
		
		return "redirect:/usedBoard/boardDetail?boardNo="+boardNo;		
	}
	
	
	
	@RequestMapping(value ="/list_ajax")
    public String list_expertmb_ajax(@ModelAttribute SearchVO searchVo,          //여기서는 ModelAndView를 사용하는게 중요 포인트입니다.
    		@RequestParam(defaultValue = "0") String kind,
            Model model) {
        
              //request에서 getParameter를 사용하여 kind 값을 불러옵니다.
        
        logger.info("파라미터 kind = {}",kind);
        logger.info("파라미터 searchVo = {}",searchVo);
       
        /*
        int result=0;
        if("노트북".equals(kind))
        {
        	result=1;
        }else if("노트북 주변기기".equals(kind)){
        	result=2;
        }else if("기타 pc부품".equals(kind)) {
        	result=3;
        }
        
        logger.info("result:{}",result);
        */
       
        
        List<usedBoardVO> list=usedBoardService.selectByGroupNo(kind);;
		logger.info("전체조회 결과 list.size={}",list.size());
        
        model.addAttribute("list",list);
      
        return "/usedBoard/board";
    }


}
