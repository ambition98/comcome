
  package com.gr.comcome.wishlist.model;
  
  import lombok.Getter; import lombok.Setter; import lombok.ToString;
  
  @Getter
  @Setter
  @ToString 
  public class WishListAndProductVO {
	  private int wishlistNo; 
	  private int accountNo;
	  private int quantity;
	//특가 상품
		private int saleProductNo;
		private int categoryNo;
		private String name;
		private int price;
		
		
		private String thumbNailImg;
		private String contentImg;
		private String content;
	  
	  }
 