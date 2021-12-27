package com.gr.comcome.login.model;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginDAO {
	
	
	public Integer countEmail(String email);
	//이메일이 존재하면 계정 번호 가져온다, 사용자 이메일 확인
	public Integer selectAccountNo(String email); 
	//이메일이 존재하면 hash 테이블에서 salt와 digest 가져옴
	public HashVO selectHash(int accountNo); 
	//hashing 결과인 digest와 hash 테이블에서 가져온 digest가 같은지 비교 
	public int countDigest(String Digest); //
	
	public AccountVO selectByEmail(String email);
	

}
