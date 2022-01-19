package com.gr.comcome.pd_order.model;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class PdOrderServiceImpl implements PdOrderService {
	private final PdOrderDAO pdOrderDao;
	
	public PdOrderServiceImpl(PdOrderDAO pdOrderDao) {
		this.pdOrderDao = pdOrderDao;
	}

	@Override
	public boolean insertNewOrder(PdOrderVO vo) {
		int cnt = pdOrderDao.insertNewOrder(vo);
		
		return (cnt > 0) ? true : false;
	}

	@Override
	public List<PdOrderVO> selectByAccountNo(int accountNo) {
		return pdOrderDao.selectByAccountNo(accountNo);
	}

//	@Override
//	public List<Map<String, Object>> selectOrder(String pdOrderNo) {
//		
//		return pdOrderDao.selectOrder(pdOrderNo);
//	}

	@Override
	public List<Map<String, Object>> selectOrder(int accountNo) {
		
		return pdOrderDao.selectOrder(accountNo);
	}


}
