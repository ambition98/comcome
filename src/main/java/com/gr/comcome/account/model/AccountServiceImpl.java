package com.gr.comcome.account.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	private AccountDAO accountDao;
	
	@Transactional
	public int insertAccount(AccountVO vo) {
		return accountDao.insertAccount(vo);
	}
	@Transactional
	public int insertPwd(HashVO vo) {
		return accountDao.insertPwd(vo);
	}
	
	public int checkEmail(String email) {
		return accountDao.checkEmail(email);
	}
	
	@Override
	public AccountVO selectAccountByNo(int accountNo) {
		return accountDao.selectAccountByNo(accountNo);
	}
//	
//	public List<PersonVO> selectAll(SearchVO searchVo){
//		return boardDao.selectAll(searchVo);
//	}
//
//	@Override
//	public int selectTotalRecord(SearchVO searachVo) {
//		return boardDao.selectTotalRecord(searachVo);
//	}
//	
//	public PersonVO selectByNo(int no){
//		return boardDao.selectByNo(no);
//	}
//	
//	public int updateReadCount(int no){
//		return boardDao.updateReadCount(no);
//	}
//
//	@Override
//	public int updateBoard(PersonVO vo) {
//		return boardDao.updateBoard(vo);
//	}
//
//	@Override
//	public boolean checkPwd(PersonVO vo) {
//		String dbPwd=boardDao.selectPwd(vo.getNo());
//		
//		if(dbPwd.equals(vo.getPwd())) {
//			return true;
//		}else {
//			return false;
//		}
//	}
//	
//	public int deleteBoard(int no){
//		return boardDao.deleteBoard(no);
//	}
//
//	@Override
//	public List<PersonVO> selectMainNotice() {
//		return boardDao.selectMainNotice();
//	}
}
