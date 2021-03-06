package com.gr.comcome.usedBoard.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gr.comcome.account.model.AccountService;
import com.gr.comcome.account.model.AccountVO;
import com.gr.comcome.comment.model.commentService;
import com.gr.comcome.comment.model.commentVO;
import com.gr.comcome.common.ConstUtil;
import com.gr.comcome.common.FileUploadUtil;
import com.gr.comcome.common.PaginationInfo;
import com.gr.comcome.common.SearchVO;
import com.gr.comcome.messagebox.model.MessageBoxService;
import com.gr.comcome.messagebox.model.MessageBoxVO;
import com.gr.comcome.messagebox.model.MessageRecvService;
import com.gr.comcome.messagebox.model.MessageRecvVO;
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
	private final FileUploadUtil fileUploadUtil;
	private final AccountService accountService;
	private final MessageBoxService messageBoxService;
	private final MessageRecvService messageRecvService;
	
	
	@Autowired
	public usedBoardController(usedBoardService boardService,commentService commentService
			,FileUploadUtil fileUploadUtil, AccountService accountService
			,MessageBoxService boxService,MessageRecvService recvService) {
		this.usedBoardService=boardService;
		this.commentService=commentService;
		this.fileUploadUtil=fileUploadUtil;
		this.accountService=accountService;
		this.messageBoxService=boxService;
		this.messageRecvService=recvService;
		logger.info("???????????????!");
	}

	@RequestMapping("/list")
	public String list(@ModelAttribute SearchVO searchVo,Model model) {
		//1
		logger.info("?????????, ???????????? searchVo={},", searchVo);
		
		
		//[1]paginnationInfo ?????? ?????? - ????????????
		PaginationInfo paginationInfo=new PaginationInfo();
		paginationInfo.setBlockSize(ConstUtil.BLOCK_SIZE);
		paginationInfo.setRecordCountPerPage(ConstUtil.RECORD_COUNT2);
		paginationInfo.setCurrentPage(searchVo.getCurrentPage());
		
		//[2]searchVo??? ??? ??????
		searchVo.setRecordCountPerPage(ConstUtil.RECORD_COUNT2);
		searchVo.setFirstRecordIndex(paginationInfo.getFirstRecordIndex());
		logger.info("??? ?????? ??? searchVo={}",searchVo);
		
		List<usedBoardVO> list=usedBoardService.selectAll(searchVo);
		logger.info("???????????? ?????? list.size={}",list.size());
		
		//[3]
		int totalRecord=usedBoardService.selectTotalRecord(searchVo);
		paginationInfo.setTotalRecord(totalRecord);
		
		//3.model??? ?????? ??????
		model.addAttribute("list",list);
		model.addAttribute("pagingInfo",paginationInfo);
		
		
		return "/usedBoard/board";
	}
	/*
	@RequestMapping("/category2")
	public String categotyList(Model model) {
		//1
		logger.info("??????????????? ???????????? ??????");
		
		//[1]paginnationInfo ?????? ?????? - ????????????
		PaginationInfo paginationInfo=new PaginationInfo();
		paginationInfo.setBlockSize(ConstUtil.BLOCK_SIZE);
		paginationInfo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
		
		
		//[2]searchVo??? ??? ??????
	
		
		
		logger.info("???????????? ?????? list.size={}",list.size());
		
		//3.model??? ?????? ??????
		model.addAttribute("list",list);
		model.addAttribute("pagingInfo",paginationInfo);
		
		
		return "/usedBoard/category2";
	}
	*/
	@RequestMapping("/boardDetail")
	public String detail(@RequestParam(defaultValue = "0") int boardNo, Model model) {
		logger.info("??? ???????????? ???????????? boardNo={}", boardNo);
		
		if(boardNo==0) {
			model.addAttribute("msg", "????????? url?????????.");
			model.addAttribute("url", "/usedBoard/board");
			
			return "common/message";
		}
		
		usedBoardVO vo=usedBoardService.selectByNo(boardNo);
		logger.info("???????????? ?????? vo={}", vo);
		
		List<commentVO> list2=commentService.selectByNo(boardNo);
		log.info("???????????? ?????? ??????list="+list2);
		
		model.addAttribute("vo", vo);
		model.addAttribute("list2",list2);
		
		return "usedBoard/boardDetail";
	}
	
	@RequestMapping("/countUpdate")
	public String countUpdate(@RequestParam(defaultValue = "0") int boardNo, 
			Model model) {
		logger.info("????????? ?????? ???????????? no={}", boardNo);
		
		if(boardNo==0) {
			model.addAttribute("msg", "????????? url?????????.");
			model.addAttribute("url", "/usedBoard/board");
			
			return "common/message";
		}
		
		int cnt=usedBoardService.updateReadCount(boardNo);
		logger.info("????????? ?????? ?????? cnt={}", cnt);
		
		return "redirect:/usedBoard/boardDetail?boardNo="+boardNo;		
	}
	
	
	
	@RequestMapping(value ="/list_ajax")
    public String list_expertmb_ajax(@ModelAttribute SearchVO searchVo,         
    		@RequestParam(defaultValue = "0") String kind,
            Model model) {
        
             
        
        logger.info("???????????? kind = {}",kind);
        logger.info("???????????? searchVo = {}",searchVo);
       
        /*
        int result=0;
        if("?????????".equals(kind))
        {
        	result=1;
        }else if("????????? ????????????".equals(kind)){
        	result=2;
        }else if("?????? pc??????".equals(kind)) {
        	result=3;
        }
        
        logger.info("result:{}",result);
        */
       
        
        List<usedBoardVO> list=usedBoardService.selectByGroupNo(kind);;
		logger.info("???????????? ?????? list.size={}",list.size());
        
        model.addAttribute("list",list);
      
        return "/usedBoard/board";
    }
	
	@RequestMapping(value ="/list_ajax2" , method = RequestMethod.GET)
    public String list_expertmb_ajax2(@ModelAttribute SearchVO searchVo,          
    		@RequestParam(defaultValue = "0") String groupNo,
            Model model) {
        
  
        logger.info("???????????? groupNo = {}",groupNo);
      	logger.info("?????????, ???????????? searchVo={},", searchVo);
      		
      		
      		//[1]paginnationInfo ?????? ?????? - ????????????
      		PaginationInfo paginationInfo=new PaginationInfo();
      		paginationInfo.setBlockSize(ConstUtil.BLOCK_SIZE);
      		paginationInfo.setRecordCountPerPage(ConstUtil.RECORD_COUNT2);
      		paginationInfo.setCurrentPage(searchVo.getCurrentPage());
      		
      		//[2]searchVo??? ??? ??????
      		searchVo.setRecordCountPerPage(ConstUtil.RECORD_COUNT2);
      		searchVo.setFirstRecordIndex(paginationInfo.getFirstRecordIndex());
      		logger.info("??? ?????? ??? searchVo={}",searchVo);
      		
      		Map<String,Object>map = new HashMap<String,Object>();
      		map.put("groupNo", groupNo);
      		map.put("recordCountPerPage",8);
      		map.put("firstRecordIndex",1);
      		
      		
      		List<usedBoardVO> list=usedBoardService.selectByGroupNo2(map);
      		logger.info("???????????? ?????? list.size={}",list.size());
      		
      		//[3]
      		int totalRecord=usedBoardService.selectTotalRecord(searchVo);
      		paginationInfo.setTotalRecord(totalRecord);
      		
      		//3.model??? ?????? ??????
      		model.addAttribute("list",list);
      		model.addAttribute("pagingInfo",paginationInfo);
      
        return "usedBoard/board";
    }
	
	@RequestMapping(value="/write", method = RequestMethod.GET)
	public String write_get() {
		logger.info("????????? ??????");
		
		return "usedBoard/write";
	}
	
	
	
	@RequestMapping(value="/write", method = RequestMethod.POST)
	public String write_post(@ModelAttribute usedBoardVO usedboardvo,
			HttpServletRequest request) {
		//1
		logger.info("????????? ??????, ???????????? vo={}", usedboardvo);
		
		//?????? ????????? ??????
		String fileName="", originName="";
		long fileSize=0;
		String path="/usedboard";
		try {
			List<Map<String, Object>> fileList 
				= fileUploadUtil.fileUpload(request, path);
			for(int i=0;i<fileList.size();i++) {
				 Map<String, Object> map=fileList.get(i);
				 
				 fileName=(String) map.get("fileName");
				 originName=(String) map.get("originFileName");
				 fileSize=(long) map.get("fileSize");				 
			}
			
			logger.info("?????? ????????? ??????, fileName={}", fileName);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} 
		
		//2
		usedboardvo.setFileName(fileName);
		usedboardvo.setOriginalFileName(originName);
		usedboardvo.setFileSize(fileSize);
		usedboardvo.setSortNo(1);
		usedboardvo.setDownCount(0);
		usedboardvo.setStep(0);
		
		int cnt=usedBoardService.insertBoard(usedboardvo);
		logger.info("????????? ??????, cnt={}", cnt);
		
		//3		
		
		//4
		return "redirect:/usedBoard/list";
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public String edit_get(@RequestParam(defaultValue = "0") int boardNo, 
			Model model) {
		logger.info("????????? ??????");
		if(boardNo==0) {
			model.addAttribute("msg", "????????? url?????????.");
			model.addAttribute("url", "/usedBoard/board");
			
			return "common/message";
		}
		usedBoardVO vo=usedBoardService.selectByNo(boardNo);
		logger.info("????????? ???????????? ?????? vo={}", vo);
		
		
		
		model.addAttribute("vo", vo);
		
		
		return "usedBoard/edit";
	}
	
	@RequestMapping(value="/edit" , method = RequestMethod.POST)
	public String edit_post(@ModelAttribute usedBoardVO vo, 
			@RequestParam String oldFileName,
			HttpServletRequest request, Model model) {
		//1
		logger.info("????????? ??????, ???????????? vo={}, oldFileName={}", vo, oldFileName);
		
		//?????? ????????? ??????
				String fileName="", originName="";
				long fileSize=0;
				String path="/usedboard";
				try {
					List<Map<String, Object>> fileList 
						= fileUploadUtil.fileUpload(request, path);
					for(int i=0;i<fileList.size();i++) {
						 Map<String, Object> map=fileList.get(i);
						 
						 fileName=(String) map.get("fileName");
						 originName=(String) map.get("originFileName");
						 fileSize=(long) map.get("fileSize");				 
					}
					
					logger.info("?????? ????????? ??????, fileName={}", fileName);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} 
				
				//2
				vo.setFileName(fileName);
				vo.setOriginalFileName(originName);
				vo.setFileSize(fileSize);
				
				
				int cnt=usedBoardService.updateBoard(vo);
				logger.info("????????? ??????, cnt={}", cnt);
		//2
		String msg="????????? ??????", url="/usedBoard/edit?BoardNo="+vo.getBoardNo();
		
			
			if(cnt>0) {
				msg="????????????????????????.";
				url="/usedBoard/detail?BoardNo="+vo.getBoardNo();
				
				//?????? ??????????????? ??????, ??????????????? ????????? ???????????? ????????????
				if(fileName!=null && !fileName.isEmpty() 
						&& oldFileName !=null && !oldFileName.isEmpty()) {
					String upPath 
			= ConstUtil.USER_FILE_UPLOAD_ROOT_PATH;
					File oldFile = new File(upPath, oldFileName);
					if(oldFile.exists()) {
						boolean bool =oldFile.delete();
						logger.info("?????????,??????????????????:{}", bool);
					}
				}
			}
		
		
		//3
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		//4
		return "redirect:/usedBoard/boardDetail?boardNo="+vo.getBoardNo();
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String delete_post(@ModelAttribute usedBoardVO vo, 
			HttpServletRequest request, Model model) {
		//1
		logger.info("????????? ??????, ???????????? vo={}", vo);
		
		//2
		String msg="????????? ??????", url="/usedBoard/delete?boardNo="
				+vo.getBoardNo()+"&step="+vo.getStep()+"&groupNo="+vo.getGroupNo()
				+"&fileName="+vo.getFileName();
		
			//?????? - ?????? ???????????? ??????
		
			usedBoardService.deleteBoardByNo(vo.getBoardNo());			
			msg="????????????????????????.";
			url="/usedBoard/list";		
			
			//?????? ??????
			String fileName=vo.getFileName();
			if(fileName!=null && !fileName.isEmpty()) {
				String upPath
			=fileUploadUtil.getUploadPath( request)+"usedboard";
				logger.info("??????????????????"+ upPath);
				File oldFile = new File(upPath ,fileName);
				if(oldFile.exists()) {
					boolean bool=oldFile.delete();
					logger.info("???????????? ??????:{}", bool);
				}
			}
		
		
		//3
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		//4
		return "common/message";
	}
	

	@RequestMapping(value = "/profile" , method=RequestMethod.GET)
	public String write(String account_id,Model model){
		log.info("[/profile] account_id args : {"+account_id+"}");
		
		AccountVO vo= accountService.selectAccountByEmail(account_id);
		log.info("" + vo);
		
		model.addAttribute("vo",vo);
		return null;
		//return "redirect:/usedBoard/boardDetail?boardNo="+1;
	}
	
	@RequestMapping(value = "/message" , method=RequestMethod.GET)
	public String message(String account_id,Model model){
		log.info("????????? ????????? ????????????");
		
		AccountVO vo= accountService.selectAccountByEmail(account_id);
		
		
		model.addAttribute("vo",vo);
		return "usedBoard/message";
		
	}
	
	@RequestMapping(value = "/message" , method=RequestMethod.POST)
	public String message_ok(@ModelAttribute MessageBoxVO boxVo
			,HttpServletRequest request){
		log.info("MessageBoxVO = "+boxVo+"}");
		
		
		MessageRecvVO vo2=new MessageRecvVO();
		
		vo2.setAccountNo(boxVo.getAccountno());
		vo2.setMessageNo(boxVo.getMessageno());
		
		int cnt=messageBoxService.insertMessageBox(boxVo);
		int cnt2=messageRecvService.insertMessageRecv(vo2);
		logger.info("?????? ????????? ??????, cnt={}", cnt);
		return null;
	
	}
	
}
