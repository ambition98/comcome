package com.gr.comcome.login.model;

import java.security.NoSuchAlgorithmException;

import com.gr.comcome.account.model.AccountVO;
import com.gr.comcome.account.model.HashVO;

public interface LoginService {

	//로그인 시 필요한 상수 
	public static final int LOGIN_OK=1;  //로그인 성공
	public static final int DISAGREE_PWD=2; //비번 불일치
	int EMAIL_NONE=3; //아이디 존재하지 않음
	public static final int DISAGREE_TEL=4;//전화번호 불일치 
	
	//비밀번호 찾기
	public static final int SEND_EMAIL=5;
	public static final int FAIL_TO_SEND_EMAIL=6;
	
	public int loginCheck(String email, String password) throws NoSuchAlgorithmException;
	public AccountVO selectByEmail(String email);

	public int FindEmailCheck(String name, String tel);	
	public String selectEmailByName(String name);
	public int sendPassword(String email, String veriCode);
	//비밀번호 재설정 
	public int updatePassword(HashVO hashvo);
	
	public int insertAccountForKako(AccountVO accountVO);
	//카카오 이메일이 회원 테이블에 있는지 없는지 확인 
	public Integer countEmail(String email);
	
 
	}

