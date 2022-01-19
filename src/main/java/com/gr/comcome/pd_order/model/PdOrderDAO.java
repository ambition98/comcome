package com.gr.comcome.pd_order.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PdOrderDAO {
	int insertNewOrder(PdOrderVO vo);
	List<PdOrderVO> selectByAccountNo(int accountNo);
//	List<Map<String, Object>> selectOrder(String pdOrderNo);
	List<Map<String, Object>> selectOrder(int accountNo);

}
