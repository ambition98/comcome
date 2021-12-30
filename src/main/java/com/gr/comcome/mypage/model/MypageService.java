package com.gr.comcome.mypage.model;

import java.util.List;

public interface MypageService {
	
	//로그인 처리시 필요한 상수
	public static final int LOGIN_OK=1;  //로그인 성공
	public static final int DISAGREE_PWD=2; //비번 불일치
	
	List<MypageVO> selectList(String name);
	public int selectByNo(int account_no);
	public MypageVO selectByName(String name);
}