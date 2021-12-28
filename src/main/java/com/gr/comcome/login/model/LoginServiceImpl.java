package com.gr.comcome.login.model;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gr.comcome.login.service.MailService;

@Service
public class LoginServiceImpl implements LoginService {

	private final LoginDAO loginDao;
	private final MailService mailService;

	@Autowired
	public LoginServiceImpl(LoginDAO loginDao, MailService mailService) {
		this.loginDao = loginDao;
		this.mailService = mailService;
	}

	// 로그인 체크
	public int loginCheck(String email, String password) {

		Integer ExistingEmail = loginDao.countEmail(email);
		int result = 0;

		if (ExistingEmail.equals(0)) {
			// 이메일이 존재하지 않으면
			result = EMAIL_NONE; // 이메일이 존재하지 않음
		} else if (ExistingEmail > 0) {
			// 이메일이 존재하면 accountNo 받아오기
			Integer accountNo = loginDao.selectAccountNo(email);
			// 수정 : 비밀번호 일치 하면!
			result = LOGIN_OK; // 이메일이 존재하고, 비밀번호가 일치하면 로그인 오케이

		}

		/*
		 * if(dbPwd == null || dbPwd.isEmpty()) { result=USERID_NONE; }else {
		 * if(dbPwd.equals(pwd)) { result=LOGIN_OK; }else { result=DISAGREE_PWD; } }
		 */
		return result;
	}

	@Override
	public AccountVO selectByEmail(String email) {
		return loginDao.selectByEmail(email);
	}

	// 이름을 where조건으로 걸고 가져온 반환한 전화번호와 매개변수의 전화번호가 같으면 !
	@Override
	public int FindEmailCheck(String name, String tel) {
		String dbTel = loginDao.selectTelByName(name);

		int result = 0;
		if (dbTel == null || dbTel == "") {
			// 회원 정보가 존재하지 않습니다.
			result = EMAIL_NONE;

			// db전번과 사용자가 입력한 전화번호가 같지 않으면,
		} else if (!dbTel.equals(tel)) {
			// 전화번호를 잘못 입력하였습니다.
			result = DISAGREE_TEL;
		} else if (dbTel.equals(tel)) {
			// 올바른 사용자 정보 입력 확인.
			result = LOGIN_OK;
		}
		return result;
	}

	@Override
	public String selectEmailByName(String name) {
		return loginDao.selectEmailByName(name);
	}

	@Override
	public int sendPassword(String email, String veriCode) {
		// 이메일 있나 없나 체크
		Integer ExistingEmail = loginDao.countEmail(email);
		// 랜덤으로 생성된 인증번호
		int result = 0;
		if (ExistingEmail.equals(0)) {
			// 이메일이 존재하지 않으면
			result = EMAIL_NONE; // 이메일이 존재하지 않음
		} else if (ExistingEmail > 0) {
			// 이메일이 존재하면 이메일로 VO 받아오기
			AccountVO vo = loginDao.selectByEmail(email);
			int cnt = mailService.sendMail(email, vo.getName(), veriCode);
			if (cnt > 0) {
				result = SEND_EMAIL; //이메일 보내기 성공
			}else {
				result = FAIL_TO_SEND_EMAIL;
			}
		}
		
		return result;
	}
	

	

}
