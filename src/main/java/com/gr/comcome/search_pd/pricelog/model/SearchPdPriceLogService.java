package com.gr.comcome.search_pd.pricelog.model;

public interface SearchPdPriceLogService {
	boolean insertLowPrice(SearchPdPriceLogVO vo);
	int selectNewestPriceByPdNo(int searchPdNo);
}
