package com.gr.comcome.usedBoard.controller;

import java.util.List;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gr.comcome.common.ConstUtil;
import com.gr.comcome.common.PaginationInfo;
import com.gr.comcome.common.SearchVO;
import com.gr.comcome.usedBoard.model.usedBoardService;
import com.gr.comcome.usedBoard.model.usedBoardVO;


@Controller
@RequestMapping("/usedBoard")
public class usedBoardController {
	
	private static final Logger logger
	=LoggerFactory.getLogger(usedBoardController.class);
	
	private final usedBoardService usedBoardService;
	
	@Autowired
	public usedBoardController(usedBoardService boardService) {
		this.usedBoardService=boardService;
		logger.info("생성자주입!");
	}

	@RequestMapping("/list")
	public String list(@ModelAttribute SearchVO searchVo,Model model) {
		//1
		logger.info("글목록, 파라미터 searchVo={},", searchVo);
		
		//[1]paginnationInfo 객체 생성 - 계산해줌
		PaginationInfo paginationInfo=new PaginationInfo();
		paginationInfo.setBlockSize(ConstUtil.BLOCK_SIZE);
		paginationInfo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
		paginationInfo.setCurrentPage(searchVo.getCurrentPage());
		
		//[2]searchVo에 값 세팅
		searchVo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
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
	
	@RequestMapping("/categoryList")
	public String categotyList(@RequestParam(defaultValue = "0") int groupNo,Model model) {
		//1
		logger.info("글목록, 파라미터 groupNo=,", groupNo);
		
		//[1]paginnationInfo 객체 생성 - 계산해줌
		PaginationInfo paginationInfo=new PaginationInfo();
		paginationInfo.setBlockSize(ConstUtil.BLOCK_SIZE);
		paginationInfo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
		
		
		//[2]searchVo에 값 세팅
	
		
		List<usedBoardVO> list=usedBoardService.selectByGroupNo(groupNo);
		logger.info("전체조회 결과 list.size={}",list.size());
		
		//3.model에 결과 저장
		model.addAttribute("list",list);
		model.addAttribute("pagingInfo",paginationInfo);
		
		
		return "/usedBoard/board";
	}
	
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
		
		model.addAttribute("vo", vo);
		
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

}
