package com.gr.comcome.account.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HashVO {
	private int accountNo;
	private String salt;
	private String digest;
}
