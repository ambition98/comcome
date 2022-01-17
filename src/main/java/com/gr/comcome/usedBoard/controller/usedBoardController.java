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
	
	@Autowired
	public usedBoardController(usedBoardService boardService,commentService commentService,FileUploadUtil fileUploadUtil, AccountService accountService) {
		this.usedBoardService=boardService;
		this.commentService=commentService;
		this.fileUploadUtil=fileUploadUtil;
		this.accountService=accountService;
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
	
	@RequestMapping(value="/write", method = RequestMethod.GET)
	public String write_get() {
		logger.info("글쓰기 화면");
		
		return "usedBoard/write";
	}
	
	
	
	@RequestMapping(value="/write", method = RequestMethod.POST)
	public String write_post(@ModelAttribute usedBoardVO usedboardvo,
			HttpServletRequest request) {
		//1
		logger.info("글쓰기 처리, 파라미터 vo={}", usedboardvo);
		
		//파일 업로드 처리
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
			
			logger.info("파일 업로드 성공, fileName={}", fileName);
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
		logger.info("글쓰기 결과, cnt={}", cnt);
		
		//3		
		
		//4
		return "redirect:/usedBoard/list";
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public String edit_get(@RequestParam(defaultValue = "0") int boardNo, 
			Model model) {
		logger.info("글수정 화면");
		if(boardNo==0) {
			model.addAttribute("msg", "잘못된 url입니다.");
			model.addAttribute("url", "/usedBoard/board");
			
			return "common/message";
		}
		usedBoardVO vo=usedBoardService.selectByNo(boardNo);
		logger.info("글수정 상세보기 결과 vo={}", vo);
		
		
		
		model.addAttribute("vo", vo);
		
		
		return "usedBoard/edit";
	}
	
	@PostMapping(value="/edit")
	public String edit_post(@ModelAttribute usedBoardVO vo, 
			@RequestParam String oldFileName,
			HttpServletRequest request, Model model) {
		//1
		logger.info("글수정 처리, 파라미터 vo={}, oldFileName={}", vo, oldFileName);
		String path="/usedboard";
		//업로드 처리
		String fileName="";
		
		try {
			List<Map<String, Object>> fileList
			=fileUploadUtil.fileUpload(request, path);
			
			for(Map<String, Object> fileMap: fileList) {
				fileName=(String)fileMap.get("fileName");
				vo.setFileName(fileName);
				vo.setOriginalFileName((String)fileMap.get("originalFileName"));
				vo.setFileSize((Long)fileMap.get("fileSize"));				
			}//for
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} 
		
		//2
		String msg="글수정 실패", url="/usedBoard/edit?BoardNo="+vo.getBoardNo();
		
			int cnt=usedBoardService.updateBoard(vo);
			if(cnt>0) {
				msg="글수정되었습니다.";
				url="/usedBoard/detail.do?BoardNo="+vo.getBoardNo();
				
				//새로 업로드하는 경우, 기존파일이 있다면 기존파일 삭제처리
				if(fileName!=null && !fileName.isEmpty() 
						&& oldFileName !=null && !oldFileName.isEmpty()) {
					String upPath 
			= ConstUtil.USER_FILE_UPLOAD_ROOT_PATH;
					File oldFile = new File(upPath, oldFileName);
					if(oldFile.exists()) {
						boolean bool =oldFile.delete();
						logger.info("글수정,파일삭제여부:{}", bool);
					}
				}
			}
		
		
		//3
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		//4
		return "common/message";
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String delete_post(@ModelAttribute usedBoardVO vo, 
			HttpServletRequest request, Model model) {
		//1
		logger.info("글삭제 처리, 파라미터 vo={}", vo);
		
		//2
		String msg="글삭제 실패", url="/usedBoard/delete?boardNo="
				+vo.getBoardNo()+"&step="+vo.getStep()+"&groupNo="+vo.getGroupNo()
				+"&fileName="+vo.getFileName();
		
			//삭제 - 저장 프로시저 이용
		
			usedBoardService.deleteBoardByNo(vo.getBoardNo());			
			msg="글삭제되었습니다.";
			url="/usedBoard/list";		
			
			//파일 삭제
			String fileName=vo.getFileName();
			if(fileName!=null && !fileName.isEmpty()) {
				String upPath
			=fileUploadUtil.getUploadPath( request)+"usedboard";
				logger.info("파일삭제경로"+ upPath);
				File oldFile = new File(upPath ,fileName);
				if(oldFile.exists()) {
					boolean bool=oldFile.delete();
					logger.info("파일삭제 여부:{}", bool);
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
		log.info("[/profile] account_id args : {"+account_id+"}");
		
		AccountVO vo= accountService.selectAccountByEmail(account_id);
		
		
		model.addAttribute("vo",vo);
		return null;
		//return "redirect:/usedBoard/boardDetail?boardNo="+1;
	}
	
}
