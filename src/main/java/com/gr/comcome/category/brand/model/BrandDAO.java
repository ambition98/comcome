package com.gr.comcome.category.brand.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BrandDAO {
	List<BrandVO> selectAllBrand();
}
