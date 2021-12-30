package com.gr.comcome.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

@Service
public class HashingUtil {
	private final int SALT_SIZE = 16;
	private final int KEY_STRETCHING_CNT = 10000;
	private final String HASH_ALGORITHM = "SHA-256";
			
	public String makeNewSalt() {
		SecureRandom rand = new SecureRandom();

		byte[] temp = new byte[SALT_SIZE];
		rand.nextBytes(temp);
		String salt = byteToString(temp);

		return salt;
	}
	
	/**
	 * @author YJ_Lee
	 * 
	 * @param pwd 암호화할 패스워드
	 * @param salt 솔트
	 * @return String 암호화된 다이제스트
	 * 
	 * makeNewSalt() 메서드를 이용하여 솔트 생성 후
	 * 패스워드와 같이 매개변수로 전달하면 암호화된 다이제스트를 리턴
	 */
	public String hashing(String pwd, String salt) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);
		long start = System.currentTimeMillis();
		
		for(int i=0; i<KEY_STRETCHING_CNT; i++) {
			String temp = pwd + salt;
			md.update(temp.getBytes());
			byte[] digest_ = md.digest();
			pwd = byteToString(digest_);
		}
		
		long end = System.currentTimeMillis();
		System.out.println("Key-stretching time: " + (end-start));
		return pwd;
	}

	private String byteToString(byte[] b) {
		StringBuilder sb = new StringBuilder();
		for (byte bb : b)
			sb.append(String.format("%02x", bb));

		return sb.toString();
	}
}
