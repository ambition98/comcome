package com.gr.comcome.pd_order.model;

import java.util.List;

public interface PdOrderService {
	boolean insertNewOrder(PdOrderVO vo);
	List<PdOrderVO> selectByAccountNo(int accountNo);
}
