package com.develup.noramore.freeboard.model.vo;

import java.sql.Date;

public class FreeBoard implements java.io.Serializable {
	
	private static final long serialVersionUID = 5569582721809744524L;
	
	private int boardId;
	private String memberId;
	private int categoryId;
	private String title;
	private String context;
	private String freeOriginalFileName;
	private String freeRenameFileName;  
	private int readCount;
	private int likeCount;
	private int reportCount;
	private java.sql.Date registDate;
	private java.sql.Date lastUpdDate;
	private int commentCount; 
	
	public FreeBoard() {
		super();
	}

	public FreeBoard(int boardId, String memberId, int categoryId, String title, String context,
			String freeOriginalFileName, String freeRenameFileName, int readCount, int likeCount, int reportCount,
			Date registDate, Date lastUpdDate, int commentCount) {
		super();
		this.boardId = boardId;
		this.memberId = memberId;
		this.categoryId = categoryId;
		this.title = title;
		this.context = context;
		this.freeOriginalFileName = freeOriginalFileName;
		this.freeRenameFileName = freeRenameFileName;
		this.readCount = readCount;
		this.likeCount = likeCount;
		this.reportCount = reportCount;
		this.registDate = registDate;
		this.lastUpdDate = lastUpdDate;
		this.commentCount = commentCount;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getFreeOriginalFileName() {
		return freeOriginalFileName;
	}

	public void setFreeOriginalFileName(String freeOriginalFileName) {
		this.freeOriginalFileName = freeOriginalFileName;
	}

	public String getFreeRenameFileName() {
		return freeRenameFileName;
	}

	public void setFreeRenameFileName(String freeRenameFileName) {
		this.freeRenameFileName = freeRenameFileName;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getReportCount() {
		return reportCount;
	}

	public void setReportCount(int reportCount) {
		this.reportCount = reportCount;
	}

	public java.sql.Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(java.sql.Date registDate) {
		this.registDate = registDate;
	}

	public java.sql.Date getLastUpdDate() {
		return lastUpdDate;
	}

	public void setLastUpdDate(java.sql.Date lastUpdDate) {
		this.lastUpdDate = lastUpdDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	@Override
	public String toString() {
		return "FreeBoard [boardId=" + boardId + ", memberId=" + memberId + ", categoryId=" + categoryId + ", title="
				+ title + ", context=" + context + ", freeOriginalFileName=" + freeOriginalFileName
				+ ", freeRenameFileName=" + freeRenameFileName + ", readCount=" + readCount + ", likeCount=" + likeCount
				+ ", reportCount=" + reportCount + ", registDate=" + registDate + ", lastUpdDate=" + lastUpdDate
				+ ", commentCount=" + commentCount + "]";
	}

	

	
	
	
	

}
