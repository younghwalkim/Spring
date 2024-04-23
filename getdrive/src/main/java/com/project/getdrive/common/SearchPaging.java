package com.project.getdrive.common;

import java.sql.Date;

public class SearchPaging {
	
	//페이지 검색 기능을 위해 추가함
	   private String keyword;
	   private int startRow;  //페이지의 시작
	   private int endRow;   //페이지의 끝
	   private Date begin;
	   private Date end;
	   private int tNo;
	   
	public SearchPaging() {
		super();
	}
	
	public SearchPaging(String keyword, int startRow, int endRow, Date begin, Date end, int tNo) {
		super();
		this.keyword = keyword;
		this.startRow = startRow;
		this.endRow = endRow;
		this.begin = begin;
		this.end = end;
		this.tNo = tNo;
	}
	
	public int gettNo() {
		return tNo;
	}
	
	public void settNo(int tNo) {
		this.tNo = tNo;
	}
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	public Date getBegin() {
		return begin;
	}
	public void setBegin(Date begin) {
		this.begin = begin;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "SearchPaging [keyword=" + keyword + ", startRow=" + startRow + ", endRow=" + endRow + ", begin=" + begin
				+ ", end=" + end + ", tNo=" + tNo + "]";
	}
	

	   
	   

}
