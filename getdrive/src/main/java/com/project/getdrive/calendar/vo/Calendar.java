package com.project.getdrive.calendar.vo;

import java.io.Serializable;

public class Calendar implements Serializable{

	private static final long serialVersionUID = -8323427735171465489L;
	
	
	private int calendarNo; //캘린더 고유번호
	private String calendarDate; //캘린더 날짜
	private String calendarTitle; 	//스케줄 제목
	private String calendarContent;	//스케줄 내용
	private String calendarStart; 	//캘린더 시작시간
	private String calendarEnd;	//캘린더 종료시간
	private String calendarCdate;	//캘린더 등록일
	private String calendarUdate;	//캘린더 수정일
	private String calendarCheck;	//캘린더 공개여부
	private int calendarCRUid;	//캘린더 작성자 고유번호
	private String calendarXpoint;	//캘린더 좌표(위도)
	private String calendarYpoint;	//캘린더 좌표(경도)
	private int calendarTid;	//팀 고유번호
	private int year; //년
	private int month;	//월
	private int day;	//일
	
	
	public Calendar() {
		super();
	}


	public Calendar(int calendarNo, String calendarDate, String calendarTitle, String calendarContent,
			String calendarStart, String calendarEnd, String calendarCdate, String calendarUdate, String calendarCheck,
			int calendarCRUid, String calendarXpoint, String calendarYpoint, int calendarTid, int year, int month,
			int day) {
		super();
		this.calendarNo = calendarNo;
		this.calendarDate = calendarDate;
		this.calendarTitle = calendarTitle;
		this.calendarContent = calendarContent;
		this.calendarStart = calendarStart;
		this.calendarEnd = calendarEnd;
		this.calendarCdate = calendarCdate;
		this.calendarUdate = calendarUdate;
		this.calendarCheck = calendarCheck;
		this.calendarCRUid = calendarCRUid;
		this.calendarXpoint = calendarXpoint;
		this.calendarYpoint = calendarYpoint;
		this.calendarTid = calendarTid;
		this.year = year;
		this.month = month;
		this.day = day;
	}


	public int getCalendarNo() {
		return calendarNo;
	}


	public void setCalendarNo(int calendarNo) {
		this.calendarNo = calendarNo;
	}


	public String getCalendarDate() {
		return calendarDate;
	}


	public void setCalendarDate(String calendarDate) {
		this.calendarDate = calendarDate;
	}


	public String getCalendarTitle() {
		return calendarTitle;
	}


	public void setCalendarTitle(String calendarTitle) {
		this.calendarTitle = calendarTitle;
	}


	public String getCalendarContent() {
		return calendarContent;
	}


	public void setCalendarContent(String calendarContent) {
		this.calendarContent = calendarContent;
	}


	public String getCalendarStart() {
		return calendarStart;
	}


	public void setCalendarStart(String calendarStart) {
		this.calendarStart = calendarStart;
	}


	public String getCalendarEnd() {
		return calendarEnd;
	}


	public void setCalendarEnd(String calendarEnd) {
		this.calendarEnd = calendarEnd;
	}


	public String getCalendarCdate() {
		return calendarCdate;
	}


	public void setCalendarCdate(String calendarCdate) {
		this.calendarCdate = calendarCdate;
	}


	public String getCalendarUdate() {
		return calendarUdate;
	}


	public void setCalendarUdate(String calendarUdate) {
		this.calendarUdate = calendarUdate;
	}


	public String getCalendarCheck() {
		return calendarCheck;
	}


	public void setCalendarCheck(String calendarCheck) {
		this.calendarCheck = calendarCheck;
	}


	public int getCalendarCRUid() {
		return calendarCRUid;
	}


	public void setCalendarCRUid(int calendarCRUid) {
		this.calendarCRUid = calendarCRUid;
	}


	public String getCalendarXpoint() {
		return calendarXpoint;
	}


	public void setCalendarXpoint(String calendarXpoint) {
		this.calendarXpoint = calendarXpoint;
	}


	public String getCalendarYpoint() {
		return calendarYpoint;
	}


	public void setCalendarYpoint(String calendarYpoint) {
		this.calendarYpoint = calendarYpoint;
	}


	public int getCalendarTid() {
		return calendarTid;
	}


	public void setCalendarTid(int calendarTid) {
		this.calendarTid = calendarTid;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public int getMonth() {
		return month;
	}


	public void setMonth(int month) {
		this.month = month;
	}


	public int getDay() {
		return day;
	}


	public void setDay(int day) {
		this.day = day;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "Calendar [calendarNo=" + calendarNo + ", calendarDate=" + calendarDate + ", calendarTitle="
				+ calendarTitle + ", calendarContent=" + calendarContent + ", calendarStart=" + calendarStart
				+ ", calendarEnd=" + calendarEnd + ", calendarCdate=" + calendarCdate + ", calendarUdate="
				+ calendarUdate + ", calendarCheck=" + calendarCheck + ", calendarCRUid=" + calendarCRUid
				+ ", calendarXpoint=" + calendarXpoint + ", calendarYpoint=" + calendarYpoint + ", calendarTid="
				+ calendarTid + ", year=" + year + ", month=" + month + ", day=" + day + "]";
	}
	
	
	
}