package com.gr.comcome.search_pd.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gr.comcome.category.model.CategoryVO;
import com.gr.comcome.common.GetImgFromDanawa;
import com.gr.comcome.common.mallapi.NaverAPI;
import com.gr.comcome.common.mallapi.Product;
import com.gr.comcome.search_pd.model.SearchProductService;
import com.gr.comcome.search_pd.model.SearchProductVO;
import com.gr.comcome.search_pd.pricelog.model.SearchPdPriceLogService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequestMapping("/searchpd")
public class SearchProductController {
	private final SearchProductService searchProductService;
	private final SearchPdPriceLogService searchPdPriceLogService;

	public SearchProductController(SearchProductService searchProductService,
			SearchPdPriceLogService searchPdPriceLogService) {
		this.searchProductService = searchProductService;
		this.searchPdPriceLogService = searchPdPriceLogService;
	}

	@RequestMapping("/list")
	public String list(Model model
			,@ModelAttribute() CategoryVO vo
			,@RequestParam(value = "keyword", required = false) String keyword) {
		
		log.info("Enter list()");
		log.info("keyword: " + keyword);
//		log.info("vo: " + vo.toString()); //파라미터 전달 안해도 null은 아님
		//List<SearchProductVO> searchPdList = null;
		
		model.addAttribute("brandNo", vo.getBrandNo());
		model.addAttribute("screenSizeNo", vo.getScreenSizeNo());
		if(keyword != null && !keyword.isBlank()) {
			model.addAttribute("keyword", keyword);
		}
		
		return "/search_pd/list";
	}
	
	@ResponseBody
	@PostMapping("/changelist")
	public List<SearchProductVO> changeList(@RequestBody String data) {
		log.info("Enter changelist()");
		log.info(data);
		
		List<SearchProductVO> voList = null;
		JSONObject jObject = new JSONObject(data);
		
		if(jObject.has("keyword")) {
			//검색어 or 전체카테고리 선택
			String keyword = jObject.getString("keyword");
			
			if(keyword.equals("all")) {
				voList = searchProductService.selectAll();
			} else {
				voList = searchProductService.selectByKeyword(keyword);
			}
			
		} else {
			// 옵션버블 사용
			Map<String, Object> map = new HashMap<>();
			List<String> brandList = new ArrayList<>();
			List<String> screenSizeList = new ArrayList<>();
			List<String> cpuList = new ArrayList<>();
			List<String> memoryList = new ArrayList<>();
			
			JSONArray jBrand = jObject.getJSONArray("brand");
			JSONArray jScreenSize = jObject.getJSONArray("screenSize");
			JSONArray jCpu = jObject.getJSONArray("cpu");
			JSONArray jMemory = jObject.getJSONArray("memory");
			
			for(int i=0; i<jBrand.length(); i++)
				brandList.add(jBrand.getString(i));
			
			for(int i=0; i<jScreenSize.length(); i++)
				screenSizeList.add(jScreenSize.getString(i));
			
			for(int i=0; i<jCpu.length(); i++)
				cpuList.add(jCpu.getString(i));
			
			for(int i=0; i<jMemory.length(); i++)
				memoryList.add(jMemory.getString(i));
			
			map.put("brand", brandList);
			map.put("screenSize", screenSizeList);
			map.put("cpu", cpuList);
			map.put("memory", memoryList);
			//System.out.println(cpuList.size());
			
			voList = searchProductService.selectByOption(map);
	//		for(SearchProductVO vo : voList) {
	//			System.out.println(vo.getName());
	//			System.out.println(vo.getDetail());
	//			System.out.println("---------------------------");
	//		}
			log.info("vo size: " + voList.size());
			
			for(SearchProductVO vo : voList) {
				int lowPrice = searchPdPriceLogService.selectNewestPriceByPdNo(vo.getSearchProductNo());
				if(lowPrice == Integer.MAX_VALUE) {
					lowPrice = 0;
				}
				
				vo.setPrice(lowPrice);
			}
		}
		
		return voList;
	}
	
	@RequestMapping("/detail")
	public String detail(Model model, @RequestParam("pdNo") int pdNo) {
		log.info("Enter detail(), pdNo: " + pdNo);
		SearchProductVO vo = searchProductService.selectByNo(pdNo);
		if(vo == null) {
			String url = "/searchpd/list";
			String msg = "존재하지 않는 상품입니다.";
			
			model.addAttribute("url", url);
			model.addAttribute("msg", msg);
			
			return "forward:/message";
		}
		
		//네이버에서 검색결과 가져오기
		NaverAPI naverApi = new NaverAPI();
		Map<String, Product> pdMap = null;
		List<Product> pdList = new ArrayList<Product>();
		try {
			pdMap = naverApi.getProduct(vo.getName());
			pdMap.forEach((key, value) -> {
				pdList.add(value);
			});
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Collections.sort(pdList);
		
		//다나와에서 이미지 추출
		//System.out.println("code: " + vo.getCode());
		List<String> imgLinkList = new GetImgFromDanawa().getImg(vo.getCode());
		
		
		model.addAttribute("pdList", pdList);
		model.addAttribute("vo", vo);
		model.addAttribute("imgLinkList", imgLinkList);
		
		return "/search_pd/detail";
	}
}
