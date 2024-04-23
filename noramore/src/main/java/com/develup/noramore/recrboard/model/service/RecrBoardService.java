package com.develup.noramore.recrboard.model.service;

import java.util.ArrayList;

import com.develup.noramore.commentrecrboard.model.vo.CommentRecrBoard;
import com.develup.noramore.common.Search;
import com.develup.noramore.recrboard.model.vo.RecrBoard;

public interface RecrBoardService {

	ArrayList<RecrBoard> selectRecrBoard();

	RecrBoard selectBoardId(int boardId);

	int selectListcount(int categoryId);

	ArrayList<RecrBoard> selectSearchList(Search search);

	int insertRecrBoard(RecrBoard recrBoard);

	int upNowRecr(int boardId);

	int upCountComment(int boardId);

	int updateBoard(RecrBoard recrBoard);

	int downCount(int boardId);

	void countcoment(CommentRecrBoard commentRecrBoard);

	int deleteBoard(int boardId);

	int searchtitlecount(Search serach);

	ArrayList<RecrBoard> searchtitleList(Search search);

	int searchwritercount(Search search);

	ArrayList<RecrBoard> searchwriterList(Search search);

	void upReadCount(int boardId);

	ArrayList<RecrBoard> selectLocation(int categoryId);

	int boardReport(int boardId);

	int countAppl(int boardId);

	int closerecr(int boardId);
	
	 ArrayList<RecrBoard> selectRecrBoardId(String memberid);
	
}