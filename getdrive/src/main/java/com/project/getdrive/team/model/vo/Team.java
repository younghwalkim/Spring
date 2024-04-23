package com.project.getdrive.team.model.vo;

import java.sql.Date;

public class Team implements java.io.Serializable{
	private static final long serialVersionUID = 3798304482121357637L;
	
	private int tNo;
	private int tMID;
	private String tName;
	private Date tDate;
	
	// 조인된 결과
	private int tuMID;
	private Date tuODate;
	private String tuLeader;
	private String tuInvited;
	private String tuEmail;
	
	public Team() {
		super();
	}

	public Team(int tNo, int tMID, String tName, Date tDate, int tuMID, Date tuODate, String tuLeader, String tuInvited,
			String tuEmail) {
		super();
		this.tNo = tNo;
		this.tMID = tMID;
		this.tName = tName;
		this.tDate = tDate;
		this.tuMID = tuMID;
		this.tuODate = tuODate;
		this.tuLeader = tuLeader;
		this.tuInvited = tuInvited;
		this.tuEmail = tuEmail;
	}

	public int gettNo() {
		return tNo;
	}

	public void settNo(int tNo) {
		this.tNo = tNo;
	}

	public int gettMID() {
		return tMID;
	}

	public void settMID(int tMID) {
		this.tMID = tMID;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public Date gettDate() {
		return tDate;
	}

	public void settDate(Date tDate) {
		this.tDate = tDate;
	}

	public int getTuMID() {
		return tuMID;
	}

	public void setTuMID(int tuMID) {
		this.tuMID = tuMID;
	}

	public Date getTuODate() {
		return tuODate;
	}

	public void setTuODate(Date tuODate) {
		this.tuODate = tuODate;
	}

	public String getTuLeader() {
		return tuLeader;
	}

	public void setTuLeader(String tuLeader) {
		this.tuLeader = tuLeader;
	}

	public String getTuInvited() {
		return tuInvited;
	}

	public void setTuInvited(String tuInvited) {
		this.tuInvited = tuInvited;
	}

	public String getTuEmail() {
		return tuEmail;
	}

	public void setTuEmail(String tuEmail) {
		this.tuEmail = tuEmail;
	}

	@Override
	public String toString() {
		return "Team [tNo=" + tNo + ", tMID=" + tMID + ", tName=" + tName + ", tDate=" + tDate + ", tuMID=" + tuMID
				+ ", tuODate=" + tuODate + ", tuLeader=" + tuLeader + ", tuInvited=" + tuInvited + ", tuEmail="
				+ tuEmail + "]";
	}
}
