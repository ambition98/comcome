package com.gr.comcome.search_pd.reply.model;

import java.sql.Timestamp;

import com.gr.comcome.admin.model.AdminVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchPdReviewReplyVO {
	private int searchPdReviewReplyNo;
	private int searchPdReviewNo;
	private int adminNo;
	private String content;
	private Timestamp regdate;
	private AdminVO adminVo;
}
