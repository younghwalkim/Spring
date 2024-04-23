package com.project.getdrive.teamuser.model.vo;

import java.sql.Date;

public class TeamUser implements java.io.Serializable{
	private static final long serialVersionUID = 7450783242834948527L;

	private int tuTID;
	private int tuMID;
	private Date tuEDate;
	private Date tuODate;
	private String tuLeader;
	private String tuInvited;
	private String tuEmail;
	
	public TeamUser() {
		super();
	}

	public TeamUser(int tuTID, int tuMID, Date tuEDate, Date tuODate, String tuLeader, String tuInvited,
			String tuEmail) {
		super();
		this.tuTID = tuTID;
		this.tuMID = tuMID;
		this.tuEDate = tuEDate;
		this.tuODate = tuODate;
		this.tuLeader = tuLeader;
		this.tuInvited = tuInvited;
		this.tuEmail = tuEmail;
	}

	public int getTuTID() {
		return tuTID;
	}

	public void setTuTID(int tuTID) {
		this.tuTID = tuTID;
	}

	public int getTuMID() {
		return tuMID;
	}

	public void setTuMID(int tuMID) {
		this.tuMID = tuMID;
	}

	public Date getTuEDate() {
		return tuEDate;
	}

	public void setTuEDate(Date tuEDate) {
		this.tuEDate = tuEDate;
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
		return "TeamUser [tuTID=" + tuTID + ", tuMID=" + tuMID + ", tuEDate=" + tuEDate + ", tuODate=" + tuODate
				+ ", tuLeader=" + tuLeader + ", tuInvited=" + tuInvited + ", tuEmail=" + tuEmail + "]";
	}
}
