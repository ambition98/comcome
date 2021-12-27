package com.gr.comcome.login.model;

import lombok.Data;

@Data
public class HashVO {
	
	private int account_no;
	private String salt;
	private String digest;

}
