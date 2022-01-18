package com.gr.comcome.scheduler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gr.comcome.common.mallapi.NaverAPI;
import com.gr.comcome.common.mallapi.Product;
import com.gr.comcome.search_pd.model.SearchProductService;
import com.gr.comcome.search_pd.model.SearchProductVO;
import com.gr.comcome.search_pd.pricelog.model.SearchPdPriceLogService;
import com.gr.comcome.search_pd.pricelog.model.SearchPdPriceLogVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@EnableScheduling
public class UpdateSearchPdLowPrice {
	private final SearchProductService searchProductService;
	private final SearchPdPriceLogService searchPdPriceLogService;
	
	public UpdateSearchPdLowPrice(SearchProductService searchProductService,
			SearchPdPriceLogService searchPdPriceLogService) {
		this.searchProductService = searchProductService;
		this.searchPdPriceLogService = searchPdPriceLogService;
	}

	String lowMall = "";
	int lowPrice = Integer.MAX_VALUE;
	
	//@Scheduled(fixedRate = 1000 * 60 * 30)
	//@Scheduled(cron = "0 0 0/6 * * *")
	public void insertLog() {
		/*
		 * 1. 모든 search_pd의 name 가져오기
		 * 2. naverapi로 name을 검색하여 최저가 가져오기
		 * 3. db에 insert
		 */
		List<SearchProductVO> searchPdVoList = searchProductService.selectAll();
		Map<String, Product> pdMap = null;
		List<Product> pdList = new ArrayList<Product>();
		NaverAPI naverApi = new NaverAPI();
		try {
			for(SearchProductVO vo : searchPdVoList) {
				pdMap = naverApi.getProduct(vo.getName());
				
				pdMap.forEach((k, v) -> {
					if(lowPrice > v.getPrice()) {
						lowMall = k;
						lowPrice = v.getPrice();
					}
				});
				
				System.out.println("searchPdNo: " + vo.getSearchProductNo());
				System.out.println("lowMall: " + lowMall);
				System.out.println("lowPrice: " + lowPrice);
				System.out.println("-----------------");
				
				SearchPdPriceLogVO searchPdPriceLogVo = new SearchPdPriceLogVO();
				searchPdPriceLogVo.setSearchProductNo(vo.getSearchProductNo());
				searchPdPriceLogVo.setLowPrice(lowPrice);
				boolean isSucceeded = searchPdPriceLogService.insertLowPrice(searchPdPriceLogVo);
				if(!isSucceeded) {
					log.warn("최저가 insert 오류!!!");
					return;
				} else {
					log.info("최저가 insert 성공");
					lowMall = "";
					lowPrice = Integer.MAX_VALUE;
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
