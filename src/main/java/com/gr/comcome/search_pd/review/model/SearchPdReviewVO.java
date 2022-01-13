package com.gr.comcome.search_pd.review.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchPdReviewVO {
	private int searchPdNo;
	private int accountNo;
	private String content;
	private String type;
}
