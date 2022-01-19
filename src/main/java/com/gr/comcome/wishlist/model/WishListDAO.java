package com.gr.comcome.wishlist.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WishListDAO {

	List<Map<String, Object>> selectWishList(String wisilistNo);

	int insertWishByNo(WishListVO wishListVO);

	List<Map<String, Object>> selectAll(int accountNo);

	Integer countProductByNo(WishListVO wishListVO);

	int updateWishByNo(WishListVO wishListVO);

	int deleteWish(WishListVO wishListVo);

	int updateQuantity(WishListVO wishListVo);
	
	List<Map<String, Object>> selectByWishlistNoArr(Map<String, Object> map);
}
