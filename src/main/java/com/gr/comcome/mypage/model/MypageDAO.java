package com.gr.comcome.mypage.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface MypageDAO {
	List<MypageVO> selectList(String name);
	public MypageVO selectByNo(int account_no);
	public MypageVO selectByName(String name);
	public int updateProfile(MypageVO vo);
	List<MypageVO> selectMainNotice();
}
