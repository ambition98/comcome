package com.gr.comcome.pd_order.model;

import java.util.List;
import java.util.Map;

public interface PdOrderService {
	boolean insertNewOrder(PdOrderVO vo);
	List<PdOrderVO> selectByAccountNo(int accountNo);
//	List<Map<String, Object>> selectOrder(String pdOrderNo);
	List<Map<String, Object>> selectOrder(int accountNo);

}
