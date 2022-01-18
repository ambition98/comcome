package com.gr.comcome.usedBoard.model;

import java.util.List;
import java.util.Map;

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
	
	public List<usedBoardVO> selectByGroupNo(String no){
		return usedBoardDao.selectByGroupNo(no);
	}
	public List<usedBoardVO> selectByGroupNo2(Map<String, Object> map){
		return usedBoardDao.selectByGroupNo2(map);
	}
	
	
	public usedBoardVO selectByNo(int boardNo){
		return usedBoardDao.selectByNo(boardNo);
	}
	
	public int updateReadCount(int boardNo){
		return usedBoardDao.updateReadCount(boardNo);
	}
	
	@Override
	public int selectTotalRecord(int result) {
		return usedBoardDao.selectTotalRecord(result);
	}
	
	@Override
	public int deleteBoardByNo(int boardNo) {
		return usedBoardDao.deleteBoardByNo(boardNo);
		
	}
	
	@Override
	public int updateBoardByAdmin(usedBoardVO usedBoardVO) {
		return usedBoardDao.updateBoardByAdmin(usedBoardVO);
	}
	
	@Override
	public int insertBoard(usedBoardVO usedBoardVO){
		return usedBoardDao.insertBoard(usedBoardVO);
	};
	@Override
	public int updateBoard(usedBoardVO usedBoardVO) {
		return usedBoardDao.updateBoard(usedBoardVO);
	};
}
