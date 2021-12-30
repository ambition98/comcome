package com.gr.comcome.messageBoxRec.model;

import com.gr.comcome.messagebox.model.MessageBoxVO;

public class AllMessageVO extends MessageBoxVO{

	private int recvno;
	private int accountno;
	private int messageno;
	private String readflag;
	private String delflag;
	
	@Override
	public String toString() {
		return "AllMessageVO [recvno=" + recvno + ", accountno=" + accountno + ", messageno=" + messageno
				+ ", readflag=" + readflag + ", delflag=" + delflag + "]";
	}
	public int getRecvno() {
		return recvno;
	}
	public void setRecvno(int recvno) {
		this.recvno = recvno;
	}
	public int getAccountno() {
		return accountno;
	}
	public void setAccountno(int accountno) {
		this.accountno = accountno;
	}
	public int getMessageno() {
		return messageno;
	}
	public void setMessageno(int messageno) {
		this.messageno = messageno;
	}
	public String getReadflag() {
		return readflag;
	}
	public void setReadflag(String readflag) {
		this.readflag = readflag;
	}
	public String getDelflag() {
		return delflag;
	}
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}
	
}
