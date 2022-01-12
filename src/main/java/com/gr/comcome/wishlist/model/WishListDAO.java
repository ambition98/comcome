package com.gr.comcome.wishlist.model;
  
 import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;  
  
 @Mapper 
public interface WishListDAO {
  
	 List<Map<String, Object>> selectWishList(String wisilistNo ); 
  
}
 