package com.gr.comcome.admin.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gr.comcome.common.SearchVO;
import com.gr.comcome.login.model.AccountVO;

@Mapper
public interface AdminDAO {

	int selectTotalRecord(SearchVO searchVo);
	public List<AccountVO> selectAllMember(SearchVO searchVo);
	AccountVO selectByAccountNo(int account_no);
	
}
