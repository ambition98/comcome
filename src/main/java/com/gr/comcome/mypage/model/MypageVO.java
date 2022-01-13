package com.gr.comcome.mypage.model;

import java.security.Timestamp;

public class MypageVO {
	private int ACCOUNTNO;
	private String EMAIL;
	private String NAME;
	private String ADDRESS;
	private String TEL;
	private String ISVERIFIED;
	private String CARD_NO;
	private Timestamp REGDATE;
	@Override
	public String toString() {
		return "MypageVO [ACCOUNTNO=" + ACCOUNTNO + ", EMAIL=" + EMAIL + ", NAME=" + NAME + ", ADDRESS=" + ADDRESS
				+ ", TEL=" + TEL + ", ISVERIFIED=" + ISVERIFIED + ", CARD_NO=" + CARD_NO + ", REGDATE=" + REGDATE + "]";
	}
	public int getACCOUNTNO() {
		return ACCOUNTNO;
	}
	public void setACCOUNTNO(int aCCOUNTNO) {
		ACCOUNTNO = aCCOUNTNO;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}
	public String getTEL() {
		return TEL;
	}
	public void setTEL(String tEL) {
		TEL = tEL;
	}
	public String getISVERIFIED() {
		return ISVERIFIED;
	}
	public void setISVERIFIED(String iSVERIFIED) {
		ISVERIFIED = iSVERIFIED;
	}
	public String getCARD_NO() {
		return CARD_NO;
	}
	public void setCARD_NO(String cARD_NO) {
		CARD_NO = cARD_NO;
	}
	public Timestamp getREGDATE() {
		return REGDATE;
	}
	public void setREGDATE(Timestamp rEGDATE) {
		REGDATE = rEGDATE;
	}
	
	
	
}
