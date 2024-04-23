package com.project.getdrive.member.model.vo;

import java.sql.Date;

public class Social_Member implements java.io.Serializable{

	private static final long serialVersionUID = 5656692455386081056L;
	
	private int social_AccountNo;
	private int accountNo;
	private String social_Id;
	private String social_Email;
	private String social_Name;
	private String social_Type;
	private Date social_Cdate;
	private Date social_Ddate;
	
	public Social_Member() {
		super();
	}

	public Social_Member(int social_AccountNo, int accountNo, String social_Id, String social_Email, String social_Name,
			String social_Type, Date social_Cdate, Date social_Ddate) {
		super();
		this.social_AccountNo = social_AccountNo;
		this.accountNo = accountNo;
		this.social_Id = social_Id;
		this.social_Email = social_Email;
		this.social_Name = social_Name;
		this.social_Type = social_Type;
		this.social_Cdate = social_Cdate;
		this.social_Ddate = social_Ddate;
	}

	public int getSocial_AccountNo() {
		return social_AccountNo;
	}

	public void setSocial_AccountNo(int social_AccountNo) {
		this.social_AccountNo = social_AccountNo;
	}

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public String getSocial_Id() {
		return social_Id;
	}

	public void setSocial_Id(String social_Id) {
		this.social_Id = social_Id;
	}

	public String getSocial_Email() {
		return social_Email;
	}

	public void setSocial_Email(String social_Email) {
		this.social_Email = social_Email;
	}

	public String getSocial_Name() {
		return social_Name;
	}

	public void setSocial_Name(String social_Name) {
		this.social_Name = social_Name;
	}

	public String getSocial_Type() {
		return social_Type;
	}

	public void setSocial_Type(String social_Type) {
		this.social_Type = social_Type;
	}

	public Date getSocial_Cdate() {
		return social_Cdate;
	}

	public void setSocial_Cdate(Date social_Cdate) {
		this.social_Cdate = social_Cdate;
	}

	public Date getSocial_Ddate() {
		return social_Ddate;
	}

	public void setSocial_Ddate(Date social_Ddate) {
		this.social_Ddate = social_Ddate;
	}

	@Override
	public String toString() {
		return "Social_Member [social_AccountNo=" + social_AccountNo + ", accountNo=" + accountNo + ", social_Id="
				+ social_Id + ", social_Email=" + social_Email + ", social_Name=" + social_Name + ", social_Type="
				+ social_Type + ", social_Cdate=" + social_Cdate + ", social_Ddate=" + social_Ddate + "]";
	}
	
	
}
