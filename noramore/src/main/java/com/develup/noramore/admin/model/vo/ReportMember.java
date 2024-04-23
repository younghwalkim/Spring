package com.develup.noramore.admin.model.vo;

import java.sql.Date;

public class ReportMember implements java.io.Serializable{
	

	private static final long serialVersionUID = -1385736250837785774L;
	
	private String memberID;     //회원아이디
	private String memberNicname;
	private String memberName;      //이름
	private Date birth;       //생년월일
	private String gender;			//성별
	private String email;          //이메일
	private String address;        //주소
	private String resign;         //회원탈퇴
	private String actLimit;        //사용제약
	private Date registDate;       //등록일자 
	private int heartBeat;     //하트비트
	private String grade;       //등급
	private int articleCount;  //활동건수
	private int reportCount;    //신고건수
	
	public ReportMember() {
		super();
	}
	public ReportMember(String memberID, String memberNicname, String memberName, Date birth, String gender,
			String email, String address, String resign, String actLimit, Date registDate, int heartBeat, String grade,
			int articleCount, int reportCount) {
		super();
		this.memberID = memberID;
		this.memberNicname = memberNicname;
		this.memberName = memberName;
		this.birth = birth;
		this.gender = gender;
		this.email = email;
		this.address = address;
		this.resign = resign;
		this.actLimit = actLimit;
		this.registDate = registDate;
		this.heartBeat = heartBeat;
		this.grade = grade;
		this.articleCount = articleCount;
		this.reportCount = reportCount;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getMemberNicname() {
		return memberNicname;
	}
	public void setMemberNicname(String memberNicname) {
		this.memberNicname = memberNicname;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getResign() {
		return resign;
	}
	public void setResign(String resign) {
		this.resign = resign;
	}
	public String getActLimit() {
		return actLimit;
	}
	public void setActLimit(String actLimit) {
		this.actLimit = actLimit;
	}
	public Date getRegistDate() {
		return registDate;
	}
	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}
	public int getHeartBeat() {
		return heartBeat;
	}
	public void setHeartBeat(int heartBeat) {
		this.heartBeat = heartBeat;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}
	public int getReportCount() {
		return reportCount;
	}
	public void setReportCount(int reportCount) {
		this.reportCount = reportCount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "ReportMember [memberID=" + memberID + ", memberNicname=" + memberNicname + ", memberName=" + memberName
				+ ", birth=" + birth + ", gender=" + gender + ", email=" + email + ", address=" + address + ", resign="
				+ resign + ", actLimit=" + actLimit + ", registDate=" + registDate + ", heartBeat=" + heartBeat
				+ ", grade=" + grade + ", articleCount=" + articleCount + ", reportCount=" + reportCount + "]";
	}
	
}
