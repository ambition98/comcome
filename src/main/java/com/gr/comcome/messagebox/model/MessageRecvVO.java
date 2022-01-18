package com.gr.comcome.messagebox.model;

import lombok.Setter;
import lombok.ToString;
import lombok.Getter;

@Getter
@Setter
@ToString
public class MessageRecvVO {
	private int recvNo;
	private int accountNo;
	private int messageNo;
	private String readFlag;
	private String delFlag;
}
