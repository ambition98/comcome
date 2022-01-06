package com.gr.comcome.search_pd.model;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.gr.comcome.category.model.CategoryVO;

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
	public List<SearchProductVO> selectByCategoryNo(CategoryVO vo) {
		return searchProductDao.selectByCategoryNo(vo);
	}

	@Override
	public List<SearchProductVO> selectByBrandNo(int brandNo) {
		return searchProductDao.selectByBrandNo(brandNo);
	}

	@Override
	public List<SearchProductVO> selectByKeyword(String keyword) {
		return searchProductDao.selectByKeyword(keyword);
	}

	@Override
	public SearchProductVO selectByNo(int no) {
		return searchProductDao.selectByNo(no);
	}

	@Override
	public List<SearchProductVO> selectByOption(Map<String, Object> map) {
		return searchProductDao.selectByOption(map);
	}
}
