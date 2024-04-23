package com.project.getdrive.drive.model.vo;

import java.sql.Date;

public class Drive implements java.io.Serializable{
	private static final long serialVersionUID = 179458836295131024L;

	private int dNo;
	private String dName;
	private Date dCDate;
	private Date dUDate;
	private String dPub;
	private String dTRCan;
	private int dCRUID;
	private int dTID;
	
	public Drive() {
		super();
	}

	public Drive(int dNo, String dName, Date dCDate, Date dUDate, String dPub, String dTRCan, int dCRUID, int dTID) {
		super();
		this.dNo = dNo;
		this.dName = dName;
		this.dCDate = dCDate;
		this.dUDate = dUDate;
		this.dPub = dPub;
		this.dTRCan = dTRCan;
		this.dCRUID = dCRUID;
		this.dTID = dTID;
	}

	public int getdNo() {
		return dNo;
	}

	public void setdNo(int dNo) {
		this.dNo = dNo;
	}

	public String getdName() {
		return dName;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}

	public Date getdCDate() {
		return dCDate;
	}

	public void setdCDate(Date dCDate) {
		this.dCDate = dCDate;
	}

	public Date getdUDate() {
		return dUDate;
	}

	public void setdUDate(Date dUDate) {
		this.dUDate = dUDate;
	}

	public String getdPub() {
		return dPub;
	}

	public void setdPub(String dPub) {
		this.dPub = dPub;
	}

	public String getdTRCan() {
		return dTRCan;
	}

	public void setdTRCan(String dTRCan) {
		this.dTRCan = dTRCan;
	}

	public int getdCRUID() {
		return dCRUID;
	}

	public void setdCRUID(int dCRUID) {
		this.dCRUID = dCRUID;
	}

	public int getdTID() {
		return dTID;
	}

	public void setdTID(int dTID) {
		this.dTID = dTID;
	}

	@Override
	public String toString() {
		return "Drive [dNo=" + dNo + ", dName=" + dName + ", dCDate=" + dCDate + ", dUDate=" + dUDate + ", dPub=" + dPub
				+ ", dTRCan=" + dTRCan + ", dCRUID=" + dCRUID + ", dTID=" + dTID + "]";
	}
}
