package com.gr.comcome.admin.model;

import java.util.List;

import com.gr.comcome.account.model.AccountVO;
import com.gr.comcome.common.SearchVO;

public interface AdminService {

	public List<AccountVO> selectAllMember(SearchVO searchVo);
	int selectTotalRecord(SearchVO searchVo);
	public AccountVO selectByAccountNo(int account_no);
}
