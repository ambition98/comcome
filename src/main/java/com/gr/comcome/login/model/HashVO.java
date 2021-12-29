package com.gr.comcome.login.model;

import lombok.Data;

@Data
public class HashVO {
	
	private int account_no;
	private String salt;
	private String digest;
	public int getAccount_no() {
		return account_no;
	}
	public void setAccount_no(int account_no) {
		this.account_no = account_no;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getDigest() {
		return digest;
	}
	public void setDigest(String digest) {
		this.digest = digest;
	}
	@Override
	public String toString() {
		return "HashVO [account_no=" + account_no + ", salt=" + salt + ", digest=" + digest + "]";
	}
	
	

}
