package com.gr.comcome.usedBoard.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gr.comcome.common.SearchVO;



@Mapper
public interface usedBoardDAO {
	public List<usedBoardVO> selectAll(SearchVO searchVo);
	int selectTotalRecord(SearchVO searchVo);
	public usedBoardVO selectByNo(int boardNo);
	public List<usedBoardVO> selectByGroupNo(String no);
	public List<usedBoardVO> selectByGroupNo2(Map<String, Object> map);
	public int updateReadCount(int no);
	int selectTotalRecord(int result);
	int deleteBoardByNo(int boardNo);
	public int updateBoardByAdmin(usedBoardVO usedBoardVO);
	public int insertBoard(usedBoardVO usedBoardVO);
	public int updateBoard(usedBoardVO usedBoardVO);
	public List<Map<String, Integer>> selectDaysBoardCount();
}
