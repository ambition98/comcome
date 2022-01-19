package com.gr.comcome.search_pd.pricelog.model;

import org.springframework.stereotype.Service;

@Service
public class SearchPdPriceLogServiceImpl implements SearchPdPriceLogService {
	private final SearchPdPriceLogDAO searchPdPriceLogDao;
	
	public SearchPdPriceLogServiceImpl(SearchPdPriceLogDAO searchPdPriceLogDao) {
		this.searchPdPriceLogDao = searchPdPriceLogDao;
	}

	@Override
	public boolean insertLowPrice(SearchPdPriceLogVO vo) {
		int cnt = searchPdPriceLogDao.insertLowPrice(vo);
		
		return (cnt > 0) ? true : false;
	}

	@Override
	public int selectNewestPriceByPdNo(int searchPdNo) {
		return searchPdPriceLogDao.selectNewestPriceByPdNo(searchPdNo);
	}

}
