package com.gr.comcome.account.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

//sql과의 연동을 위한 파일
@Mapper
public interface AccountDAO {
	public int insertAccount(AccountVO vo);
	public int insertPwd(HashVO vo);
//	public List<PersonVO> selectAll(SearchVO searchVo);
//	int selectTotalRecord(SearchVO searchVo);
//	public PersonVO selectByNo(int no);
//	public int updateReadCount(int no);
//	public String selectPwd(int no);	
//	public int updateBoard(PersonVO vo);
//	public int deleteBoard(int no);
//	List<PersonVO> selectMainNotice();
}
