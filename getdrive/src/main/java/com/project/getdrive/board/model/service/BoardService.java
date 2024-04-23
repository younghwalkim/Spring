package com.project.getdrive.board.model.service;

import java.util.ArrayList;

import com.project.getdrive.board.model.vo.Board;
import com.project.getdrive.common.Paging;
import com.project.getdrive.common.Search;
import com.project.getdrive.common.SearchDate;
import com.project.getdrive.common.SearchPaging;

public interface BoardService {

	ArrayList<Board> selectList(Paging paging);

	int insertOriginBoard(Board board);

	int updateBoard(Board board);

	Board selectBoard(int boardNo);

	int boardOriginUpdate(Board board);

	int deleteBoard(Board board);

	int selectListCount(int btId);

	int selectSearchTitleCount(String keyword);
	
	int selectSearchWriterCount(String keyword);
	
	int selectSearchDateCount(SearchDate searchDate);

	ArrayList<Board> selectSearchTitle(SearchPaging search);

	ArrayList<Board> selectSearchWriter(SearchPaging search);

	ArrayList<Board> selectSearchDate(SearchPaging search);







}
