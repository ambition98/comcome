package com.gr.comcome.search_pd.reply.model;

import org.springframework.stereotype.Service;

@Service
public class SearchPdReviewReplyServiceImpl implements SearchPdReviewReplyService{
	private final SearchPdReviewReplyDAO searchPdReviewReplyDao;

	public SearchPdReviewReplyServiceImpl(SearchPdReviewReplyDAO searchPdReviewReplyDao) {
		this.searchPdReviewReplyDao = searchPdReviewReplyDao;
	}

	@Override
	public SearchPdReviewReplyVO selectByReviewNo(int searchPdReviewNo) {
		return searchPdReviewReplyDao.selectByReviewNo(searchPdReviewNo);
	}
}
