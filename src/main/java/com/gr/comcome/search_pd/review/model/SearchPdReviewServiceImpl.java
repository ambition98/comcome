package com.gr.comcome.search_pd.review.model;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SearchPdReviewServiceImpl implements SearchPdReviewService{
	private final SearchPdReviewDAO searchPdReviewDao;
	
	public SearchPdReviewServiceImpl(SearchPdReviewDAO dao) {
		this.searchPdReviewDao = dao;
	}

	@Override
	public List<SearchPdReviewVO> selectAll() {
		return searchPdReviewDao.selectAll();
	}

	@Override
	public List<SearchPdReviewVO> selectByType() {
		return searchPdReviewDao.selectByType();
	}

	@Override
	public List<SearchPdReviewVO> selectByAccountId() {
		return searchPdReviewDao.selectByAccountId();
	}
}
