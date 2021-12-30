package com.gr.comcome.usedBoard.model;

import java.util.List;

import com.gr.comcome.common.SearchVO;

public interface usedBoardService {
	public List<usedBoardVO> selectAll(SearchVO searchVo);
	int selectTotalRecord(SearchVO searchVo);
	public List<usedBoardVO> selectByGroupNo(int no);
}
