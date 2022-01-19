package com.gr.comcome.comment.model;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class commentServiceImpl implements commentService {
	private final commentDAO commentDao;
	
	public commentServiceImpl(commentDAO commentDao) {
		this.commentDao=commentDao;
	}
	
	@Override
	public List<commentVO> selectByNo(int boardNo){
		return commentDao.selectByNo(boardNo);
	}
	
	@Override
	public int updateComment(commentVO vo){
		return commentDao.updateComment(vo);
	}
	
	@Override
	public int insertComment(commentVO vo) {
		return commentDao.insertComment(vo);
	}
	
	@Override
	public int deleteComment(commentVO vo) {
		return commentDao.deleteComment(vo);
	}

	@Override
	public int selectCntByNo(int no) {
		return commentDao.selectCntByNo(no);
	}
}
