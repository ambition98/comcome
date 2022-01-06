package com.gr.comcome.common.mallapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

/**
 * * 표시는 필수
 * @param *keyword - 검색어
 * @param display  - 검색 결과 출력 건수, default 10, max 100
 * @param start    - 검색 시작 위치, default 1, max 100
 * @param sort     - 정렬옵션 sim(유사도순), default date(날짜순) asc(가격 오름차순) dsc(가격 내림차순)
 * 
 * @return
 * @throws ParserConfigurationException
 * @throws IOException 
 */
@Component
public class NaverAPI {
	private final String URL = "https://openapi.naver.com/v1/search/shop.json?query=";
	private final String ID = "TxHTr7MUAWXGUkPBeGwD";
	private final String SECRET = "3YFVy0W_rN";

	public void getProduct(String keyword) throws ParserConfigurationException, IOException {
		String display = "10";
		String start = "1";
		String sort = "sim";
		
		getProduct(keyword, display, start, sort);
	}
	
	public void getProduct(String keyword, String display, String start, String sort)
			throws ParserConfigurationException, IOException {

		HttpURLConnection conn = null;
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

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
		System.out.println(url);
		conn = (HttpURLConnection) url.openConnection();

		conn.setRequestMethod("GET");
		conn.setRequestProperty("X-Naver-Client-Id", ID);
		conn.setRequestProperty("X-Naver-Client-Secret", SECRET);

		br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

		String line = "";
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		System.out.println(sb);
		
		JSONObject MainObj;
		try {
			MainObj = new JSONObject(sb.toString());
			JSONArray items = MainObj.getJSONArray("items");
			for(int i=0; i<items.length(); i++) {
				JSONObject item = items.getJSONObject(i);
				if(item.getString("category1").equals("디지털/가전")
						&& item.getString("category2").equals("노트북")) {
					String title = item.getString("title");
					String price = item.getString("lprice");
					System.out.println(title);
					System.out.println(price);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
//		eSystem.out.println(sb.toString().replaceAll("}", "}\n"));

		conn.disconnect();
		br.close();
	}
}
