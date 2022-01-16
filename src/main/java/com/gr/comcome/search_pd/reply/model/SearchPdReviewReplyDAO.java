package com.gr.comcome.search_pd.reply.model;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SearchPdReviewReplyDAO {
	SearchPdReviewReplyVO selectByReviewNo(int searchPdReviewNo);
}
