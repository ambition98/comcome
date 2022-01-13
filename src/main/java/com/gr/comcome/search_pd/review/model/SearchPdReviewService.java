package com.gr.comcome.search_pd.review.model;

import java.util.List;

public interface SearchPdReviewService {
	public List<SearchPdReviewVO> selectAll();
	public List<SearchPdReviewVO> selectByType();
	public List<SearchPdReviewVO> selectByAccountId();
}
