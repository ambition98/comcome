package com.gr.comcome.common;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class MyHttpRequest {
	public Document getHttpDocument(String link) throws IOException, InterruptedException {
//		link = "https://www.naver.com";
		//System.out.println("link: " + link);
		StringBuilder sb = new StringBuilder();
		URL url = new URL(link);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		//conn.setRequestMethod("GET");
		conn.setRequestProperty("Referer", "http://prod.danawa.com/");
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.109 Safari/537.36");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		String line;
		while((line = br.readLine()) != null)
			sb.append(line);
		
		//System.out.println(sb.toString());
		
		Thread.sleep(20);
		return Jsoup.parse(sb.toString());
	}
	
	/**
	 * 다나와 상품 상세페이지에서 호출하는 ajax를 호출하여 document 형태로 리턴
	 * 
	 * @param code - 다나와 상품 코드
	 * 				 ajax의 페이로드로 전달됨
	 */
	public Document postRequestToDanawaAjax(String code) throws IOException {
		String requestUrl = "http://prod.danawa.com/info/ajax/getProductDescription.ajax.php";
		String host = "prod.danawa.com";
		String origin = "http://prod.danawa.com";
		String referer = "http://prod.danawa.com/info/?pcode=" + code;
		String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36";
		String payload = "pcode=" + code;
		URL url = new URL(requestUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		conn.setRequestMethod("POST");
		conn.setRequestProperty("host", host);
		conn.setRequestProperty("origin", origin);
		conn.setRequestProperty("referer", referer);
		conn.setRequestProperty("userAgent", userAgent);
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setDoOutput(true);
		
		// x-www-form-urlencoded 형태 payload 삽입
		DataOutputStream writer = new DataOutputStream(conn.getOutputStream());
		writer.write(payload.getBytes(StandardCharsets.UTF_8));
		writer.close();
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		String line = "";
		while((line = br.readLine()) != null)
			sb.append(line);
		
//		System.out.println("--- ajax ---");
//		System.out.println(sb);
		return Jsoup.parse(sb.toString());
	}
}
