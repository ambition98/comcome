package com.gr.comcome.usedBoard.model;

import java.sql.Timestamp;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class usedBoardVO {
	private int boardNo;
	private int accountNo;
	private String groupNo;
	private long fileSize;
	private String delFlag;
	private String title;
	private int readcount;
	private int sortNo;
	private String content;
	private String fileName;
	private int downCount;
	private int step;
	private String originalFileName;
	private Timestamp regdate;
	private String email;
	private int price;
	
	
	
	
}
