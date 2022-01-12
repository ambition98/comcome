package com.gr.comcome.comment.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface commentDAO {
	
	public List<commentVO> selectByNo(int boardNo);
	public int updateComment(int no);
	public int insertComment(commentVO vo);
	public int deleteComment(int no);
}