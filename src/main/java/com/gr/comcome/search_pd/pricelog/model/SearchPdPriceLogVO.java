package com.gr.comcome.search_pd.pricelog.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchPdPriceLogVO {
	private int searchPdPriceLogNo;
	private int searchProductNo;
	private int lowPrice;
	private Timestamp regdate;
}
