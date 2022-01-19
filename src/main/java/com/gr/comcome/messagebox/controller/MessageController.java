package com.gr.comcome.messagebox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gr.comcome.account.model.AccountService;
import com.gr.comcome.account.model.AccountVO;
import com.gr.comcome.messagebox.model.MessageBoxService;
import com.gr.comcome.messagebox.model.MessageRecvService;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/message")
public class MessageController {

	private final MessageBoxService messageBoxService;
	private final MessageRecvService messageRecvService;
	private final AccountService accountService;
	
	@Autowired
	public MessageController(MessageBoxService boxService,MessageRecvService messageRecvService,AccountService accountService) {
		this.messageBoxService=boxService;
		this.messageRecvService=messageRecvService;
		this.accountService=accountService;
		log.info("생성자주입");
	}
	
	@RequestMapping(value = "/write" , method=RequestMethod.GET)
	public String message(String account_id,Model model){
		log.info("[/profile] account_id args : {"+account_id+"}");
		
		AccountVO vo= accountService.selectAccountByEmail(account_id);
		
		
		model.addAttribute("vo",vo);
		return null;
		
	}
}
