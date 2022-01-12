package com.gr.comcome.admin.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NoticeVO {

	
	private int adminNo;
	private int noticeNo;
	private String title;
	private String content;
	private Timestamp regdate;
}
