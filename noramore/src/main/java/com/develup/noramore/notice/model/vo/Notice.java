package com.develup.noramore.notice.model.vo;

import java.sql.Date;

public class Notice implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int boardId;
    private String memberID;
    private String title;
    private String substance;
    private int readCount;
    private Date registDt;
    private Date lastUpdDt;
    private String originalFilePath;
	private String renameFilePath;
		
	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Notice(int boardId, String memberID, String title, String substance, int readCount, Date registDt,
			Date lastUpdDt, String originalFilePath, String renameFilePath) {
		super();
		this.boardId = boardId;
		this.memberID = memberID;
		this.title = title;
		this.substance = substance;
		this.readCount = readCount;
		this.registDt = registDt;
		this.lastUpdDt = lastUpdDt;
		this.originalFilePath = originalFilePath;
		this.renameFilePath = renameFilePath;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getmemberID() {
		return memberID;
	}

	public void setmemberID(String memberID) {
		this.memberID = memberID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubstance() {
		return substance;
	}

	public void setSubstance(String substance) {
		this.substance = substance;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public Date getRegistDt() {
		return registDt;
	}

	public void setRegistDt(Date registDt) {
		this.registDt = registDt;
	}

	public Date getLastUpdDt() {
		return lastUpdDt;
	}

	public void setLastUpdDt(Date lastUpdDt) {
		this.lastUpdDt = lastUpdDt;
	}

	public String getOriginalFilePath() {
		return originalFilePath;
	}

	public void setOriginalFilePath(String originalFilePath) {
		this.originalFilePath = originalFilePath;
	}

	public String getRenameFilePath() {
		return renameFilePath;
	}

	public void setRenameFilePath(String renameFilePath) {
		this.renameFilePath = renameFilePath;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Notice [boardId=" + boardId + ", memberID=" + memberID + ", title=" + title + ", substance=" + substance
				+ ", readCount=" + readCount + ", registDt=" + registDt + ", lastUpdDt=" + lastUpdDt
				+ ", originalFilePath=" + originalFilePath + ", renameFilePath=" + renameFilePath + "]";
	}
    
    
    
    
}
