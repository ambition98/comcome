package com.gr.comcome.saleproduct.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class SaleProductVO {
	//특가 상품
	private int saleProductNo;
	private int categoryNo;
	private String name;
	private int price;
	
	
	private String thumbNailImg;
	private String contentImg;
	private String content;
	
	

}
