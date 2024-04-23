package com.develup.noramore.freeboard.model.service;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develup.noramore.commentfreeboard.model.vo.CommentFreeBoard;
import com.develup.noramore.common.Search;
import com.develup.noramore.freeboard.model.dao.FreeBoardDao;
import com.develup.noramore.freeboard.model.vo.FreeBoard;

@Service("freeBoardService")
public class FreeBoardServiceImpl implements FreeBoardService{
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	@Autowired
	FreeBoardDao freeBoardDao;
	
	
	
	
	@Override
	public ArrayList<FreeBoard> selectFreeBoard() {
		List<FreeBoard> list = sqlSessionTemplate.selectList("freeboard.selectFreeBoard");
		return (ArrayList<FreeBoard>)list;
	}
	
	@Override
	public void insertFreeBoard(FreeBoard freeBoard) {
		
		
	}

	@Override
	public FreeBoard selectBoardId(int boardId) {
		// TODO Auto-generated method stub
		return freeBoardDao.selectBoardId(boardId);
	}

	@Override
	public int selectListcount(int categoryId) {
		// TODO Auto-generated method stub
		return freeBoardDao.selectListcount(categoryId);
	}

	@Override
	public ArrayList<FreeBoard> selectSearchList(Search search) {
		// TODO Auto-generated method stub
		return freeBoardDao.selectSearchList(search);
	}

	@Override
	public int searchTitleCount(Search search) {
		// TODO Auto-generated method stub
		return freeBoardDao.searchTitleCount(search);
	}

	@Override
	public ArrayList<FreeBoard> selectSearchTitle(Search search) {
		// TODO Auto-generated method stub
		return freeBoardDao.selectSearchTitle(search);
	}

	@Override
	public int selectSearchWriterCount(Search search) {
		// TODO Auto-generated method stub
		return freeBoardDao.selectSearchWriterCount(search);
	}

	@Override
	public ArrayList<FreeBoard> selectSearchWriter(Search search) {
		// TODO Auto-generated method stub
		return freeBoardDao.selectSearchWriter(search);
	}

	@Override
	public int insertOriginBoard(FreeBoard freeBoard) {
		// TODO Auto-generated method stub
		return freeBoardDao.insertOriginBoard(freeBoard);
	}

	@Override
	public int updateAddReadCount(int boardId) {
		// TODO Auto-generated method stub
		return freeBoardDao.updateAddReadCount(boardId);
	}

	@Override
	public int deleteBoard(int boardId) {
		// TODO Auto-generated method stub
		return freeBoardDao.deleteBoard(boardId);
	}

	@Override
	public int updateOrigin(FreeBoard freeBoard) {
		// TODO Auto-generated method stub
		return freeBoardDao.updateOrigin(freeBoard);
	}

	@Override
	public FreeBoard selectBoard(int boardId) {
		// TODO Auto-generated method stub
		return freeBoardDao.selectBoard(boardId);
	}

	@Override
	public ArrayList<FreeBoard> selectViewsList(Search search) {
		return freeBoardDao.selectViewsList(search);
	}

	@Override
	public ArrayList<FreeBoard> selectRecentList(Search search) {
		
		return freeBoardDao.selectRecentList(search);
	}
	
	@Override
	public ArrayList<FreeBoard> selectLikesList(Search search) {
		// TODO Auto-generated method stub
		return freeBoardDao.selectLikesList(search);
	}


	@Override
	public int selectViewsListCount(int categoryId) {
		// TODO Auto-generated method stub
		return freeBoardDao.selectViewsListCount(categoryId);
	}
	
	@Override
	public int selectLikesListCount(int categoryId) {
		// TODO Auto-generated method stub
		return freeBoardDao.selectLikesListCount(categoryId);
	}

	@Override
	public int selectRecentListCount(int categoryId) {
		// TODO Auto-generated method stub
		return freeBoardDao.selectRecentListCount(categoryId);
	}

	@Override
    public void incrementReportCount(int boardId) {
		freeBoardDao.incrementReportCount(boardId);
    }

	@Override
	public void incrementLikeCount(int boardId) {
		freeBoardDao.incrementLikeCount(boardId);
		
	}

	@Override
	public void saveContext(String context) {
		// TODO Auto-generated method stub
		freeBoardDao.saveContent(context);
	}

	@Override
	public int upCountComment(int boardId) {
		// TODO Auto-generated method stub
		return freeBoardDao.upCountComment(boardId);
	}

	@Override
	public int downCount(int boardId) {
		// TODO Auto-generated method stub
		return freeBoardDao.downCount(boardId);
	}

	@Override
	public void countcoment(CommentFreeBoard commentFreeBoard) {
		// TODO Auto-generated method stub
		freeBoardDao.countcoment(commentFreeBoard);
		
	}

	@Override
	public ArrayList<FreeBoard> selectfreeBoardId(String memberid) {
		// TODO Auto-generated method stub
		return freeBoardDao.selectfreeBoardId(memberid);
	}

	
	
	

	

	

	

	
	
	

	
	
}
