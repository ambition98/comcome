package com.gr.comcome.saleproduct.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gr.comcome.common.SearchVO;

@Mapper
public interface SaleProductDAO {

	int insertProduct(SaleProductVO saleProductVO);

	List<SaleProductVO> selectAllProduct(SearchVO searchVO);

	int selectTotalRecord(SearchVO searchVO);

	SaleProductVO selectByNo(int saleProductNo);

	int updateProduct(SaleProductVO saleProductVO);

	int deleteProductByNo(int saleProductNo);

}
