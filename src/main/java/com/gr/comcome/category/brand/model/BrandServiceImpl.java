package com.gr.comcome.category.brand.model;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService{
	private final BrandDAO brandDao;
	
	public BrandServiceImpl(BrandDAO brandDao) {
		this.brandDao = brandDao;
	}

	@Override
	public List<BrandVO> selectAllBrand() {
		return brandDao.selectAllBrand();
	}
}
