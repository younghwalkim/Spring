package com.develup.noramore.qna.model.service;

import java.util.ArrayList;

import com.develup.noramore.commentqnaboard.model.vo.CommentQnaBoard;
import com.develup.noramore.commentrecrboard.model.vo.CommentRecrBoard;
import com.develup.noramore.common.Paging;
import com.develup.noramore.common.Search;
import com.develup.noramore.common.SearchDate;
import com.develup.noramore.qna.model.vo.Qna;

public interface QnaService {
	
	Qna selectOne(int boardID);

	ArrayList<Qna> selectList(Paging paging);

	void updateAddReadCount(int boardID);

	int insertQna(Qna qna);

	int updateQna(Qna qna);

	int deleteQna(int boardID);

	 ArrayList<Qna> selectSearchTitle(Search search);
	 ArrayList<Qna> selectSearchTitle(String keyword);
	 ArrayList<Qna> selectSearchContent(Search search);
	 ArrayList<Qna> selectSearchDate(Search search);

	 int selectListCount();
	 int selectSearchTitleCount(String keyword);
	 int selectSearchContentCount(String keyword);
	 int selectSearchDateCount(SearchDate date);
	 
	 int downCount(int boardId);

	void countcoment(CommentQnaBoard commentQnaBoard);

	int countComment(int boardId);
}
