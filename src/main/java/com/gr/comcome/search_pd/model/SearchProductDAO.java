package com.gr.comcome.search_pd.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SearchProductDAO {
	List<SearchProductVO> selectAll();
	List<SearchProductVO> selectByCategoryNo(int categoryNo);
	List<SearchProductVO> selectBySearchKeywordVo(SearchKeywordVO vo);
}
