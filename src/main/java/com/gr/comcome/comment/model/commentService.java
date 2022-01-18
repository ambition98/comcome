package com.gr.comcome.comment.model;

import java.util.List;

public interface commentService {

	public List<commentVO> selectByNo(int boardNo);
	public int updateComment(commentVO vo);
	public int insertComment(commentVO vo);
	public int deleteComment(commentVO vo);
}
