package com.gr.comcome.wishlist.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gr.comcome.login.model.LoginDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
  public class WishListServiceImpl implements WishListService {
	private final WishListDAO wishlistDao;
	
	@Autowired
	private LoginDAO loginDAO;
	
	@Autowired
	public WishListServiceImpl(WishListDAO wishlistDao) {
		this.wishlistDao =wishlistDao;
	}

	@Override
	public List<Map<String, Object>> selectWishList(String wisilistNo) {
		
		return wishlistDao.selectWishList(wisilistNo);
	}
	
	@Override
	public int insertWishByNo(String email, int saleProductNo) {
		
		//email 통해서 accountNo가져오기 
		int accountNo = loginDAO.selectAccountNo(email);
		
		WishListVO wishListVO = new WishListVO();
		wishListVO.setAccountNo(accountNo);
	    wishListVO.setSaleProductNo(saleProductNo);
	    
		log.info("wishListVO={}",wishListVO.toString());
	    
		//accountNo를 where로 걸어서 wish리스트에 추가하기 
		int result = wishlistDao.insertWishByNo(wishListVO);
		
	
		return result;
	}
  }
 