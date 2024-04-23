package com.project.getdrive.board.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.getdrive.board.model.vo.Board;
import com.project.getdrive.common.Paging;
import com.project.getdrive.common.Search;
import com.project.getdrive.common.SearchDate;
import com.project.getdrive.common.SearchPaging;

@Repository("boardDao")
public class BoardDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public ArrayList<Board> selectList(Paging	paging) {
		List<Board> list = sqlSessionTemplate.selectList("boardMapper.selectList", paging);
		
		return (ArrayList<Board>) list;
	}
	
	public int selectListCount(int btId) {
		return sqlSessionTemplate.selectOne("boardMapper.selectListCount", btId);
	}

	public int insertOriginBoard(Board board) {
		return sqlSessionTemplate.insert("boardMapper.insertOriginBoard", board);
	}

	public int updateBoard(Board board) {
		return sqlSessionTemplate.update("boardMapper.updateBoard", board);
	}

	public Board selectBoard(int boardNo) {
		return sqlSessionTemplate.selectOne("boardMapper.selectBoard", boardNo);
	}

	public int boardOriginUpdate(Board board) {
		return sqlSessionTemplate.update("boardMapper.boardOriginUpdate", board);
	}

	public int deleteBoard(Board board) {
		return sqlSessionTemplate.delete("boardMapper.deleteBoard", board);
	}

	public int selectSearchTitleCount(String keyword) {
		return sqlSessionTemplate.selectOne("boardMapper.selectSearchTitleCount", keyword);
	}
	
	public int selectSearchWriterCount(String keyword) {
		return sqlSessionTemplate.selectOne("boardMapper.selectSearchWriterCount", keyword) ;
	}
	
	public int selectSearchDateCount(SearchDate searchDate) {
		return sqlSessionTemplate.selectOne("boardMapper.selectSearchDateCount", searchDate);
	}

	public ArrayList<Board> selectSearchTitle(SearchPaging search) {
		List<Board> list = sqlSessionTemplate.selectList("boardMapper.selectSearchTitle", search);
		return (ArrayList<Board>) list;
	}

	public ArrayList<Board> selectSearchWriter(SearchPaging search) {
		List<Board> list = sqlSessionTemplate.selectList("boardMapper.selectSearchWriter", search);
		return (ArrayList<Board>) list;
	}

	public ArrayList<Board> selectSearchDate(SearchPaging search) {
		List<Board> list = sqlSessionTemplate.selectList("boardMapper.selectSearchDate", search);
		return (ArrayList<Board>) list;
	}




}
