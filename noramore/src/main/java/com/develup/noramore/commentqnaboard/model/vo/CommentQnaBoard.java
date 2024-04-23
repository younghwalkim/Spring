package com.develup.noramore.commentqnaboard.model.vo;

import java.sql.Date;

public class CommentQnaBoard implements java.io.Serializable{
	private static final long serialVersionUID = 8301082631266725585L;
	
	private int boardId;            // 게시판 ID
    private int commentId;          // 댓글 ID
    private String memberId;        // 회원 ID
    private String substance;         // 내용
    private int refCommentId;       // 상위 댓글 ID
    private int countSubComment;	// 하위 댓글 갯수
    private int reportCount;        // 신고 횟수
    private Date registDate;        // 등록 날짜
    private Date lastUpdateDate;    // 최종 수정 날짜
	
    public CommentQnaBoard() {
		super();
	}

	public CommentQnaBoard(int boardId, int commentId, String memberId, String substance, int refCommentId,
			int countSubComment, int reportCount, Date registDate, Date lastUpdateDate) {
		super();
		this.boardId = boardId;
		this.commentId = commentId;
		this.memberId = memberId;
		this.substance = substance;
		this.refCommentId = refCommentId;
		this.countSubComment = countSubComment;
		this.reportCount = reportCount;
		this.registDate = registDate;
		this.lastUpdateDate = lastUpdateDate;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getSubstance() {
		return substance;
	}

	public void setSubstance(String substance) {
		this.substance = substance;
	}

	public int getRefCommentId() {
		return refCommentId;
	}

	public void setRefCommentId(int refCommentId) {
		this.refCommentId = refCommentId;
	}

	public int getCountSubComment() {
		return countSubComment;
	}

	public void setCountSubComment(int countSubComment) {
		this.countSubComment = countSubComment;
	}

	public int getReportCount() {
		return reportCount;
	}

	public void setReportCount(int reportCount) {
		this.reportCount = reportCount;
	}

	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	@Override
	public String toString() {
		return "CommentQnaBoard [boardId=" + boardId + ", commentId=" + commentId + ", memberId=" + memberId
				+ ", substance=" + substance + ", refCommentId=" + refCommentId + ", countSubComment=" + countSubComment
				+ ", reportCount=" + reportCount + ", registDate=" + registDate + ", lastUpdateDate=" + lastUpdateDate
				+ "]";
	}

    
}//
