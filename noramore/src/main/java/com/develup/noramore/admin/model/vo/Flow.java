package com.develup.noramore.admin.model.vo;

public class Flow implements java.io.Serializable{

	private static final long serialVersionUID = 3249163605837475252L;
	
	private int enrollYesterday;
	private int enrollToday;
	private int withdrawalYesterday;
	private int withdrawalToday;
	
	public Flow() {
		super();
	}

	public Flow(int enrollYesterday, int enrollToday, int withdrawalYesterday, int withdrawalToday) {
		super();
		this.enrollYesterday = enrollYesterday;
		this.enrollToday = enrollToday;
		this.withdrawalYesterday = withdrawalYesterday;
		this.withdrawalToday = withdrawalToday;
	}

	public int getEnrollYesterday() {
		return enrollYesterday;
	}

	public void setEnrollYesterday(int enrollYesterday) {
		this.enrollYesterday = enrollYesterday;
	}

	public int getEnrollToday() {
		return enrollToday;
	}

	public void setEnrollToday(int enrollToday) {
		this.enrollToday = enrollToday;
	}

	public int getWithdrawalYesterday() {
		return withdrawalYesterday;
	}

	public void setWithdrawalYesterday(int withdrawalYesterday) {
		this.withdrawalYesterday = withdrawalYesterday;
	}

	public int getWithdrawalToday() {
		return withdrawalToday;
	}

	public void setWithdrawalToday(int withdrawalToday) {
		this.withdrawalToday = withdrawalToday;
	}

	@Override
	public String toString() {
		return "Flow [enrollYesterday=" + enrollYesterday + ", enrollToday=" + enrollToday + ", withdrawalYesterday="
				+ withdrawalYesterday + ", withdrawalToday=" + withdrawalToday + "]";
	}
	
	
}
