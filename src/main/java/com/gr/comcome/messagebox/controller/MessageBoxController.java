package com.gr.comcome.messagebox.controller;

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
import com.gr.comcome.messagebox.model.MessageBoxService;
import com.gr.comcome.messagebox.model.MessageBoxVO;

@Controller
@RequestMapping("/mypage") 
public class MessageBoxController { 
	private static final
	Logger logger =LoggerFactory.getLogger(MessageBoxController.class);
	
	@Autowired
	private MessageBoxService messageboxService;
		
	@RequestMapping("/messagebox")
	public String messagebox(@ModelAttribute SearchVO searchVo,Model model) {
		
	logger.info("쪽지 목록 화면, searchVo={}", searchVo);
		
		//2
	      PaginationInfo pagingInfo = new PaginationInfo();
	      pagingInfo.setBlockSize(ConstUtil.BLOCK_SIZE);
	      pagingInfo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
	      pagingInfo.setCurrentPage(searchVo.getCurrentPage());
	      
	      //3
	      searchVo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
	      searchVo.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
	      logger.info("값 셋팅 후 searchVo={}", searchVo);
	      
	      
	      List<MessageBoxVO> list = messageboxService.selectAll(searchVo);
	      logger.info("전체조회 결과 list.size={}", list.size());
	      for(MessageBoxVO vo : list) {
	    	  logger.info(vo.toString());
	      }
	      //4
	      int totalRecord=messageboxService.selectTotalRecord(searchVo);
	      pagingInfo.setTotalRecord(totalRecord);
	      
	      model.addAttribute("list", list);
	      model.addAttribute("pagingInfo", pagingInfo);
	      
	      return "messagebox/messagebox";
	     
	    
	}
}
