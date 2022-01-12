package com.gr.comcome.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gr.comcome.category.model.CategoryVO;
import com.gr.comcome.common.FileUploadUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IndexController {
	@RequestMapping("/")
	public String index() {
		log.info("Enter index()");
		
		return "/index";
	}
	
	@RequestMapping("/message")
	public String message() {
		return "/common/message";
	}
	
	@RequestMapping("/test")
	public String testPage(Model model) {
		model.addAttribute("testpage", "test123");
		
		return "/test";
	}
	
	@ModelAttribute("map")
	public Map<String, String> testModel() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("testmodel", "test456");
		return map;
	}
	
	@RequestMapping("/upload_test")
	public String uploadPage() {
		return "/upload_test";
	}
	
	@RequestMapping("/upload")
	public String upload(HttpServletRequest request) {
		System.out.println("Enter upload()");
		
//		fileMap.forEach((key, value) -> {
//			System.out.println("key: " + key);
//			System.out.println("Name: " + value.getName());
//			System.out.println("ContentType: " + value.getContentType());
//			System.out.println("OriginalFilename: " + value.getOriginalFilename());
//			System.out.println("-----------------------------");
//		});
		FileUploadUtil util = new FileUploadUtil();
		List<Map<String, Object>> list = util.fileUpload(request, "testboard");
		
		return "";
	}
}
