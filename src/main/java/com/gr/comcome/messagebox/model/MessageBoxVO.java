package com.gr.comcome.messagebox.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageBoxVO {
	private int messageno;
	private int accountno ;
	private String title;
	private String content;
	private Timestamp senddate;
	private String delflag;
	public int getMessageno() {
		return messageno;
	}
	public void setMessageno(int messageno) {
		this.messageno = messageno;
	}
	public int getAccountno() {
		return accountno;
	}
	public void setAccountno(int accountno) {
		this.accountno = accountno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getSenddate() {
		return senddate;
	}
	public void setSenddate(Timestamp senddate) {
		this.senddate = senddate;
	}
	public String getDelflag() {
		return delflag;
	}
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}
	@Override
	public String toString() {
		return "MessageBoxVO [messageno=" + messageno + ", accountno=" + accountno + ", title=" + title + ", content="
				+ content + ", senddate=" + senddate + ", delflag=" + delflag + "]";
	}
	
	
}
