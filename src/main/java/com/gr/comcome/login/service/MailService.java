package com.gr.comcome.login.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	
	public static final Logger logger = 
			LoggerFactory.getLogger(MailService.class);
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public int sendMail(String email, String name, String veriCode) {
		int cnt = 0;
		try {
		//수신 대상을 담을 ArrayList 생성
		ArrayList<String> list = new ArrayList<String>();
		
		//수신 대상 추가 
		list.add(email);
		
		//수신 대상 개수
		int listSize = list.size();
	
		//SimpleMailMessage(단순 텍스트 구성 메일 메시지 생성할 때 이용)
		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		
		//수신자 설정 
		simpleMessage.setTo((String[]) list.toArray(new String[listSize]));
	
		//메일 제목
		simpleMessage.setSubject("comcome 비밀번호 재설정");
		
		//메일 내용 
		simpleMessage.setText(name+"님의 인증번호는 "+veriCode+" 입니다. "
				+ "인증번호 칸에 입력해주세요");
		
		//메일 발송
		javaMailSender.send(simpleMessage);

		logger.info("메일 발송 처리");

		cnt = 1;
		
		}catch(Exception e) {
			e.printStackTrace();
		cnt=0;
		}
		return cnt;
	}

	
	
}
