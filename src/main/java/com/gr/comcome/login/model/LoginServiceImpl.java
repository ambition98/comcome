package com.gr.comcome.login.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

	private final LoginDAO loginDao;
	
	@Autowired
	public LoginServiceImpl(LoginDAO loginDao) {
		this.loginDao = loginDao;
	}
	
	//로그인 체크 
	public int loginCheck(String email, String password){
		
		Integer ExistingEmail = loginDao.countEmail(email);
		int result=0;
		
		if(ExistingEmail< 0) {
		//이메일이 존재하지 않으면 
			result = EMAIL_NONE; //이메일이 존재하지 않음
		}else if(ExistingEmail > 0) {
		// 이메일이 존재하면 accountNo 받아오기
			Integer accountNo = loginDao.selectAccountNo(email);
			//수정 : 비밀번호 일치 하면! 
			result =LOGIN_OK; //이메일이 존재하고, 비밀번호가 일치하면 로그인 오케이 
			
		}
		
	/*	
		if(dbPwd == null || dbPwd.isEmpty()) {
			result=USERID_NONE;
		}else {
			if(dbPwd.equals(pwd)) {
				result=LOGIN_OK;
			}else {
				result=DISAGREE_PWD;
			}
		}
		*/
		return result;
	}
	
	@Override
	public AccountVO selectByEmail(String email) {
		return loginDao.selectByEmail(email);
	}
}
