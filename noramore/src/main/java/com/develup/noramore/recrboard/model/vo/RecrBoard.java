package com.develup.noramore.recrboard.model.vo;

import java.sql.Date;

public class RecrBoard implements java.io.Serializable {
	private static final long serialVersionUID = 3659973663458254242L;
	
	private int boardId; 				// 모집글 ID
	private String memberId; 			// 모집글 작성자ID
	private int categoryId;				// 카테고리ID
	private String title; 				// 모집글 제목
	private String context; 			// 모집글 내용
	private int reportCount; 			// 신고 횟수
	private String genderCondition;		// 성별 조건
	private int ageMinCondition;		// 나이 조건(최소)
	private int ageMaxCondition;		// 나이 조건(최대)
	private int maxRecr;				// 모집 인원
	private int nowRecr; 				// 현재 인원
	private String recrStatus;			// 모집 상태
	private Date recrStartDate; 		// 모집시작일
	private Date recrEndDate; 			// 모집종료일
	private String recrOriginalFilename;//모집원본파일명
    private String recrRenameFilename;	//모집변경파일명
	private Date recrActStartDate; 		// 활동시작일시
	private Date recrActEndDate; 		// 활동종료일시
	private String recrLocation; 		// 위치정보
	private int commentCount; 			// 댓글 개수
	private int readCount; 				// 조회수
	private Date registrationDate; 		// 등록일자
	private Date lastUpdateDate; 		// 수정일자
	
	public RecrBoard() {
		super();
	}

	public RecrBoard(int boardId, String memberId, int categoryId, String title, String context, int reportCount,
			String genderCondition, int ageMinCondition, int ageMaxCondition, int maxRecr, int nowRecr,
			String recrStatus, Date recrStartDate, Date recrEndDate, String recrOriginalFilename,
			String recrRenameFilename, Date recrActStartDate, Date recrActEndDate, String recrLocation,
			int commentCount, int readCount, Date registrationDate, Date lastUpdateDate) {
		super();
		this.boardId = boardId;
		this.memberId = memberId;
		this.categoryId = categoryId;
		this.title = title;
		this.context = context;
		this.reportCount = reportCount;
		this.genderCondition = genderCondition;
		this.ageMinCondition = ageMinCondition;
		this.ageMaxCondition = ageMaxCondition;
		this.maxRecr = maxRecr;
		this.nowRecr = nowRecr;
		this.recrStatus = recrStatus;
		this.recrStartDate = recrStartDate;
		this.recrEndDate = recrEndDate;
		this.recrOriginalFilename = recrOriginalFilename;
		this.recrRenameFilename = recrRenameFilename;
		this.recrActStartDate = recrActStartDate;
		this.recrActEndDate = recrActEndDate;
		this.recrLocation = recrLocation;
		this.commentCount = commentCount;
		this.readCount = readCount;
		this.registrationDate = registrationDate;
		this.lastUpdateDate = lastUpdateDate;
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

	public int getReportCount() {
		return reportCount;
	}

	public void setReportCount(int reportCount) {
		this.reportCount = reportCount;
	}

	public String getGenderCondition() {
		return genderCondition;
	}

	public void setGenderCondition(String genderCondition) {
		this.genderCondition = genderCondition;
	}

	public int getAgeMinCondition() {
		return ageMinCondition;
	}

	public void setAgeMinCondition(int ageMinCondition) {
		this.ageMinCondition = ageMinCondition;
	}

	public int getAgeMaxCondition() {
		return ageMaxCondition;
	}

	public void setAgeMaxCondition(int ageMaxCondition) {
		this.ageMaxCondition = ageMaxCondition;
	}

	public int getMaxRecr() {
		return maxRecr;
	}

	public void setMaxRecr(int maxRecr) {
		this.maxRecr = maxRecr;
	}

	public int getNowRecr() {
		return nowRecr;
	}

	public void setNowRecr(int nowRecr) {
		this.nowRecr = nowRecr;
	}

	public String getRecrStatus() {
		return recrStatus;
	}

	public void setRecrStatus(String recrStatus) {
		this.recrStatus = recrStatus;
	}

	public Date getRecrStartDate() {
		return recrStartDate;
	}

	public void setRecrStartDate(Date recrStartDate) {
		this.recrStartDate = recrStartDate;
	}

	public Date getRecrEndDate() {
		return recrEndDate;
	}

	public void setRecrEndDate(Date recrEndDate) {
		this.recrEndDate = recrEndDate;
	}

	public String getRecrOriginalFilename() {
		return recrOriginalFilename;
	}

	public void setRecrOriginalFilename(String recrOriginalFilename) {
		this.recrOriginalFilename = recrOriginalFilename;
	}

	public String getRecrRenameFilename() {
		return recrRenameFilename;
	}

	public void setRecrRenameFilename(String recrRenameFilename) {
		this.recrRenameFilename = recrRenameFilename;
	}

	public Date getRecrActStartDate() {
		return recrActStartDate;
	}

	public void setRecrActStartDate(Date recrActStartDate) {
		this.recrActStartDate = recrActStartDate;
	}

	public Date getRecrActEndDate() {
		return recrActEndDate;
	}

	public void setRecrActEndDate(Date recrActEndDate) {
		this.recrActEndDate = recrActEndDate;
	}

	public String getRecrLocation() {
		return recrLocation;
	}

	public void setRecrLocation(String recrLocation) {
		this.recrLocation = recrLocation;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	@Override
	public String toString() {
		return "RecrBoard [boardId=" + boardId + ", memberId=" + memberId + ", categoryId=" + categoryId + ", title="
				+ title + ", context=" + context + ", reportCount=" + reportCount + ", genderCondition="
				+ genderCondition + ", ageMinCondition=" + ageMinCondition + ", ageMaxCondition=" + ageMaxCondition
				+ ", maxRecr=" + maxRecr + ", nowRecr=" + nowRecr + ", recrStatus=" + recrStatus + ", recrStartDate="
				+ recrStartDate + ", recrEndDate=" + recrEndDate + ", recrOriginalFilename=" + recrOriginalFilename
				+ ", recrRenameFilename=" + recrRenameFilename + ", recrActStartDate=" + recrActStartDate
				+ ", recrActEndDate=" + recrActEndDate + ", recrLocation=" + recrLocation + ", commentCount="
				+ commentCount + ", readCount=" + readCount + ", registrationDate=" + registrationDate
				+ ", lastUpdateDate=" + lastUpdateDate + "]";
	}

	
    
    
}// class
