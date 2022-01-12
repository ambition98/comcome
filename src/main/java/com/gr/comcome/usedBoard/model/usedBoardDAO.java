package com.gr.comcome.usedBoard.model;

import java.util.List;



import org.apache.ibatis.annotations.Mapper;

import com.gr.comcome.common.SearchVO;



@Mapper
public interface usedBoardDAO {
	public List<usedBoardVO> selectAll(SearchVO searchVo);
	int selectTotalRecord(SearchVO searchVo);
	public usedBoardVO selectByNo(int boardNo);
	public List<usedBoardVO> selectByGroupNo(String no);
	public int updateReadCount(int no);
	int selectTotalRecord(int result);
}
