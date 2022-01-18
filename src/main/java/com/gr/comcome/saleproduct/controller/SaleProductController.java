package com.gr.comcome.saleproduct.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gr.comcome.common.ConstUtil;
import com.gr.comcome.common.PaginationInfo;
import com.gr.comcome.common.SearchVO;
import com.gr.comcome.saleproduct.model.SaleProductService;
import com.gr.comcome.saleproduct.model.SaleProductVO;
import com.gr.comcome.usedBoard.model.usedBoardVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/saleProduct")
public class SaleProductController {
	
	private final SaleProductService saleProductService;
	
	@Autowired
	public SaleProductController(SaleProductService saleProductService) {
		this.saleProductService=saleProductService;
		log.info("생성자주입!");
	}
	
	@RequestMapping("/list")
	public String list(@ModelAttribute SearchVO searchVo,Model model) {
		//1
		log.info("특가글목록, 파라미터 searchVo={},", searchVo);
		
		
		//[1]paginnationInfo 객체 생성 - 계산해줌
		PaginationInfo paginationInfo=new PaginationInfo();
		paginationInfo.setBlockSize(ConstUtil.BLOCK_SIZE);
		paginationInfo.setRecordCountPerPage(ConstUtil.RECORD_COUNT2);
		paginationInfo.setCurrentPage(searchVo.getCurrentPage());
		
		//[2]searchVo에 값 세팅
		searchVo.setRecordCountPerPage(ConstUtil.RECORD_COUNT2);
		searchVo.setFirstRecordIndex(paginationInfo.getFirstRecordIndex());
		log.info("값 세팅 후 searchVo={}"+searchVo);
		
		List<SaleProductVO> list=saleProductService.selectAllProduct(searchVo);
		log.info("전체조회 결과 list.size={}"+list.size());
		
		//[3]
		int totalRecord=saleProductService.selectTotalRecord(searchVo);
		paginationInfo.setTotalRecord(totalRecord);
		
		//3.model에 결과 저장
		model.addAttribute("list",list);
		model.addAttribute("pagingInfo",paginationInfo);
		
		
		return "/saleproduct/list";
	}
	
	@RequestMapping(value="/detail", method = RequestMethod.GET)
	public String checkemail_get(@RequestParam(required = false) int saleProductNo, Model model){
		//request 세션, response 쿠키	
		log.info("특가상품 상세 화면");
		log.info("특가상품번호 파라미터, saleProductNo={}", saleProductNo);
		
		
		SaleProductVO vo=saleProductService.selectByNo(saleProductNo);
		log.info("조회상품 정보, vo={}", vo.getThumbNailImg());
		model.addAttribute("vo", vo);
		return "/saleproduct/detail";
	}
}
