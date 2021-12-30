package com.gr.comcome.search_pd.model;

import java.util.List;

public interface SearchProductService {
	List<SearchProductVO> selectAll();
	List<SearchProductVO> selectByCategoryNo(int categoryNo);
	List<SearchProductVO> selectBySearchKeywordVo(SearchKeywordVO vo);
}
