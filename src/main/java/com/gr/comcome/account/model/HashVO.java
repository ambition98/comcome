package com.gr.comcome.account.model;

public class HashVO {
	private int accountNo;
	private String salt;
	private String digest;
	public int getAccountNo() {
		return accountNo;
	}
	
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
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
		return "HashVO [accountNo=" + accountNo + ", salt=" + salt + ", digest=" + digest + "]";
	}
	
	
}
