package com.gr.comcome.account.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccountVO {
	private int accountNo;
	private String email;
	private String name;
	private String address;
	private String tel;
	private String isVerified;
	private String cardNo;
	private Timestamp regdate;
	private String zipcode;
	private String addressDetail;
}