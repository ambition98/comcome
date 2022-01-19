package com.gr.comcome.common.mallapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.jsoup.nodes.Document;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

import com.gr.comcome.common.MyHttpRequest;

/**
 * @author YJ_Lee
 * 
 * * 표시는 필수
 * @param *keyword - 검색어
 * @param display  - 검색 결과 출력 건수, default 10, max 100
 * @param start    - 검색 시작 위치, default 1, max 100
 * @param sort     - 정렬옵션 sim(유사도순), default date(날짜순) asc(가격 오름차순) dsc(가격 내림차순)
 */

public class NaverAPI {
	private final String URL = "https://openapi.naver.com/v1/search/shop.json?query=";
	private final String ID = "TxHTr7MUAWXGUkPBeGwD";
	private final String SECRET = "3YFVy0W_rN";
	private final String PD_NUM = "20";

	public Map<String, Product> getProduct(String keyword) throws ParserConfigurationException, IOException {
		String display = PD_NUM;
		String start = "1";
		String sort = "sim";
		
		return getProduct(keyword, display, start, sort);
	}
	
	public Map<String, Product> getProduct(String keyword, String display, String start, String sort)
			throws ParserConfigurationException, IOException {

		HttpURLConnection conn = null;
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		if (keyword == null || keyword.isBlank())
			new IllegalArgumentException("keyword는 공백이나 null이 올 수 없습니다.");

		keyword = URLEncoder.encode(keyword, "utf-8");
		sb.append(URL + keyword);

		if (display != null && !display.isBlank()) {
			display = URLEncoder.encode(display, "utf-8");
			sb.append("&display=" + display);
		}
		if (start != null && !start.isBlank()) {
			start = URLEncoder.encode(start, "utf-8");
			sb.append("&start=" + start);
		}
		if (sort != null && !sort.isBlank()) {
			sort = URLEncoder.encode(sort, "utf-8");
			sb.append("&sort=" + sort);
		}

		URL url = new URL(sb.toString());
		sb.setLength(0);
		//System.out.println(url);
		conn = (HttpURLConnection) url.openConnection();

		conn.setRequestMethod("GET");
		conn.setRequestProperty("X-Naver-Client-Id", ID);
		conn.setRequestProperty("X-Naver-Client-Secret", SECRET);

		br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

		String line = "";
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		//System.out.println(sb);
		
		JSONObject mainObj;
		Map<String, Product> productMap = new HashMap<>();
		try {
			mainObj = new JSONObject(sb.toString());
			JSONArray items = mainObj.getJSONArray("items");
			
			for(int i=0; i<items.length(); i++) {
//			for(int i=0; i<1; i++) {
				JSONObject item = items.getJSONObject(i);
				if(item.getString("category1").equals("디지털/가전")
						&& item.getString("category2").equals("노트북")) {
					
					String mallName = item.getString("mallName");
					String productId = item.getString("productId");
//					String naverLink = "https://search.shopping.naver.com/product/" + productId;
//					String realLink = getRealLink(naverLink);
					String realLink = item.getString("link");
					int price = Integer.parseInt(item.getString("lprice"));
					Product newPd = new Product(mallName, realLink, price);
					
					if(newPd.getPrice() >= 600000) {
						//key가 이미 존재하면 해당 value반환, 존재하지 않으면 null후 newPd삽입
						Product mapPd = productMap.putIfAbsent(mallName, newPd);
						if(mapPd != null && (mapPd.getPrice() > newPd.getPrice())) {
							//이미 존재할 경우 가격 비교후 객체 교체
							productMap.put(mallName, newPd);
						}
					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			conn.disconnect();
			br.close();
		}

		return productMap;
	}
	
	private String getRealLink(String link) throws JSONException {
		/*
		 * 네이버측의 차단으로 사용 안함
		 */
		Document doc = null;
		try {
			doc = new MyHttpRequest().getHttpDocument(link);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("link: " + link);
		String data = doc.getElementById("__NEXT_DATA__").html();
		JSONObject mainObj = new JSONObject(data);
//		System.out.println(mainObj.toString(4));
		String realLink = mainObj.getJSONObject("props")
								.getJSONObject("pageProps")
								.getJSONObject("product")
								.getString("productUrl");
		
		return realLink;
	}
}