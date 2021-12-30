package com.gr.comcome.category.model;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService{
	private final CategoryDAO categoryDao;

	public CategoryServiceImpl(CategoryDAO categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public List<CategoryVO> selectAllCategory() {
		return categoryDao.selectAllCategory();
	}

	@Override
	public CategoryVO selectCategoryNo(CategoryVO vo) {
		vo.setCategoryNo(categoryDao.selectCategoryNo(vo));
		
		return vo;
	}
	
	
}
