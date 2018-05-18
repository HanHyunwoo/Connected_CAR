package com.vo;

public class User {
	/*
	 * USERID VARCHAR(20) NOT NULL, -- 아이디 
	 * USERPW VARCHAR(20) NULL, 	-- 비밀번호
	 * USERPHONE VARCHAR(20) NULL,  -- 전화번호 
	 * USERBIRTH VARCHAR(20) NULL,  -- 생년월일
	 * USERADDR VARCHAR(100) NULL,  -- 주소 
	 * CATE NUMBER(1) NULL 			-- 관리자
	 */

	String USERID;
	String USERPW;
	String USERPHONE;
	String USERBIRTH;
	String USERADDR;
	String CATE;

	public String getUSERID() {
		return USERID;
	}

	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}

	public String getUSERPW() {
		return USERPW;
	}

	public void setUSERPW(String uSERPW) {
		USERPW = uSERPW;
	}

	public String getUSERPHONE() {
		return USERPHONE;
	}

	public void setUSERPHONE(String uSERPHONE) {
		USERPHONE = uSERPHONE;
	}

	public String getUSERBIRTH() {
		return USERBIRTH;
	}

	public void setUSERBIRTH(String uSERBIRTH) {
		USERBIRTH = uSERBIRTH;
	}

	public String getUSERADDR() {
		return USERADDR;
	}

	public void setUSERADDR(String uSERADDR) {
		USERADDR = uSERADDR;
	}

	public String getCATE() {
		return CATE;
	}

	public void setCATE(String cATE) {
		CATE = cATE;
	}
}
