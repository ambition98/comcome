package com.gr.comcome.search_pd.model;

import java.util.List;
import java.util.Map;

import com.gr.comcome.category.model.CategoryVO;

public interface SearchProductService {
	List<SearchProductVO> selectAll();
	List<SearchProductVO> selectByCategoryNo(CategoryVO vo);
	List<SearchProductVO> selectByBrandNo(int brandNo);
	List<SearchProductVO> selectByKeyword(String keyword);
	SearchProductVO selectByNo(int no);
	List<SearchProductVO> selectByOption(Map<String, Object> map);
}
