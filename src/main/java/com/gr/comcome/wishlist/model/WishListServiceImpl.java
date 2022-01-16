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
		
		// saleProductNo통해서 상품이 이미 담겨있나 확인하기 
		Integer checkProductExisting = wishlistDao.countProductByNo(saleProductNo);
		
		int result = 0;
		WishListVO wishListVO = new WishListVO();
		wishListVO.setAccountNo(accountNo);
	    wishListVO.setSaleProductNo(saleProductNo);
	    
		log.info("wishListVO={}",wishListVO.toString());
		
		if (checkProductExisting.equals(0)) {
			log.info("장바구니 상품이 존재하지 않음");
			wishListVO.setQuantity(1); //수량은 하나로 하기
			// 상품이 존재하지 않으면
			//accountNo를 where로 걸어서 wish리스트에 추가하기 
			result = wishlistDao.insertWishByNo(wishListVO);
			log.info("장바구니 상품이 존재하지 않음, result={}",result);
		} else if (checkProductExisting > 0) {
			log.info("장바구니 상품이 존재");
			
			result = wishlistDao.updateWishByNo(wishListVO);
			log.info("장바구니 상품이 존재, result={}",result);
		}
	
		return result;
	}
	
	@Override
	public List<Map<String, Object>> selectAll(int accountNo) {
		return wishlistDao.selectAll(accountNo);
	}
	
	@Override
	public int deleteWishByNo(int accountNo, int saleProductNo) {
		
		WishListVO wishListVo = new WishListVO();
		wishListVo.setAccountNo(accountNo);
		wishListVo.setSaleProductNo(saleProductNo);
		return wishlistDao.deleteWish(wishListVo);
	}
	
	
	@Override
	public int updateQuantity(WishListVO wishListVo) {
		log.info("장바구니 수량 수정 serviceimpl 처리 wishListVo={}",wishListVo.toString());
		return wishlistDao.updateQuantity(wishListVo);
	}
  }
 