package com.gr.comcome.usedBoard.model;

import java.util.List;

import com.gr.comcome.common.SearchVO;

public interface usedBoardService {
	public List<usedBoardVO> selectAll(SearchVO searchVo);
	int selectTotalRecord(SearchVO searchVo);
	public usedBoardVO selectByNo(int boardNo);
	public List<usedBoardVO> selectByGroupNo(String no);
	public int updateReadCount(int no);
	int selectTotalRecord(int result);
	public int deleteBoardByNo(int boardNo);
	public int updateBoardByAdmin(usedBoardVO usedBoardVO);
}
