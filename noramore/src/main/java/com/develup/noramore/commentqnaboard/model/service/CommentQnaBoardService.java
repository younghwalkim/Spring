package com.develup.noramore.commentqnaboard.model.service;

import java.util.ArrayList;

import com.develup.noramore.commentqnaboard.model.vo.CommentQnaBoard;

public interface CommentQnaBoardService {
	int insertQnaComment(CommentQnaBoard commentQnaBoard);

	ArrayList<CommentQnaBoard> selectQnaComment(int boardId);
		
	int deleteComment(CommentQnaBoard commentQnaBoard);

	int updateComment(CommentQnaBoard commentQnaBoard);
	
	void deleteBoardComment(int boardId);

	int insertQnaCocomment(CommentQnaBoard commentQnaBoard);

	int deleteQnaComment(CommentQnaBoard commentQnaBoard);

	void deleteQnaSubComment(CommentQnaBoard commentQnaBoard);
}
