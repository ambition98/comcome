package com.gr.comcome.account.model;

import java.util.List;
import java.util.Map;

public interface AccountService {
	//아이디 중복확인시 필요한 상수
	public static final int EXIST_EMAIL=1;  //해당 아이디가 이미 존재함
	public static final int NON_EXIST_EMAIL=0;  //해당 아이디가 존재하지 않음-사용가능
		
	int insertAccount(AccountVO vo);
	int insertPwd(HashVO vo);
	int checkEmail(String email);
	AccountVO selectAccountByNo(int accountNo);
	List<Map<String, Integer>> selectDaysRegister();
//	public List<PersonVO> selectAll(SearchVO searchVo);
//	int selectTotalRecord(SearchVO searchVo);
//	public PersonVO selectByNo(int no) ;
//	public int updateReadCount(int no);
//	public int updateBoard(PersonVO vo);
//	public boolean checkPwd(PersonVO vo);
//	public int deleteBoard(int no);
//	List<PersonVO> selectMainNotice();
}
