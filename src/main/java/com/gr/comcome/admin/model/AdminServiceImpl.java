package com.gr.comcome.admin.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gr.comcome.account.model.AccountVO;
import com.gr.comcome.common.SearchVO;


@Service
public class AdminServiceImpl implements AdminService{
	
	private Logger logger 
	= LoggerFactory.getLogger(AdminServiceImpl.class);
	
	
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
	
	
	//공지창
	@Override
	public int insertNotice(String email, String title, String content) {

		int adminNo = adminDAO.selectAdminNoByEmail(email);
		
		logger.info("관리자 번호, adminNo={}",adminNo);
		NoticeVO vo = new NoticeVO();
		
		vo.setAdminNo(adminNo);
		vo.setTitle(title);
		vo.setContent(content);
		logger.info("공지테이블 ,vo={}}",vo.toString());
		int result = adminDAO.insertNotice(vo);
		if(result >0) {
			result = INSERT_OK;
		}else {
			result = INSERT_DISAGREE;
		}
		return result;
		
		
	}
	
	@Override
	public NoticeVO selectRecentNotice() {
		return adminDAO.selectRecentNotice();
	}

	@Override
	public AdminVO selectByNo(int adminNo) {
		return adminDAO.selectByNo(adminNo);
	}
	
}
