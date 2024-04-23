package com.project.getdrive.folder.model.vo;

import java.sql.Date;

public class Folder implements java.io.Serializable{
	private static final long serialVersionUID = 5318711336578730581L;

	private int fdNo;
	private String fdName;
	private Date fdCdate;
	private Date fdUdate;
	private int fdCRUID;
	private int fdDID;
	private int fdTID;
	
	public Folder() {
		super();		
	}

	public Folder(int fdNo, String fdName, Date fdCdate, Date fdUdate, int fdCRUID, int fdDID, int fdTID) {
		super();
		this.fdNo = fdNo;
		this.fdName = fdName;
		this.fdCdate = fdCdate;
		this.fdUdate = fdUdate;
		this.fdCRUID = fdCRUID;
		this.fdDID = fdDID;
		this.fdTID = fdTID;
	}

	public int getFdNo() {
		return fdNo;
	}

	public void setFdNo(int fdNo) {
		this.fdNo = fdNo;
	}

	public String getFdName() {
		return fdName;
	}

	public void setFdName(String fdName) {
		this.fdName = fdName;
	}

	public Date getfdCdate() {
		return fdCdate;
	}

	public void setfdCdate(Date fdCdate) {
		this.fdCdate = fdCdate;
	}

	public Date getFdUdate() {
		return fdUdate;
	}

	public void setFdUdate(Date fdUdate) {
		this.fdUdate = fdUdate;
	}

	public int getFdCRUID() {
		return fdCRUID;
	}

	public void setFdCRUID(int fdCRUID) {
		this.fdCRUID = fdCRUID;
	}

	public int getFdDID() {
		return fdDID;
	}

	public void setFdDID(int fdDID) {
		this.fdDID = fdDID;
	}

	public int getFdTID() {
		return fdTID;
	}

	public void setFdTID(int fdTID) {
		this.fdTID = fdTID;
	}

	@Override
	public String toString() {
		return "Folder [fdNo=" + fdNo + ", fdName=" + fdName + ", fdCdate=" + fdCdate + ", fdUdate=" + fdUdate
				+ ", fdCRUID=" + fdCRUID + ", fdDID=" + fdDID + ", fdTID=" + fdTID + "]";
	}
}
