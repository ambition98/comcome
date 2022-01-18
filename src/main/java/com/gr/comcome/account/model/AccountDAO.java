package com.gr.comcome.account.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

//sql과의 연동을 위한 파일
@Mapper
public interface AccountDAO {
	int insertAccount(AccountVO vo);
	int insertPwd(HashVO vo);
	int checkEmail(String email);
	AccountVO selectAccountByNo(int accountNo);
	List<Map<String,Integer>> selectDaysRegister();
	AccountVO selectAccountByEmail(String account_id);
//	public List<PersonVO> selectAll(SearchVO searchVo);
//	int selectTotalRecord(SearchVO searchVo);
//	public PersonVO selectByNo(int no);
//	public int updateReadCount(int no);
//	public String selectPwd(int no);	
//	public int updateBoard(PersonVO vo);
//	public int deleteBoard(int no);
//	List<PersonVO> selectMainNotice();
}
