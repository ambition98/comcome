package com.gr.comcome.messageBoxRec.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MessageBoxRecVO {
	private int recvno;
	private int accountno;
	private int messageno;
	private String readflag;
	private String delflag;
}
