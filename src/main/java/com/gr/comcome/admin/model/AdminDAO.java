package com.gr.comcome.admin.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gr.comcome.account.model.AccountVO;
import com.gr.comcome.common.SearchVO;

@Mapper
public interface AdminDAO {

	int selectTotalRecord(SearchVO searchVo);
	public List<AccountVO> selectAllMember(SearchVO searchVo);
	AccountVO selectByAccountNo(int account_no);
	//로그인 체크 : 이메일이 존재하는지 안하는지 확인 
	public Integer countEmail(String email);
	
	//로그인 체크 : 비밀번호가 일치하는지 확인 
	String checkPwd(String email);
	
	//이메일 통해서 관리자 테이블 정보 가져오기
	AdminVO selectByEmail(String email);
	//이메일 통해서 adminNo가져오기
	int selectAdminNoByEmail(String email);
	//notice 내용을 notice에 넣기 
	int insertNotice(NoticeVO vo);
	
	//notice 최근 것 불러오기 
	NoticeVO selectRecentNotice();
	
		
}
