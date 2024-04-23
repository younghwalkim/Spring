package com.project.getdrive.meeting.model.vo;

import java.sql.Date;

public class Meeting implements java.io.Serializable {
	private static final long serialVersionUID = 1401137704835676588L;

	private int mtId;
	private String mtTitle;
	private Date mtDate;
	private Date mtUpdate;
	private int mtMid;
	private String mtWriter;
	private String mtWriterId;
	private String mtContent;
	private String mtOriginalFileName;
	private String mtRenameFileName;
	private String mtImportance;
	private int mtReadCount;
	private int mtTid;
	
	public Meeting() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Meeting(int mtId, String mtTitle, Date mtDate, Date mtUpdate, int mtMid, String mtWriter, String mtWriterId,
			String mtContent, String mtOriginalFileName, String mtRenameFileName, String mtImportance, int mtReadCount,
			int mtTid) {
		super();
		this.mtId = mtId;
		this.mtTitle = mtTitle;
		this.mtDate = mtDate;
		this.mtUpdate = mtUpdate;
		this.mtMid = mtMid;
		this.mtWriter = mtWriter;
		this.mtWriterId = mtWriterId;
		this.mtContent = mtContent;
		this.mtOriginalFileName = mtOriginalFileName;
		this.mtRenameFileName = mtRenameFileName;
		this.mtImportance = mtImportance;
		this.mtReadCount = mtReadCount;
		this.mtTid = mtTid;
	}
	
	public int getMtId() {
		return mtId;
	}
	public void setMtId(int mtId) {
		this.mtId = mtId;
	}
	public String getMtTitle() {
		return mtTitle;
	}
	public void setMtTitle(String mtTitle) {
		this.mtTitle = mtTitle;
	}
	public Date getMtDate() {
		return mtDate;
	}
	public void setMtDate(Date mtDate) {
		this.mtDate = mtDate;
	}
	public Date getMtUpdate() {
		return mtUpdate;
	}
	public void setMtUpdate(Date mtUpdate) {
		this.mtUpdate = mtUpdate;
	}
	public int getMtMid() {
		return mtMid;
	}
	public void setMtMid(int mtMid) {
		this.mtMid = mtMid;
	}
	public String getMtWriter() {
		return mtWriter;
	}
	public void setMtWriter(String mtWriter) {
		this.mtWriter = mtWriter;
	}
	public String getMtWriterId() {
		return mtWriterId;
	}
	public void setMtWriterId(String mtWriterId) {
		this.mtWriterId = mtWriterId;
	}
	public String getMtContent() {
		return mtContent;
	}
	public void setMtContent(String mtContent) {
		this.mtContent = mtContent;
	}
	public String getMtOriginalFileName() {
		return mtOriginalFileName;
	}
	public void setMtOriginalFileName(String mtOriginalFileName) {
		this.mtOriginalFileName = mtOriginalFileName;
	}
	public String getMtRenameFileName() {
		return mtRenameFileName;
	}
	public void setMtRenameFileName(String mtRenameFileName) {
		this.mtRenameFileName = mtRenameFileName;
	}
	public String getMtImportance() {
		return mtImportance;
	}
	public void setMtImportance(String mtImportance) {
		this.mtImportance = mtImportance;
	}
	public int getMtReadCount() {
		return mtReadCount;
	}
	public void setMtReadCount(int mtReadCount) {
		this.mtReadCount = mtReadCount;
	}
	public int getMtTid() {
		return mtTid;
	}
	public void setMtTid(int mtTid) {
		this.mtTid = mtTid;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "Meeting [mtId=" + mtId + ", mtTitle=" + mtTitle + ", mtDate=" + mtDate + ", mtUpdate=" + mtUpdate
				+ ", mtMid=" + mtMid + ", mtWriter=" + mtWriter + ", mtWriterId=" + mtWriterId + ", mtContent="
				+ mtContent + ", mtOriginalFileName=" + mtOriginalFileName + ", mtRenameFileName=" + mtRenameFileName
				+ ", mtImportance=" + mtImportance + ", mtReadCount=" + mtReadCount + ", mtTid=" + mtTid + "]";
	}
}
