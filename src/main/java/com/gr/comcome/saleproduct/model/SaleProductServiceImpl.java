package com.gr.comcome.saleproduct.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gr.comcome.common.SearchVO;


@Service
public class SaleProductServiceImpl implements SaleProductService {

	@Autowired
	private SaleProductDAO saleProductDAO;
	
	@Override
	public int insertProduct(SaleProductVO saleProductVO) {
		return saleProductDAO.insertProduct(saleProductVO);
	}
	
	@Override
	public List<SaleProductVO> selectAllProduct(SearchVO searchVO) {
		return saleProductDAO.selectAllProduct(searchVO);
	}
	
	@Override
	public int selectTotalRecord(SearchVO searchVO) {
		return saleProductDAO.selectTotalRecord(searchVO);
	}
	
	@Override
	public SaleProductVO selectByNo(int saleProductNo) {
		return saleProductDAO.selectByNo(saleProductNo);
	}
	
	@Override
	public int updateProduct(SaleProductVO saleProductVO) {
		return saleProductDAO.updateProduct(saleProductVO);
	}
	
	@Override
	public int deleteProductByNo(int saleProductNo) {
		return saleProductDAO.deleteProductByNo(saleProductNo);
	}
}
