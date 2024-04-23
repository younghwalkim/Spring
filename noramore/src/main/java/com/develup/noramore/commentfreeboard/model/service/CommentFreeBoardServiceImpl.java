package com.develup.noramore.commentfreeboard.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develup.noramore.commentfreeboard.model.dao.CommentFreeBoardDao;
import com.develup.noramore.commentfreeboard.model.vo.CommentFreeBoard;

@Service("commentFreeBoardSevice")
public class CommentFreeBoardServiceImpl implements CommentFreeBoardService {
	
	@Autowired
	CommentFreeBoardDao commentFreeBoardDao;

	@Override
	public int insertFreeComment(CommentFreeBoard commentFreeBoard) {
		return commentFreeBoardDao.insertFreeComment(commentFreeBoard);
	}

	@Override
	public ArrayList<CommentFreeBoard> selectFreeComment(int boardId) {
		return commentFreeBoardDao.selectFreeComment(boardId);
	}

	@Override
	public void upcountcocoment(CommentFreeBoard commentFreeBoard) {
		commentFreeBoardDao.upcountcocoment(commentFreeBoard);
	}

	@Override
	public ArrayList<CommentFreeBoard> selectFreeCocomment(CommentFreeBoard commentFreeBoard) {
		return commentFreeBoardDao.selectFreeCocomment(commentFreeBoard);
	}

	@Override
	public int deleteComment(CommentFreeBoard commentFreeBoard) {
		return commentFreeBoardDao.deleteComment(commentFreeBoard);
	}

	@Override
	public int updateComment(CommentFreeBoard commentFreeBoard) {
		return commentFreeBoardDao.updateComment(commentFreeBoard);
	}

	@Override
	public void downcountcocoment(CommentFreeBoard commentFreeBoard) {
		commentFreeBoardDao.downcountcoment(commentFreeBoard);
	}

	@Override
	public void deleteSubComment(CommentFreeBoard commentFreeBoard) {
		commentFreeBoardDao.deleteSubComment(commentFreeBoard);
	}

	@Override
	public void deleteBoardComment(int boardId) {
		commentFreeBoardDao.deleteBoardComment(boardId);
		
	}

	
}
