
package com.gr.comcome.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gr.comcome.admin.model.AdminService;
import com.gr.comcome.admin.model.NoticeVO;
import com.gr.comcome.category.model.CategoryVO;
import com.gr.comcome.common.FileUploadUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IndexController {
	
	private AdminService adminService;
	
	
    
	 @Autowired
	public IndexController(AdminService adminService) {
		this.adminService = adminService;
	}

	// localhost:9091/comcome/
	@RequestMapping("/")
	public String index(Model model) {
		log.info("Enter index()");
		NoticeVO vo = adminService.selectRecentNotice();
		String content = vo.getContent();
		String contentbr = content.replaceAll("\n", "<br />");
		String title = vo.getTitle();
		String titlebr = title.replaceAll("\n", "<br />");
		model.addAttribute("content", contentbr);
		model.addAttribute("title", titlebr);
		model.addAttribute("vo", vo);
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
}

