package com.gr.comcome.usedBoard.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gr.comcome.common.SearchVO;

@Service
public class usedBoardServiceImpl implements usedBoardService{

	@Autowired
	private usedBoardDAO usedBoardDao;
	
	public List<usedBoardVO> selectAll(SearchVO searchVo){
		return usedBoardDao.selectAll(searchVo);
	}
	
	@Override
	public int selectTotalRecord(SearchVO searchVo) {
		return usedBoardDao.selectTotalRecord(searchVo);
	}
	
	public List<usedBoardVO> selectByGroupNo(int no){
		return usedBoardDao.selectByGroupNo(no);
	};
}
