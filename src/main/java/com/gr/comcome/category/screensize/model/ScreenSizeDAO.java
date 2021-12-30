package com.gr.comcome.category.screensize.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ScreenSizeDAO {
	List<ScreenSizeVO> selectAllScreenSize();
}
