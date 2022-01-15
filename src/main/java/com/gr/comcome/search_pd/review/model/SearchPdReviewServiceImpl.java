package com.gr.comcome.search_pd.review.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class SearchPdReviewServiceImpl implements SearchPdReviewService{
	private final SearchPdReviewDAO searchPdReviewDao;
	
	public SearchPdReviewServiceImpl(SearchPdReviewDAO dao) {
		this.searchPdReviewDao = dao;
	}

	@Override
	public List<SearchPdReviewVO> selectByPdNo(int searchProductNo) {
		return searchPdReviewDao.selectByPdNo(searchProductNo);
	}

	@Override
	public List<SearchPdReviewVO> selectByType(Map<String, Object> map) {
		return searchPdReviewDao.selectByType(map);
	}

	@Override
	public List<SearchPdReviewVO> selectByAccountId(int accountId) {
		return searchPdReviewDao.selectByAccountId(accountId);
	}

	@Override
	public boolean insertNewReview(SearchPdReviewVO vo) {
		int cnt = searchPdReviewDao.insertNewReview(vo);
		
		return (cnt >= 1) ? true : false;
	}

	@Override
	public Map<String, Integer> selectReviewCount(int searchProductNo) {
		List<Map<String, Object>> listMap
					= searchPdReviewDao.selectReviewCount(searchProductNo);
		
		Map<String, Boolean> isExist = new HashMap<>();
		isExist.put("opinion", false);
		isExist.put("question", false);
		isExist.put("review", false);
		
		Map<String, Integer> countMap = new HashMap<>();
		
		int sum = 0;
		for(Map<String, Object> map : listMap) {
//			map.forEach((k, v) -> {
//				System.out.println("k: " + k);
//				System.out.println("v: " + v);
//			});
			
			String type = (String) map.get("TYPE");
			int count = Integer.parseInt(String.valueOf(map.get("COUNT")));
			sum += count;
			
			countMap.put(type, count);
			isExist.put(type, true);
		}			
		
		isExist.forEach((k, v) -> {
			if(!v) {
				countMap.put(k, 0);
			}
		});
		
		countMap.put("all", sum);
		
		return countMap;
	}
}
