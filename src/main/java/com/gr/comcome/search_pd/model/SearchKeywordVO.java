package com.gr.comcome.search_pd.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchKeywordVO {
	private String keyword;
	private String brand;
	private String screenSize;
	private String cpu;
	private String core;
	private String memory;
	private String weight;
}
