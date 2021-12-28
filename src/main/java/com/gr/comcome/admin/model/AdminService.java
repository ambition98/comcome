package com.gr.comcome.admin.model;

import java.util.List;

import com.gr.comcome.common.SearchVO;
import com.gr.comcome.login.model.AccountVO;

public interface AdminService {

	public List<AccountVO> selectAllMember(SearchVO searchVo);
	int selectTotalRecord(SearchVO searchVo);
	
	
	public AccountVO selectByAccountNo(int account_no);

}
