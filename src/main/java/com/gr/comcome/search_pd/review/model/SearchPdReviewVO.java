package com.gr.comcome.search_pd.review.model;

import java.sql.Timestamp;

import com.gr.comcome.account.model.AccountVO;
import com.gr.comcome.search_pd.reply.model.SearchPdReviewReplyVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchPdReviewVO {
	private int searchPdReviewNo;
	private int searchProductNo;
	private int accountNo;
	private String content;
	private String type;
	private Timestamp regdate;
	private AccountVO accountVo;
	private SearchPdReviewReplyVO searchPdReviewReplyVo;
}
