package com.develup.noramore.admin.model.vo;

import java.sql.Date;

public class BoardManage {
	private String boardRef;
	private int categoryId;
	private String categoryName;
	private int boardId;
	private String title;
	private String memberID;
	private int reportCount;
	private int readCount;
	private Date registDate;
	
	public BoardManage() {
		super();
	}

	public BoardManage(String boardRef, int categoryId, String categoryName, int boardId, String title, String memberID,
			int reportCount, int readCount, Date registDate) {
		super();
		this.boardRef = boardRef;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.boardId = boardId;
		this.title = title;
		this.memberID = memberID;
		this.reportCount = reportCount;
		this.readCount = readCount;
		this.registDate = registDate;
	}

	public String getBoardRef() {
		return boardRef;
	}

	public void setBoardRef(String boardRef) {
		this.boardRef = boardRef;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public int getReportCount() {
		return reportCount;
	}

	public void setReportCount(int reportCount) {
		this.reportCount = reportCount;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	@Override
	public String toString() {
		return "BoardManage [boardRef=" + boardRef + ", categoryId=" + categoryId + ", categoryName=" + categoryName
				+ ", boardId=" + boardId + ", title=" + title + ", memberID=" + memberID + ", reportCount="
				+ reportCount + ", readCount=" + readCount + ", registDate=" + registDate + "]";
	}

	
	
}
