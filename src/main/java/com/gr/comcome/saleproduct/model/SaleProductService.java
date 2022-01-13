package com.gr.comcome.saleproduct.model;

import java.util.List;

import com.gr.comcome.common.SearchVO;

public interface SaleProductService {

	int insertProduct(SaleProductVO saleProductVO);

	List<SaleProductVO> selectAllProduct(SearchVO searchVO);

	int selectTotalRecord(SearchVO searchVO);

	SaleProductVO selectByNo(int saleProductNo);

	int updateProduct(SaleProductVO saleProductVO);

	int deleteProductByNo(int saleProductNo);

}
