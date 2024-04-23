package com.project.getdrive.common;

//월별 스케줄 기능 출력 객체
public class CommonCL {
	
	private int year;
	private int month;
	private int day;
	private int tNo;
	private int accountNo;
	
	public CommonCL() {
		super();
	}

	public CommonCL(int year, int month, int day, int tNo, int accountNo) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
		this.tNo = tNo;
		this.accountNo = accountNo;
	}

	public int getYear() {
		return year;
	}

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
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

	public int gettNo() {
		return tNo;
	}

	public void settNo(int tNo) {
		this.tNo = tNo;
	}

	@Override
	public String toString() {
		return "CommonCL [year=" + year + ", month=" + month + ", day=" + day + ", tNo=" + tNo + ", accountNo="
				+ accountNo + "]";
	}

	
}
