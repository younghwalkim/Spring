package com.project.getdrive.common;

public class AlarmSch {
	private int mNo;
	private int tNo;

	public AlarmSch() {
		super();
	}
	
	public AlarmSch(int mNo, int tNo) {
		super();
		this.mNo = mNo;
		this.tNo = tNo;
	}

	public int getmNo() {
		return mNo;
	}

	public void setmNo(int mNo) {
		this.mNo = mNo;
	}

	public int gettNo() {
		return tNo;
	}

	public void settNo(int tNo) {
		this.tNo = tNo;
	}

	@Override
	public String toString() {
		return "AlarmSch [mNo=" + mNo + ", tNo=" + tNo + "]";
	}
	
	
}
