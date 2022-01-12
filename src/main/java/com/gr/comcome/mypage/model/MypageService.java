package com.gr.comcome.mypage.model;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.gr.comcome.account.model.AccountVO;
import com.gr.comcome.mypage.model.MypageVO;

public interface MypageService {
	
	//로그인 처리시 필요한 상수
	public static final int LOGIN_OK=1;  //로그인 성공
	public static final int DISAGREE_PWD=2; //비번 불일치
	public static final int PWD_NONE=3; //카카오톡 비밀번호 없을때 
	public static final int PWD_OK=4; //(중첩if)비밀번호 일치
	
	List<MypageVO> selectList(String name);
	public MypageVO selectByNo(int accountNo);
	public MypageVO selectByName(String name);
	public int updateProfile(MypageVO vo);
	List<MypageVO> selectMainNotice();
	int CheckPwd(int accountNo, String password) throws NoSuchAlgorithmException;
	int DeleteAccount(int accountNo);
	
	AccountVO selectByaccountNo(int accountNo);
	int UpdateAccount(AccountVO vo);
//	boolean CheckPwd(AccountVO vo);
//	int UpdateAccount(int accountNo);
	int UpdateAccount(int accountNo);
}
