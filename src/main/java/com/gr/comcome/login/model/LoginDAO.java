package com.gr.comcome.login.model;

import org.apache.ibatis.annotations.Mapper;

import com.gr.comcome.account.model.AccountVO;
import com.gr.comcome.account.model.HashVO;

@Mapper//sql이랑 직접 소통하는 애 ! 

public interface LoginDAO {
	
	
	public Integer countEmail(String email);
	//이메일이 존재하면 계정 번호 가져온다, 사용자 이메일 확인
	public Integer selectAccountNo(String email); 
	//이메일이 존재하면 hash 테이블에서 salt와 digest 가져옴
	public HashVO selectHash(int accountNo); 
	//hashing 결과인 digest와 hash 테이블에서 가져온 digest가 같은지 비교 
	public int countDigest(String Digest); //
	
	public AccountVO selectByEmail(String email);
	
	//select tel from account where name = ?
	public String selectTelByName(String name);
	//이름을 매개변수로 받아서 이메일 알려주기 
	public String selectEmailByName(String name);
	//이메일로 VO 받아오기 
	public AccountVO selectNameByEmail(String email);
	//비밀번호 재설정
	public int updatePassword(HashVO hashvo);

	//카카오 로그인시에 자동 회원가입 
	public int insertAccountForKako(AccountVO accountVO);
	
	
}
