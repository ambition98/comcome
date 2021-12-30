package com.gr.comcome.account.model;

import java.util.List;


public interface AccountService {
	public int insertAccount(AccountVO vo);
	public int insertPwd(HashVO vo);
//	public List<PersonVO> selectAll(SearchVO searchVo);
//	int selectTotalRecord(SearchVO searchVo);
//	public PersonVO selectByNo(int no) ;
//	public int updateReadCount(int no);
//	public int updateBoard(PersonVO vo);
//	public boolean checkPwd(PersonVO vo);
//	public int deleteBoard(int no);
//	List<PersonVO> selectMainNotice();
}
