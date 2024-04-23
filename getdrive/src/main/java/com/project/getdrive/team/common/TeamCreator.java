package com.project.getdrive.team.common;

public class TeamCreator {

	//팀 생성을 위한 객체
	private int tMID;
	private String tName;
	
	public TeamCreator() {
		super();
	}

	public TeamCreator(int tMID, String tName) {
		super();
		this.tMID = tMID;
		this.tName = tName;
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

	@Override
	public String toString() {
		return "TeamCreator [tMID=" + tMID + ", tName=" + tName + "]";
	}	
}
