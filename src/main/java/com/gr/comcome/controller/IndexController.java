
package com.gr.comcome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gr.comcome.admin.model.AdminService;
import com.gr.comcome.admin.model.NoticeVO;
import com.gr.comcome.common.ConstUtil;
import com.gr.comcome.common.PaginationInfo;
import com.gr.comcome.common.SearchVO;
import com.gr.comcome.saleproduct.model.SaleProductService;
import com.gr.comcome.saleproduct.model.SaleProductVO;
import com.gr.comcome.usedBoard.model.usedBoardService;
import com.gr.comcome.usedBoard.model.usedBoardVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IndexController {
	
	private AdminService adminService;
	
	@Autowired
	private SaleProductService saleProductService;
	
	
	@Autowired
	private usedBoardService usedBoardService;
    
	 @Autowired
	public IndexController(AdminService adminService) {
		this.adminService = adminService;
	}

	// localhost:9091/comcome/
	@RequestMapping("/")
	public String index(Model model,@ModelAttribute SearchVO searchVO) {
		log.info("Enter index()");
		NoticeVO vo = adminService.selectRecentNotice();
		String content = vo.getContent();
		String contentbr = content.replaceAll("\n", "<br />");
		String title = vo.getTitle();
		String titlebr = title.replaceAll("\n", "<br />");
		model.addAttribute("content", contentbr);
		model.addAttribute("title", titlebr);
		model.addAttribute("vo", vo);
		
		
		PaginationInfo pagingInfo = new PaginationInfo();
		pagingInfo.setBlockSize(ConstUtil.BLOCK_SIZE);
		pagingInfo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
		pagingInfo.setCurrentPage(searchVO.getCurrentPage());

		searchVO.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
		searchVO.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
		log.info("값 셋팅 후 searchVo={}", searchVO);
		
		//특가 상품 관리 
		List<SaleProductVO> list = saleProductService.selectAllProduct(searchVO);
	
		log.info("전체조회 결과 list.size={}", list.size());
		for (SaleProductVO saleProductVO : list) {
			log.info(saleProductVO.toString());
		}
		
		//중고 거래 게시판 
		List<usedBoardVO> list2 = usedBoardService.selectAll(searchVO);
		log.info("전체조회 결과 list.size={}", list.size());
		for (usedBoardVO usedBoardVO : list2) {
			log.info(usedBoardVO.toString());
		}
		
		
		  for(int i=0; i< list2.size(); i++) { //만약 '내용' 컬럼이 10자가 넘으면
		  if(list2.get(i).getContent().length()>=10) {
		  list2.get(i).setContent(list2.get(i).getContent().substring(0, 9)); } }
		 
		
		
		
		int totalRecord = usedBoardService.selectTotalRecord(searchVO);

		model.addAttribute("list2", list2);
		model.addAttribute("list", list);
	
		
		return "/index";
	}
	
	//localhost:9091/comcome/indexcontent/indexcontent
	@GetMapping("/indexcontent")
	public String indexcontent() {
		return "/indexcontent/indexcontent";
	}
	
	@RequestMapping("/message")
	public String message() {
		return "/common/message";
	}
}

