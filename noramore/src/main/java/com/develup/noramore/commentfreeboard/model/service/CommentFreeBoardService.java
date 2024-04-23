package com.develup.noramore.commentfreeboard.model.service;

import java.util.ArrayList;

import com.develup.noramore.commentfreeboard.model.vo.CommentFreeBoard;

public interface CommentFreeBoardService {
	
	int insertFreeComment(CommentFreeBoard commentFreeBoard);

	ArrayList<CommentFreeBoard> selectFreeComment(int boardId);

	void upcountcocoment(CommentFreeBoard commentFreeBoard);
	
	void downcountcocoment(CommentFreeBoard commentFreeBoard);

	ArrayList<CommentFreeBoard> selectFreeCocomment(CommentFreeBoard commentFreeBoard);

	int deleteComment(CommentFreeBoard commentFreeBoard);

	int updateComment(CommentFreeBoard commentFreeBoard);

	void deleteSubComment(CommentFreeBoard commentFreeBoard);

	void deleteBoardComment(int boardId);
}
