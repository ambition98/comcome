package com.gr.comcome.usedBoard.model;

import java.util.List;
import java.util.Map;

import com.gr.comcome.common.SearchVO;

public interface usedBoardService {
	public List<usedBoardVO> selectAll(SearchVO searchVo);
	int selectTotalRecord(SearchVO searchVo);
	public usedBoardVO selectByNo(int boardNo);
	public List<usedBoardVO> selectByGroupNo(String no);
	public List<usedBoardVO> selectByGroupNo2(Map<String, Object> map);
	public int updateReadCount(int no);
	int selectTotalRecord(int result);
	public int deleteBoardByNo(int boardNo);
	public int updateBoardByAdmin(usedBoardVO usedBoardVO);
	public int insertBoard(usedBoardVO usedBoardVO);
	public int updateBoard(usedBoardVO usedBoardVO);
}
