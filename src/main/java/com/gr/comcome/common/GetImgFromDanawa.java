package com.gr.comcome.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class GetImgFromDanawa {
	public List<String> getImg(String code) {
		List<String> imgLinkList = new ArrayList<String>();
		String link = "http://prod.danawa.com/info/?pcode=" + code;
		Document doc = null;
		
		// 다나와 상품상세 페이지 html
		try {
			doc = new MyHttpRequest().getHttpDocument(link);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		String img = doc.select("#imgExtensionAnchorLayer")
						.select("img")
						.attr("src");
		imgLinkList.add(img);
		
		// 다나와 상품상세 페이지에서 호출하는 ajax의 html
		try {
			doc = new MyHttpRequest().postRequestToDanawaAjax(code);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements imgTags = doc.getElementsByClass("inner")
								.get(0)
								.getElementsByTag("img");
		
		for(Element e : imgTags) {
			imgLinkList.add(e.attr("src"));
		}
		
		for(int i=0; i<imgLinkList.size(); i++) {
			if(imgLinkList.get(i).contains("dnwCopyrightBanner"))
				imgLinkList.remove(i);
		}
		
		return imgLinkList;
	}
}
