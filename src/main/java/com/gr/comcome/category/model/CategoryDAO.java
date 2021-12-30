package com.gr.comcome.category.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryDAO {
	List<CategoryVO> selectAllCategory();
	int selectCategoryNo(CategoryVO vo); 
}
