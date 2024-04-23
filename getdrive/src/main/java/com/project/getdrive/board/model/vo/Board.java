package com.project.getdrive.board.model.vo;

import java.sql.Date;

public class Board implements java.io.Serializable {
	private static final long serialVersionUID = -995192707510844419L;
	
	private int bNo;
	private String bTitle;
	private String bContent;
	private int bCruid;
	private String bName;
	private String bId;
	private Date bcDate;
	private Date buDate;
	private int bctId;
	private int tNo;
	private String bOriginFileName;
	private String bRenameFileName;
	
	public Board() {
		super();
	}

	public Board(int bNo, String bTitle, String bContent, int bCruid, String bName, String bId, Date bcDate,
			Date buDate, int bctId, int tNo, String bOriginFileName, String bRenameFileName) {
		super();
		this.bNo = bNo;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bCruid = bCruid;
		this.bName = bName;
		this.bId = bId;
		this.bcDate = bcDate;
		this.buDate = buDate;
		this.bctId = bctId;
		this.tNo = tNo;
		this.bOriginFileName = bOriginFileName;
		this.bRenameFileName = bRenameFileName;
	}
	
	

	public int getbNo() {
		return bNo;
	}

	public void setbNo(int bNo) {
		this.bNo = bNo;
	}

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	public int getbCruid() {
		return bCruid;
	}

	public void setbCruid(int bCruid) {
		this.bCruid = bCruid;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getbId() {
		return bId;
	}

	public void setbId(String bId) {
		this.bId = bId;
	}

	public Date getBcDate() {
		return bcDate;
	}

	public void setBcDate(Date bcDate) {
		this.bcDate = bcDate;
	}

	public Date getBuDate() {
		return buDate;
	}

	public void setBuDate(Date buDate) {
		this.buDate = buDate;
	}

	public int getBctId() {
		return bctId;
	}

	public void setBctId(int bctId) {
		this.bctId = bctId;
	}

	public int getTNo() {
		return tNo;
	}

	public void setTNo(int tNo) {
		this.tNo = tNo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getbOriginFileName() {
		return bOriginFileName;
	}

	public void setbOriginFileName(String bOriginFileName) {
		this.bOriginFileName = bOriginFileName;
	}

	public String getbRenameFileName() {
		return bRenameFileName;
	}

	public void setbRenameFileName(String bRenameFileName) {
		this.bRenameFileName = bRenameFileName;
	}

	@Override
	public String toString() {
		return "Board [bNo=" + bNo + ", bTitle=" + bTitle + ", bContent=" + bContent + ", bCruid=" + bCruid + ", bName="
				+ bName + ", bId=" + bId + ", bcDate=" + bcDate + ", buDate=" + buDate + ", bctId=" + bctId + ", tNo="
				+ tNo + ", bOriginFileName=" + bOriginFileName + ", bRenameFileName=" + bRenameFileName + "]";
	}




}
