package com.gr.comcome.comment.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class commentVO {
	private int no;
	private int boardNo;
	private String name;
	private String content;
	private Timestamp regdate;
	
}
