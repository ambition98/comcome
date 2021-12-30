package com.gr.comcome.search_pd.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchProductVO {
	private int searchProductNo;
	private int categoryNo;
	private String name;
	private String code;
	private String detail;
	private String thumbnail;
}
