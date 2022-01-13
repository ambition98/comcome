package com.gr.comcome.wishlist.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
  public class WishListServiceImpl implements WishListService {
	private final WishListDAO wishlistDao;
	
	@Autowired
	public WishListServiceImpl(WishListDAO wishlistDao) {
		this.wishlistDao =wishlistDao;
	}

	@Override
	public List<Map<String, Object>> selectWishList(String wisilistNo) {
		
		return wishlistDao.selectWishList(wisilistNo);
	}
  }
 