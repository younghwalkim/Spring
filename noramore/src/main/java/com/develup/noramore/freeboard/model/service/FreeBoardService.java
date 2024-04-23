package com.develup.noramore.freeboard.model.service;

import java.util.ArrayList;

import com.develup.noramore.commentfreeboard.model.vo.CommentFreeBoard;
import com.develup.noramore.common.Paging;
import com.develup.noramore.common.Search;
import com.develup.noramore.freeboard.model.vo.FreeBoard;

public interface FreeBoardService {

	ArrayList<FreeBoard> selectFreeBoard();

	
	void insertFreeBoard(FreeBoard freeBoard);


	FreeBoard selectBoardId(int boardId);


	int selectListcount(int categoryId);


	ArrayList<FreeBoard> selectSearchList(Search search);


	int searchTitleCount(Search search);


	ArrayList<FreeBoard> selectSearchTitle(Search search);


	int selectSearchWriterCount(Search search);


	ArrayList<FreeBoard> selectSearchWriter(Search search);


	int insertOriginBoard(FreeBoard freeBoard);


	int updateAddReadCount(int boardId);


	int deleteBoard(int boardId);


	int updateOrigin(FreeBoard freeBoard);


	FreeBoard selectBoard(int boardId);


	ArrayList<FreeBoard> selectViewsList(Search search);


	ArrayList<FreeBoard> selectRecentList(Search search);
	
	
	ArrayList<FreeBoard> selectLikesList(Search search);


	int selectViewsListCount(int categoryId);


	int selectRecentListCount(int categoryId);
	
	
	int selectLikesListCount(int categoryId);


	void incrementReportCount(int boardId);


	void incrementLikeCount(int boardId);


	void saveContext(String context);


	int upCountComment(int boardId);


	int downCount(int boardId);


	void countcoment(CommentFreeBoard commentFreeBoard);


	ArrayList<FreeBoard> selectfreeBoardId(String memberid);

	


	


	


	


	


	

	
	


	
	
	
	
	

}
