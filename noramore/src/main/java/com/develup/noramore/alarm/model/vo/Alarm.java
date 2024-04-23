package com.develup.noramore.alarm.model.vo;

import java.sql.Date;

import com.develup.noramore.common.Paging;

public class Alarm implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8135339567012332208L;
	private int alarmId;
	private String alarmKind;
	private int nativeId;
	private String receiverId;
	private String senderId;
	private String checkedYN;
	private Date registDate;
	
	private int categoryId;
	private int boardId;
	private String title;
	private String context;
	private int refCommentId;
	private int startRow;
	private int endRow;

	public Alarm() {
		super();
	}

	public Alarm(int alarmId, String alarmKind, int nativeId, String receiverId, String senderId, String checkedYN,
			Date registDate, int categoryId, int boardId, String title, String context, int refCommentId, int startRow,
			int endRow) {
		super();
		this.alarmId = alarmId;
		this.alarmKind = alarmKind;
		this.nativeId = nativeId;
		this.receiverId = receiverId;
		this.senderId = senderId;
		this.checkedYN = checkedYN;
		this.registDate = registDate;
		this.categoryId = categoryId;
		this.boardId = boardId;
		this.title = title;
		this.context = context;
		this.refCommentId = refCommentId;
		this.startRow = startRow;
		this.endRow = endRow;
	}

	public int getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(int alarmId) {
		this.alarmId = alarmId;
	}

	public String getAlarmKind() {
		return alarmKind;
	}

	public void setAlarmKind(String alarmKind) {
		this.alarmKind = alarmKind;
	}

	public int getNativeId() {
		return nativeId;
	}

	public void setNativeId(int nativeId) {
		this.nativeId = nativeId;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getCheckedYN() {
		return checkedYN;
	}

	public void setCheckedYN(String checkedYN) {
		this.checkedYN = checkedYN;
	}

	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
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

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public int getRefCommentId() {
		return refCommentId;
	}

	public void setRefCommentId(int refCommentId) {
		this.refCommentId = refCommentId;
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

	@Override
	public String toString() {
		return "Alarm [alarmId=" + alarmId + ", alarmKind=" + alarmKind + ", nativeId=" + nativeId + ", receiverId="
				+ receiverId + ", senderId=" + senderId + ", checkedYN=" + checkedYN + ", registDate=" + registDate
				+ ", categoryId=" + categoryId + ", boardId=" + boardId + ", title=" + title + ", context=" + context
				+ ", refCommentId=" + refCommentId + ", startRow=" + startRow + ", endRow=" + endRow + "]";
	}

}
