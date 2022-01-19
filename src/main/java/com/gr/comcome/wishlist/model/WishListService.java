package com.gr.comcome.wishlist.model;

import java.util.List;
import java.util.Map;

public interface WishListService {
	List<Map<String, Object>> selectWishList(String wisilistNo);

	int insertWishByNo(String email, int saleProductNo);

	List<Map<String, Object>> selectAll(int accountNo);

	int deleteWishByNo(int accountNo, int saleProductNo);

	int updateQuantity(WishListVO wishListVo);

	List<Map<String, Object>> selectByWishlistNoArr(Map<String, Object> map);
}
