package com.develup.noramore.commentrecrboard.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develup.noramore.commentrecrboard.model.dao.CommentRecrBoardDao;
import com.develup.noramore.commentrecrboard.model.vo.CommentRecrBoard;

@Service("commentRecrBoardService")
public class CommentRecrBoardServiceImpl implements CommentRecrBoardService{
	@Autowired
	CommentRecrBoardDao commentRecrBoardDao;

	@Override
	public int insertRecrComment(CommentRecrBoard commentRecrBoard) {
		return commentRecrBoardDao.insertRecrComment(commentRecrBoard);
	}

	@Override
	public ArrayList<CommentRecrBoard> selectRecrComment(int boardId) {
		return commentRecrBoardDao.selectRecrComment(boardId);
	}

	@Override
	public void upcountcocoment(CommentRecrBoard commentRecrBoard) {
		commentRecrBoardDao.upcountcocoment(commentRecrBoard);
	}

	@Override
	public ArrayList<CommentRecrBoard> selectRecrCocomment(CommentRecrBoard commentRecrBoard) {
		return commentRecrBoardDao.selectRecrCocomment(commentRecrBoard);
	}

	@Override
	public int deleteComment(CommentRecrBoard commentRecrBoard) {
		return commentRecrBoardDao.deleteComment(commentRecrBoard);
	}

	@Override
	public int updateComment(CommentRecrBoard commentRecrBoard) {
		return commentRecrBoardDao.updateComment(commentRecrBoard);
	}

	@Override
	public void downcountcocoment(CommentRecrBoard commentRecrBoard) {
		commentRecrBoardDao.downcountcoment(commentRecrBoard);
	}

	@Override
	public void deleteSubComment(CommentRecrBoard commentRecrBoard) {
		commentRecrBoardDao.deleteSubComment(commentRecrBoard);
	}

	@Override
	public void deleteBoardComment(int boardId) {
		commentRecrBoardDao.deleteBoardComment(boardId);
		
	}



	
}//
