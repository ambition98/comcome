package com.gr.comcome.search_pd.review.model;

import java.util.List;
import java.util.Map;

public interface SearchPdReviewService {
	List<SearchPdReviewVO> selectByPdNo(int searchProductNo);
	List<SearchPdReviewVO> selectByType(Map<String, Object> map);
	List<SearchPdReviewVO> selectByAccountId(int accountId);
	Map<String, Integer> selectReviewCount(int searchProductNo);
	boolean insertNewReview(SearchPdReviewVO vo);
}
