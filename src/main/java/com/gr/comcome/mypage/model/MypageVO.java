package com.gr.comcome.mypage.model;

import java.security.Timestamp;

public class MypageVO {
	private int ACCOUNT_NO;
	private String EMAIL;
	private String NAME;
	private String ADDRESS;
	private String TEL;
	private String IS_VERIFIED;
	private String CARD_NO;
	private Timestamp REGDATE;
	public int getACCOUNT_NO() {
		return ACCOUNT_NO;
	}
	public void setACCOUNT_NO(int aCCOUNT_NO) {
		ACCOUNT_NO = aCCOUNT_NO;
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
	public String getIS_VERIFIED() {
		return IS_VERIFIED;
	}
	public void setIS_VERIFIED(String iS_VERIFIED) {
		IS_VERIFIED = iS_VERIFIED;
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
	public MypageVO(int aCCOUNT_NO, String eMAIL, String nAME, String aDDRESS, String tEL, String iS_VERIFIED,
			String cARD_NO, Timestamp rEGDATE) {
		super();
		ACCOUNT_NO = aCCOUNT_NO;
		EMAIL = eMAIL;
		NAME = nAME;
		ADDRESS = aDDRESS;
		TEL = tEL;
		IS_VERIFIED = iS_VERIFIED;
		CARD_NO = cARD_NO;
		REGDATE = rEGDATE;
	}
	@Override
	public String toString() {
		return "MypageVO [ACCOUNT_NO=" + ACCOUNT_NO + ", EMAIL=" + EMAIL + ", NAME=" + NAME + ", ADDRESS=" + ADDRESS
				+ ", TEL=" + TEL + ", IS_VERIFIED=" + IS_VERIFIED + ", CARD_NO=" + CARD_NO + ", REGDATE=" + REGDATE
				+ "]";
	}
	public MypageVO() {
		super();
	}
	
	
}
