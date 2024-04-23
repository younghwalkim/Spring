package com.develup.noramore.recrboard.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develup.noramore.commentrecrboard.model.vo.CommentRecrBoard;
import com.develup.noramore.common.Search;
import com.develup.noramore.recrboard.model.vo.RecrBoard;
import com.develup.noramore.recrservice.model.dao.RecreBoardDao;

@Service("recrBoardSevice")
public class RecreBoardServiceImpl implements RecrBoardService {
	@Autowired
	RecreBoardDao recreBoardDao;

	@Override
	public ArrayList<RecrBoard> selectRecrBoard() {
		return recreBoardDao.selectRecrBoard();
	}

	@Override
	public RecrBoard selectBoardId(int boardId) {
		return recreBoardDao.selectBoardId(boardId);
	}

	@Override
	public int selectListcount(int categoryId) {
		return recreBoardDao.selectListcount(categoryId);
	}

	@Override
	public ArrayList<RecrBoard> selectSearchList(Search search) {
		return recreBoardDao.selectSearchList(search);
	}

	@Override
	public int insertRecrBoard(RecrBoard recrBoard) {
		return recreBoardDao.insertRecrBoard(recrBoard);
	}

	@Override
	public int upNowRecr(int boardId) {
		return recreBoardDao.upNowRecr(boardId);
	}

	@Override
	public int upCountComment(int boardId) {
		return recreBoardDao.upCountComment(boardId);
	}

	@Override
	public int updateBoard(RecrBoard recrBoard) {
		return recreBoardDao.updateBoard(recrBoard);
	}

	@Override
	public int downCount(int boardId) {
		return recreBoardDao.downCount(boardId);
	}

	@Override
	public void countcoment(CommentRecrBoard commentRecrBoard) {
		recreBoardDao.countcoment(commentRecrBoard);
	}

	@Override
	public int deleteBoard(int boardId) {
		return recreBoardDao.deleteBoard(boardId);
	}

	@Override
	public int searchtitlecount(Search serach) {
		return recreBoardDao.searchtitlecount(serach);
	}

	@Override
	public ArrayList<RecrBoard> searchtitleList(Search search) {
		return recreBoardDao.searchtitleList(search);
	}

	@Override
	public int searchwritercount(Search search) {
		return recreBoardDao.searchwritercount(search);
	}

	@Override
	public ArrayList<RecrBoard> searchwriterList(Search search) {
		return recreBoardDao.searchwriterList(search);
	}

	@Override
	public void upReadCount(int boardId) {
		recreBoardDao.upReadCount(boardId);
	}

	@Override
	public ArrayList<RecrBoard> selectLocation(int categoryId) {
		return recreBoardDao.selectLocation(categoryId);
	}

	@Override
	public int boardReport(int boardId) {
		return recreBoardDao.boardReport(boardId);
	}

	@Override
	public int countAppl(int boardId) {
		return recreBoardDao.countAppl(boardId);
	}

	@Override
	public int closerecr(int boardId) {
		return recreBoardDao.closerecr(boardId);
	}

	@Override
	public ArrayList<RecrBoard> selectRecrBoardId(String memberid) {
		return recreBoardDao.selectRecrBoardId(memberid);
	}

}//
