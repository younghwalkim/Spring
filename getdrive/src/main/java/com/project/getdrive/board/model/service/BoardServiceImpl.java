package com.project.getdrive.board.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.getdrive.board.model.dao.BoardDao;
import com.project.getdrive.board.model.vo.Board;
import com.project.getdrive.common.Paging;
import com.project.getdrive.common.Search;
import com.project.getdrive.common.SearchDate;
import com.project.getdrive.common.SearchPaging;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDao boardDao;

	@Override
	public ArrayList<Board> selectList(Paging paging) {
		return boardDao.selectList(paging);
	}
	
	@Override
	public int selectListCount(int btId) {
		return boardDao.selectListCount(btId);
	}

	@Override
	public int insertOriginBoard(Board board) {
		return boardDao.insertOriginBoard(board);
	}

	@Override
	public int updateBoard(Board board) {
		return boardDao.updateBoard(board);
	}

	@Override
	public Board selectBoard(int boardNo) {
		return boardDao.selectBoard(boardNo);
	}

	@Override
	public int boardOriginUpdate(Board board) {
		return boardDao.boardOriginUpdate(board);
	}

	@Override
	public int deleteBoard(Board board) {
		return boardDao.deleteBoard(board);
	}
	
	
    //검색 파트
	
	@Override
	public int selectSearchTitleCount(String keyword) {
		return boardDao.selectSearchTitleCount(keyword);
	}
	
	@Override
	public int selectSearchWriterCount(String keyword) {
		return boardDao.selectSearchWriterCount(keyword);
	}
	
	@Override
	public int selectSearchDateCount(SearchDate searchDate) {
		return boardDao.selectSearchDateCount(searchDate);
	}

	@Override
	public ArrayList<Board> selectSearchTitle(SearchPaging search) {
		return boardDao.selectSearchTitle(search);
	}

	@Override
	public ArrayList<Board> selectSearchWriter(SearchPaging search) {
		return boardDao.selectSearchWriter(search);
	}

	@Override
	public ArrayList<Board> selectSearchDate(SearchPaging search) {
		return boardDao.selectSearchDate(search);
	}





	
}
