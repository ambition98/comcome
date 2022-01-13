package com.gr.comcome.common.mallapi;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Product implements Comparable<Product>{
	private String mallName;
	private String link;
	private int price;
	
	public Product(String mallName, String link, int price) {
		this.mallName = mallName;
		this.link = link;
		this.price = price;
	}

	@Override
	public int compareTo(Product o) {
		if(this.price > o.price)
			return 1;
		else if(this.price == o.price)
			return 0;
		else
			return -1;
	}

}
