package com.gr.comcome.search_pd.pricelog.model;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SearchPdPriceLogDAO {
	int insertLowPrice(SearchPdPriceLogVO vo);
}
