package com.project.getdrive.team.common;

public class Invit {
	
	private int	accountNo;
	private int tNo;
	
	public Invit() {
		super();
	}
	
	public Invit(int accountNo, int tNo) {
		super();
		this.accountNo = accountNo;
		this.tNo = tNo;
	}

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public int gettNo() {
		return tNo;
	}

	public void settNo(int tNo) {
		this.tNo = tNo;
	}

	@Override
	public String toString() {
		return "Invit [accountNo=" + accountNo + ", tNo=" + tNo + "]";
	}
	
	
}
