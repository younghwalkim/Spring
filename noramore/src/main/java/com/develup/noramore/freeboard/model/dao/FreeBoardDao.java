package com.develup.noramore.freeboard.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.develup.noramore.commentfreeboard.model.vo.CommentFreeBoard;
import com.develup.noramore.common.Search;
import com.develup.noramore.freeboard.model.vo.FreeBoard;

@Repository("freeBoardDao")
public class FreeBoardDao {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	
	public FreeBoard selectBoardId(int boardId) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("freeboard.selectBoardId", boardId);
	}


	public int selectListcount(int categoryId) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("freeboard.selectListcount", categoryId);
	
	}


	public ArrayList<FreeBoard> selectSearchList(Search search) {
		List<FreeBoard> list = sqlSessionTemplate.selectList("freeboard.selectSearchList", search);
		return (ArrayList<FreeBoard>)list;
	}


	public int searchTitleCount(Search search) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("freeboard.searchTitleCount", search);
	}


	public ArrayList<FreeBoard> selectSearchTitle(Search search) {
		List<FreeBoard> list = sqlSessionTemplate.selectList("freeboard.selectSearchTitle", search);
		return (ArrayList<FreeBoard>)list;
	}


	public int selectSearchWriterCount(Search search) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("freeboard.selectSearchWriterCount", search);
	}


	public ArrayList<FreeBoard> selectSearchWriter(Search search) {
		List<FreeBoard> list = sqlSessionTemplate.selectList("freeboard.selectSearchWriter", search);
		return (ArrayList<FreeBoard>)list;
	}


	public int insertOriginBoard(FreeBoard freeBoard) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.insert("freeboard.insertOriginBoard", freeBoard);
	}


	public int updateAddReadCount(int boardId) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.update("freeboard.updateAddReadCount", boardId);
	}


	public int deleteBoard(int boardId) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.delete("freeboard.deleteBoard", boardId);
	}


	public int updateOrigin(FreeBoard freeBoard) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.update("freeboard.updateOrigin", freeBoard);
	}


	public FreeBoard selectBoard(int boardId) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("freeboard.selectBoard", boardId);
	}


	public int selectViewsListCount(int categoryId) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("freeboard.selectViewsListCount", categoryId);
	}


	public ArrayList<FreeBoard> selectRecentList(Search search) {
		List<FreeBoard> list = sqlSessionTemplate.selectList("freeboard.selectRecentList", search);
		return (ArrayList<FreeBoard>)list;
	}


	public ArrayList<FreeBoard> selectViewsList(Search search) {
		List<FreeBoard> list = sqlSessionTemplate.selectList("freeboard.selectViewsList", search);
		return (ArrayList<FreeBoard>)list;
	}
	
	public ArrayList<FreeBoard> selectLikesList(Search search) {
		List<FreeBoard> list = sqlSessionTemplate.selectList("freeboard.selectLikesList", search);
		return (ArrayList<FreeBoard>)list;
	}
	
	public int selectRecentListCount(int categoryId) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("freeboard.selectRecentListCount", categoryId);
	}

	public int selectLikesListCount(int categoryId) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("freeboard.selectLikesListCount", categoryId);
	}

	public void incrementReportCount(int boardId) {
		sqlSessionTemplate.update("freeboard.incrementReportCount", boardId);
		
	}


	public void incrementLikeCount(int boardId) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.update("freeboard.incrementLikeCount", boardId);
	}


	public void saveContent(String context) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.insert("freeboard.saveContent", context);
		
	}


	public int upCountComment(int boardId) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.update("freeboard.upCountComment", boardId);
	}


	public int downCount(int boardId) {
		return sqlSessionTemplate.update("freeboard.upCountComment", boardId);
	}

	public void countcoment(CommentFreeBoard commentFreeBoard) {
		sqlSessionTemplate.update("freeboard.countcoment", commentFreeBoard);
	}


	public ArrayList<FreeBoard> selectfreeBoardId(String memberid) {
		// TODO Auto-generated method stub
		List<FreeBoard> list = sqlSessionTemplate.selectList("freeboard.selectfreeBoardId", memberid);
		return (ArrayList<FreeBoard>)list;
		
	}


	


	

	


	

	

	
	


	
	
	

	/*
	public void insertFreeBoard(FreeBoard freeBoard) {
		
		sqlSessionTemplate.insert("freeboard.insertFreeBoard", freeBoard);
		
	}
	 */
	
	
	
	
	}


