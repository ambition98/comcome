package com.gr.comcome.pd_order.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PdOrderDAO {
	int insertNewOrder(PdOrderVO vo);
	List<PdOrderVO> selectByAccountNo(int accountNo);
}
