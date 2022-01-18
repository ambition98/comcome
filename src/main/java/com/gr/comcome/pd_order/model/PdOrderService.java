package com.gr.comcome.pd_order.model;

import java.util.List;
import java.util.Map;

import com.gr.comcome.common.SearchVO;

public interface PdOrderService {
	boolean insertNewOrder(PdOrderVO vo);
	List<PdOrderVO> selectByAccountNo(int accountNo);
	List<Map<String,Integer>> selectDaysSales();
	List<Map<String,Integer>> selectDaysSalesCount();
	List<PdOrderVO> selectAllOrder(SearchVO searchVo);
	List<PdOrderVO> selectAllData();
}
