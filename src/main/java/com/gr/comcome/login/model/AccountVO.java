package com.gr.comcome.login.model;

import java.sql.Time;
import java.sql.Timestamp;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class AccountVO {
    
	private int accountNo;
	private String email; 
	private String name; 
	private String address; 
	private String tel; 
	private String isVerified;  
	private String cardNo; 
	private Timestamp regdate;
	
	
	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getIsVerified() {
		return isVerified;
	}
	public void setIsVerified(String isVerified) {
		this.isVerified = isVerified;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		return "AccountVO [accountNo=" + accountNo + ", email=" + email + ", name=" + name + ", address=" + address
				+ ", tel=" + tel + ", isVerified=" + isVerified + ", cardNo=" + cardNo + ", regdate=" + regdate + "]";
	}
	
	
	
	
}
