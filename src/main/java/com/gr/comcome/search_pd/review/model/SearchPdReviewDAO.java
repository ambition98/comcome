package com.gr.comcome.search_pd.review.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SearchPdReviewDAO {
	List<SearchPdReviewVO> selectByPdNo(int searchProductNo);
	List<SearchPdReviewVO> selectByType(Map<String, Object> map);
	List<SearchPdReviewVO> selectByAccountId(int accountId);
	List<Map<String, Object>> selectReviewCount(int searchProductNo);
	int insertNewReview(SearchPdReviewVO vo);
}
