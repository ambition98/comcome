package com.gr.comcome.mypage.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gr.comcome.account.model.AccountVO;


@Mapper
public interface MypageDAO {
	List<MypageVO> selectList(String name);
	public MypageVO selectByNo(int accountno);
	public MypageVO selectByName(String name);
	public int updateProfile(MypageVO vo);
	List<MypageVO> selectMainNotice();
	int deleteHash(int accountNo);
	int deleteAccountbyNo(int accountNo);
	
	AccountVO selectByaccountNo(int accountNo);
	int UpdateAccountbyNo(int accountNo);
	int UpdateHash(int accountNo);
	
	
}
