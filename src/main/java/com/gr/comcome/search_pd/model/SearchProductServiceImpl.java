package com.gr.comcome.search_pd.model;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SearchProductServiceImpl implements SearchProductService{
	private final SearchProductDAO searchProductDao;

	public SearchProductServiceImpl(SearchProductDAO searchProductDao) {
		this.searchProductDao = searchProductDao;
	}

	@Override
	public List<SearchProductVO> selectAll() {
		return searchProductDao.selectAll();
	}

	@Override
	public List<SearchProductVO> selectByCategoryNo(int categoryNo) {
		return searchProductDao.selectByCategoryNo(categoryNo);
	}

	@Override
	public List<SearchProductVO> selectBySearchKeywordVo(SearchKeywordVO vo) {
		return searchProductDao.selectBySearchKeywordVo(vo);
	}
	
}
