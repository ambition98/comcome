package com.gr.comcome.pd_order.model;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.gr.comcome.common.SearchVO;

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
	
	@Override
	public List<Map<String,Integer>> selectDaysSales(){
		return pdOrderDao.selectDaysSales();
	}
	
	@Override
	public List<Map<String,Integer>> selectDaysSalesCount(){
		return pdOrderDao.selectDaysSalesCount();
	}
	
	@Override
	public List<PdOrderVO> selectAllOrder(SearchVO searchVo){
		return pdOrderDao.selectAllOrder(searchVo);
	}
	
	@Override
	public List<PdOrderVO> selectAllData(){
		return pdOrderDao.selectAllData();
	}
}
