package com.project.getdrive.file.model.vo;

import java.sql.Date;

public class File implements java.io.Serializable{
	private static final long serialVersionUID = -1406665050011950744L;

	private int flNo;
	private String flName;
	private String flExt;
	private String flLoc;
	private long flSize;
	private Date flCdate;
	private Date flUdate;
	private int flCRUID;
	private int flFDID;
	private int flDID;
	private int flTID;
	
	public File() {
		super();
	}

	public File(int flNo, String flName, String flExt, String flLoc, int flSize, Date flCdate, Date flUdate,
			int flCRUID, int flFDID, int flDID, int flTID) {
		super();
		this.flNo = flNo;
		this.flName = flName;
		this.flExt = flExt;
		this.flLoc = flLoc;
		this.flSize = flSize;
		this.flCdate = flCdate;
		this.flUdate = flUdate;
		this.flCRUID = flCRUID;
		this.flFDID = flFDID;
		this.flDID = flDID;
		this.flTID = flTID;
	}

	public int getFlNo() {
		return flNo;
	}

	public void setFlNo(int flNo) {
		this.flNo = flNo;
	}

	public String getFlName() {
		return flName;
	}

	public void setFlName(String flName) {
		this.flName = flName;
	}

	public String getFlExt() {
		return flExt;
	}

	public void setFlExt(String flExt) {
		this.flExt = flExt;
	}

	public String getFlLoc() {
		return flLoc;
	}

	public void setFlLoc(String flLoc) {
		this.flLoc = flLoc;
	}

	public long getFlSize() {
		return flSize;
	}

	public void setFlSize(long l) {
		this.flSize = l;
	}

	public Date getFlCdate() {
		return flCdate;
	}

	public void setFlCdate(Date flCdate) {
		this.flCdate = flCdate;
	}

	public Date getFlUdate() {
		return flUdate;
	}

	public void setFlUdate(Date flUdate) {
		this.flUdate = flUdate;
	}

	public int getFlCRUID() {
		return flCRUID;
	}

	public void setFlCRUID(int flCRUID) {
		this.flCRUID = flCRUID;
	}

	public int getFlFDID() {
		return flFDID;
	}

	public void setFlFDID(int flFDID) {
		this.flFDID = flFDID;
	}

	public int getFlDID() {
		return flDID;
	}

	public void setFlDID(int flDID) {
		this.flDID = flDID;
	}

	public int getFlTID() {
		return flTID;
	}

	public void setFlTID(int flTID) {
		this.flTID = flTID;
	}

	@Override
	public String toString() {
		return "File [flNo=" + flNo + ", flName=" + flName + ", flExt=" + flExt + ", flLoc=" + flLoc + ", flSize="
				+ flSize + ", flCdate=" + flCdate + ", flUdate=" + flUdate + ", flCRUID=" + flCRUID + ", flFDID="
				+ flFDID + ", flDID=" + flDID + ", flTID=" + flTID + "]";
	}
}
