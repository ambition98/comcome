package com.gr.comcome.usedBoard.model;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;

import com.gr.comcome.common.SearchVO;


@Mapper
public interface usedBoardDAO {
	public List<usedBoardVO> selectAll(SearchVO searchVo);
	int selectTotalRecord(SearchVO searchVo);
	public List<usedBoardVO> selectByGroupNo(int no);
}