package com.gr.comcome.admin.model;

import java.util.List;

import com.gr.comcome.account.model.AccountVO;
import com.gr.comcome.common.SearchVO;

public interface AdminService {

	
	//로그인 시 필요한 상수 
		public static final int LOGIN_OK=1;  //로그인 성공
		public static final int DISAGREE_PWD=2; //비번 불일치
		int EMAIL_NONE=3; //아이디 존재하지 않음
		
		int INSERT_OK=4;
		int INSERT_DISAGREE=5;
		
		
	List<AccountVO> selectAllMember(SearchVO searchVo);
	int selectTotalRecord(SearchVO searchVo);
	AccountVO selectByAccountNo(int account_no);
	int loginCheck(String email, String password);
	AdminVO selectByEmail(String email);
	int insertNotice(String email, String title, String content);
	NoticeVO selectRecentNotice();
	AdminVO selectByNo(int adminNo); 

}
