package com.gr.comcome.category.model;

import java.util.List;

public interface CategoryService {
	List<CategoryVO> selectAllCategory();
	CategoryVO selectCategoryNo(CategoryVO vo);
}
