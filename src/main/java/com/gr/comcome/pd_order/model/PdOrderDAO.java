package com.gr.comcome.pd_order.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gr.comcome.common.SearchVO;
import com.gr.comcome.usedBoard.model.usedBoardVO;

@Mapper
public interface PdOrderDAO {
	int insertNewOrder(PdOrderVO vo);
	List<PdOrderVO> selectByAccountNo(int accountNo);
	List<Map<String, Object>> selectOrder(int accountNo);
	List<Map<String,Integer>> selectDaysSales();
	List<Map<String,Integer>> selectDaysSalesCount();
	List<PdOrderVO> selectAllOrder(SearchVO searchVo);
	List<PdOrderVO> selectAllData();
}
