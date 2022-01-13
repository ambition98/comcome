package com.gr.comcome.search_pd.review.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SearchPdReviewDAO {
	public List<SearchPdReviewVO> selectAll();
	public List<SearchPdReviewVO> selectByType();
	public List<SearchPdReviewVO> selectByAccountId();
}
