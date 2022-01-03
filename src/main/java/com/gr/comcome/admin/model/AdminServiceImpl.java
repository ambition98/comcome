package com.gr.comcome.admin.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gr.comcome.account.model.AccountVO;
import com.gr.comcome.common.SearchVO;

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
	
	@Override
	public int loginCheck(String email, String password) {
		//이메일이 존재하는지 안하는지 확인 
		Integer ExistingEmail = adminDAO.countEmail(email);
		
		//이메일이 존재하지 않으면 
		int result = 0;
		if(ExistingEmail.equals(0)) {
			//이메일이 존재하지 않습니다
			result = EMAIL_NONE;
		//이메일이 존재하면
		}else if(ExistingEmail>0) {
			//비밀번호 확인 
			String dbPwd = adminDAO.checkPwd(email);
			//비밀번호 일치하면
			if(dbPwd.equals(password)) {
				result = LOGIN_OK;
			}else if(!dbPwd.equals(password)) {
				result = DISAGREE_PWD;
			}
		}
		return result;
	}
	
	@Override
	public AdminVO selectByEmail(String email) {
	
		return adminDAO.selectByEmail(email);
	}
	
}
