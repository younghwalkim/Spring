package com.develup.noramore.commentqnaboard.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develup.noramore.commentqnaboard.model.dao.CommentQnaBoardDao;
import com.develup.noramore.commentqnaboard.model.vo.CommentQnaBoard;

@Service("commentQnaBoardService")
public class CommentQnaBoardServiceImpl implements CommentQnaBoardService {
	
	@Autowired
	CommentQnaBoardDao commentQnaBoardDao;
	
	@Override
	public ArrayList<CommentQnaBoard> selectQnaComment(int boardId) {
		return commentQnaBoardDao.selectQnaComment(boardId);
	}

	@Override
	public int deleteComment(CommentQnaBoard commentQnaBoard) {
		return commentQnaBoardDao.deleteComment(commentQnaBoard);
	}
	
	@Override
	public int updateComment(CommentQnaBoard commentQnaBoard) {
		return commentQnaBoardDao.updateComment(commentQnaBoard);
	}
	
	@Override
	public int insertQnaComment(CommentQnaBoard commentQnaBoard) {
		return commentQnaBoardDao.insertQnaComment(commentQnaBoard);
	}
	
	@Override
	public void deleteBoardComment(int boardId) {
		commentQnaBoardDao.deleteBoardComment(boardId);
		
	}

	@Override
	public int insertQnaCocomment(CommentQnaBoard commentQnaBoard) {
		return commentQnaBoardDao.insertQnaCocomment(commentQnaBoard);
	}

	@Override
	public int deleteQnaComment(CommentQnaBoard commentQnaBoard) {
		return commentQnaBoardDao.deleteQnaComment(commentQnaBoard);
	}

	@Override
	public void deleteQnaSubComment(CommentQnaBoard commentQnaBoard) {
		commentQnaBoardDao.deleteQnaSubComment(commentQnaBoard);
	}
}
