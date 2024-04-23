package com.develup.noramore.qna.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develup.noramore.commentqnaboard.model.vo.CommentQnaBoard;
import com.develup.noramore.common.Paging;
import com.develup.noramore.common.Search;
import com.develup.noramore.common.SearchDate;
import com.develup.noramore.qna.model.dao.QnaDao;
import com.develup.noramore.qna.model.vo.Qna;

@Service("qnaService")
public class QnaServiceImpl implements QnaService {
	@Autowired
	public  QnaDao qnaDao;

	@Override
	public int selectListCount() {
		// TODO Auto-generated method stub
		return qnaDao.selectListCount();
	}

	@Override
	public Qna selectOne(int boardID) {
		// TODO Auto-generated method stub
		return qnaDao.selectOne(boardID);
	}

	@Override
	public ArrayList<Qna> selectList(Paging paging) {
		// TODO Auto-generated method stub
		return qnaDao.selectList(paging);
	}

	@Override
	public void updateAddReadCount(int boardID) {
		// TODO Auto-generated method stub
		qnaDao.addReadCount(boardID);
	}

	@Override
	public int insertQna(Qna qna) {
		// TODO Auto-generated method stub
		return qnaDao.insertQna(qna);
	}

	@Override
	public int deleteQna(int boardID) {
		// TODO Auto-generated method stub
		return qnaDao.deleteQna(boardID);
	}

	@Override
	public int updateQna(Qna qna) {
		// TODO Auto-generated method stub
		return qnaDao.updateQna(qna);
	}

	@Override
	public ArrayList<Qna> selectSearchTitle(Search search) {
		// TODO Auto-generated method stub
		return qnaDao.selectSearchTitle(search);
	}

	@Override
	public ArrayList<Qna> selectSearchTitle(String keyword) {
		// TODO Auto-generated method stub
		return qnaDao.selectSearchTitle(keyword);
	}

	@Override
	public ArrayList<Qna> selectSearchContent(Search search) {
		// TODO Auto-generated method stub
		return qnaDao.selectSearchContent(search);
	}

	@Override
	public ArrayList<Qna> selectSearchDate(Search search) {
		// TODO Auto-generated method stub
		return qnaDao.selectSearchDate(search);
	}

	@Override
	public int selectSearchTitleCount(String keyword) {
		// TODO Auto-generated method stub
		return qnaDao.selectSearchTitleCount(keyword);
	}

	@Override
	public int selectSearchContentCount(String keyword) {
		// TODO Auto-generated method stub
		return qnaDao.selectSearchContentCount(keyword);
	}

	@Override
	public int selectSearchDateCount(SearchDate date) {
		// TODO Auto-generated method stub
		return qnaDao.selectSearchDateCount(date);
	}


	@Override
	public int downCount(int boardId) {
		// TODO Auto-generated method stub
		return qnaDao.downCount(boardId);
	}
	
	@Override
	public void countcoment(CommentQnaBoard commentQnaBoard) {
		qnaDao.countcoment(commentQnaBoard);
	}

	@Override
	public int countComment(int boardId) {
		return qnaDao.countComment(boardId);
	}
}
