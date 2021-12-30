package com.gr.comcome.messagebox.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MessageBoxVO {
	private int messageno;
	private int accountno ;
	private String title;
	private String content;
	private Timestamp senddate;
	private String delflag;
}
