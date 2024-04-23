package com.develup.noramore.forbidden.model.vo;

import java.sql.Date;

public class Forbidden implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3019851411985854571L;
	private int fbId;
	private String fbWord;
	private Date registDate;
	
	public Forbidden() {
		super();
	}

	public int getFbId() {
		return fbId;
	}

	public void setFbId(int fbId) {
		this.fbId = fbId;
	}

	public String getFbWord() {
		return fbWord;
	}

	public void setFbWord(String fbWord) {
		this.fbWord = fbWord;
	}

	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	@Override
	public String toString() {
		return "Forbidden [fbId=" + fbId + ", fbWord=" + fbWord + ", registDate=" + registDate + "]";
	}
	
	
	
}
