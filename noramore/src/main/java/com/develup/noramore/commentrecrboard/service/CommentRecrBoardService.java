package com.develup.noramore.commentrecrboard.service;

import java.util.ArrayList;

import com.develup.noramore.commentrecrboard.model.vo.CommentRecrBoard;

public interface CommentRecrBoardService {

	int insertRecrComment(CommentRecrBoard commentRecrBoard);

	ArrayList<CommentRecrBoard> selectRecrComment(int boardId);

	void upcountcocoment(CommentRecrBoard commentRecrBoard);
	
	void downcountcocoment(CommentRecrBoard commentRecrBoard);

	ArrayList<CommentRecrBoard> selectRecrCocomment(CommentRecrBoard commentRecrBoard);

	int deleteComment(CommentRecrBoard commentRecrBoard);

	int updateComment(CommentRecrBoard commentRecrBoard);

	void deleteSubComment(CommentRecrBoard commentRecrBoard);

	void deleteBoardComment(int boardId);


}
