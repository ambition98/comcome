package com.gr.comcome.category.screensize.model;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ScreenSizeServiceImpl implements ScreenSizeService{
	private final ScreenSizeDAO screenSizeDao;
	
	public ScreenSizeServiceImpl(ScreenSizeDAO screenSizeDao) {
		this.screenSizeDao = screenSizeDao;
	}

	@Override
	public List<ScreenSizeVO> selectAllScreenSize() {
		return screenSizeDao.selectAllScreenSize();
	}
}
