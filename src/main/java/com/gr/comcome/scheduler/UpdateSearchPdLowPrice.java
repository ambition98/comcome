package com.gr.comcome.scheduler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gr.comcome.common.mallapi.NaverAPI;
import com.gr.comcome.common.mallapi.Product;
import com.gr.comcome.search_pd.model.SearchProductService;

@Component
public class UpdateSearchPdLowPrice {
	private final SearchProductService searchProductService;
	private final NaverAPI naverApi;

	public UpdateSearchPdLowPrice(SearchProductService searchProductService, NaverAPI naverApi) {
		this.searchProductService = searchProductService;
		this.naverApi = naverApi;
	}
	
	@Scheduled(cron = "* * * * * *")
	public void update() {
//		Map<String, Product> pdMap = null;
//		List<Product> pdList = new ArrayList<Product>();
//		try {
//			pdMap = naverApi.getProduct(vo.getName());
//			pdMap.forEach((key, value) -> {
//				pdList.add(value);
//			});
//		} catch (ParserConfigurationException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		Collections.sort(pdList);
		System.out.println("Every sec...");
	}
}
