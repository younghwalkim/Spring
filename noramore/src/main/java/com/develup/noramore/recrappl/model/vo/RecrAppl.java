package com.develup.noramore.recrappl.model.vo;

import java.sql.Date;

public class RecrAppl implements java.io.Serializable{
	private static final long serialVersionUID = -2140723251954122029L;
	
	private int boardId; 		// 게시글 ID
	private String memberId; 	// 회원 ID
	private String recrState;	// 모집 상태
	private Date registDate; 	// 등록 날짜
	private Date lastUpdDate; 	// 마지막 업데이트 날짜
    
	public RecrAppl() {
		super();
	}

	public RecrAppl(int boardId, String memberId, String recrState, Date registDate, Date lastUpdDate) {
		super();
		this.boardId = boardId;
		this.memberId = memberId;
		this.recrState = recrState;
		this.registDate = registDate;
		this.lastUpdDate = lastUpdDate;
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

	public String getRecrState() {
		return recrState;
	}

	public void setRecrState(String recrState) {
		this.recrState = recrState;
	}

	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	public Date getLastUpdDate() {
		return lastUpdDate;
	}

	public void setLastUpdDate(Date lastUpdDate) {
		this.lastUpdDate = lastUpdDate;
	}

	@Override
	public String toString() {
		return "RecrAppl [boardId=" + boardId + ", memberId=" + memberId + ", recrState=" + recrState + ", registDate="
				+ registDate + ", lastUpdDate=" + lastUpdDate + "]";
	}
	
	
}//
