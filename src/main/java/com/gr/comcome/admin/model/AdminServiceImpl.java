package com.gr.comcome.admin.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gr.comcome.common.SearchVO;
import com.gr.comcome.login.model.AccountVO;

@Service
public class AdminServiceImpl implements AdminService{
	
	private final AdminDAO adminDAO;
	
	@Autowired
	public AdminServiceImpl(AdminDAO adminDAO) {
		this.adminDAO = adminDAO;
	}
	
	@Override
	public List<AccountVO> selectAllMember(SearchVO searchVo){
		return adminDAO.selectAllMember(searchVo);
	}

	@Override
	public int selectTotalRecord(SearchVO searachVo) {
		return adminDAO.selectTotalRecord(searachVo);
	}

	@Override
	public AccountVO selectByAccountNo(int account_no) {
		return adminDAO.selectByAccountNo(account_no);
	}
	
}
