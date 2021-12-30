package com.gr.comcome.usedBoard.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
	/*
	@RequestMapping("/selectByNo")
	public String selectByNo(@ModelAttribute usedBoardVO vo) {
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
	}*/

}
