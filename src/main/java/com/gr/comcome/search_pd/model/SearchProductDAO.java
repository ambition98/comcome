package com.gr.comcome.search_pd.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gr.comcome.category.model.CategoryVO;

@Mapper
public interface SearchProductDAO {
	List<SearchProductVO> selectAll();
	List<SearchProductVO> selectByCategoryNo(CategoryVO vo);
	List<SearchProductVO> selectByBrandNo(int brandNo);
	List<SearchProductVO> selectByKeyword(String keyword);
	SearchProductVO selectByNo(int no);
	List<SearchProductVO> selectByOption(SearchOption option);
	List<SearchProductVO> selectTest(Map<String, Object> map);
}
