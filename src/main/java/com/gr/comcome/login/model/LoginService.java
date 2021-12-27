package com.gr.comcome.login.model;

public interface LoginService {

	//로그인 시 필요한 상수 
	public static final int LOGIN_OK=1;  //로그인 성공
	public static final int DISAGREE_PWD=2; //비번 불일치
	int EMAIL_NONE=3; //아이디 존재하지 않음
	
	public int loginCheck(String email, String password);
	public AccountVO selectByEmail(String email);
}
