package com.develup.noramore.recrservice.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.develup.noramore.commentrecrboard.model.vo.CommentRecrBoard;
import com.develup.noramore.common.Search;
import com.develup.noramore.recrboard.model.vo.RecrBoard;

@Repository("recrBoardDao")
public class RecreBoardDao {
	@Autowired
	SqlSessionTemplate SqlSessionTemplate;

	public ArrayList<RecrBoard> selectRecrBoard() {
		List<RecrBoard> list = SqlSessionTemplate.selectList("recrboard.selectRecrBoard");
		return (ArrayList<RecrBoard>) list;
	}

	public RecrBoard selectBoardId(int boardId) {
		return SqlSessionTemplate.selectOne("recrboard.selectBoardId", boardId);
	}

	public int selectListcount(int categoryId) {
		return SqlSessionTemplate.selectOne("recrboard.selectListcount", categoryId);
	}

	public ArrayList<RecrBoard> selectSearchList(Search search) {
		List<RecrBoard> list = SqlSessionTemplate.selectList("recrboard.selectSearchList", search);
		return (ArrayList<RecrBoard>) list;
	}

	public int insertRecrBoard(RecrBoard recrBoard) {
		return SqlSessionTemplate.insert("recrboard.insertRecrBoard", recrBoard);
	}

	public int upNowRecr(int boardId) {
		return SqlSessionTemplate.update("recrboard.upNowRecr", boardId);
	}

	public int upCountComment(int boardId) {
		return SqlSessionTemplate.update("recrboard.upCountComment", boardId);
	}

	public int updateBoard(RecrBoard recrBoard) {
		return SqlSessionTemplate.update("recrboard.updateBoard", recrBoard);
	}

	public int downCount(int boardId) {
		return SqlSessionTemplate.update("recrboard.upCountComment", boardId);
	}

	public void countcoment(CommentRecrBoard commentRecrBoard) {
		SqlSessionTemplate.update("recrboard.countcoment", commentRecrBoard);
	}

	public int deleteBoard(int boardId) {
		return SqlSessionTemplate.delete("recrboard.deleteBoard", boardId);
	}

	public int searchtitlecount(Search search) {
		return SqlSessionTemplate.selectOne("recrboard.searchtitlecount", search);
	}

	public ArrayList<RecrBoard> searchtitleList(Search search) {
		List<RecrBoard> list = SqlSessionTemplate.selectList("recrboard.searchtitleList", search);
		return (ArrayList<RecrBoard>) list;
	}

	public int searchwritercount(Search search) {
		return SqlSessionTemplate.selectOne("recrboard.searchwritercount", search);
	}

	public ArrayList<RecrBoard> searchwriterList(Search search) {
		List<RecrBoard> list = SqlSessionTemplate.selectList("recrboard.searchwriterList", search);
		return (ArrayList<RecrBoard>) list;
	}

	public void upReadCount(int boardId) {
		SqlSessionTemplate.update("recrboard.upReadCount", boardId);
	}

	public ArrayList<RecrBoard> selectLocation(int categoryId) {
		List<RecrBoard> list = SqlSessionTemplate.selectList("recrboard.selectLocation", categoryId);
		return (ArrayList<RecrBoard>) list;
	}

	public int boardReport(int boardId) {
		return SqlSessionTemplate.update("recrboard.boardReport", boardId);
	}

	public int countAppl(int boardId) {
		return SqlSessionTemplate.update("recrboard.countAppl", boardId);
	}

	public int closerecr(int boardId) {
		return SqlSessionTemplate.update("recrboard.closerecr", boardId);
	}

	public ArrayList<RecrBoard> selectRecrBoardId(String memberid) {
		List<RecrBoard> list = SqlSessionTemplate.selectList("recrboard.selectRecrBoardId", memberid);
		return (ArrayList<RecrBoard>) list;
	}

}//
