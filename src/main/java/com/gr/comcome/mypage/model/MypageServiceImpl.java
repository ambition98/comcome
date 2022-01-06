package com.gr.comcome.mypage.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MypageServiceImpl implements MypageService {
	private final MypageDAO mypageDao;
	
	
	@Autowired
	public MypageServiceImpl(MypageDAO mypageDao) {
		this.mypageDao=mypageDao;
	}
	
	@Override
	public List<MypageVO> selectList (String name) {
		return mypageDao.selectList(name);
		
	}
	
	@Override
	public MypageVO selectByNo(int mypageNo) {
		return mypageDao.selectByNo(mypageNo);
		
	
	}
	
	//프로필 닉네임
	public MypageVO selectByName(String name) {
		return mypageDao.selectByName(name);
		
	}

	@Override
	public int updateProfile(MypageVO vo) {
		return mypageDao.updateProfile(vo);
	}

	@Override
	public List<MypageVO> selectMainNotice() {

		return mypageDao.selectMainNotice();
	}
	
}
