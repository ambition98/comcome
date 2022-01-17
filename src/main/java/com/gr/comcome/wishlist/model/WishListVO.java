
package com.gr.comcome.wishlist.model;
  
import lombok.Getter; import lombok.Setter; import lombok.ToString;
  
@Getter
@Setter
@ToString 
public class WishListVO {
	private int wishlistNo; 
	private int accountNo;
	private int saleProductNo; 
	private int quantity;
}
 