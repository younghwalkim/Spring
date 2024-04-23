package com.develup.noramore.qna.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.develup.noramore.commentqnaboard.model.vo.CommentQnaBoard;
import com.develup.noramore.common.Paging;
import com.develup.noramore.common.Search;
import com.develup.noramore.common.SearchDate;
import com.develup.noramore.qna.model.vo.Qna;

@Repository("qnaDao")
public class QnaDao {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public void countcoment(CommentQnaBoard commentQnaBoard) {
		sqlSessionTemplate.update("qnaMapper.countcoment", commentQnaBoard);
	}	
	
	public int selectListCount() {		
		return sqlSessionTemplate.selectOne("qnaMapper.selectListCount");
	}

	public ArrayList<Qna> selectList(Paging paging) {		
		List<Qna> list = sqlSessionTemplate.selectList("qnaMapper.selectList", paging);
		return (ArrayList<Qna>)list;
	}

	public Qna selectOne(int boardID) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("qnaMapper.selectOne",boardID);
	}
	
	public int addReadCount(int boardID) {
		return sqlSessionTemplate.update("qnaMapper.addReadCount", boardID);
		
	}
	
	//새 공지글 등록
	public int insertQna(Qna qna) {
		return sqlSessionTemplate.insert("qnaMapper.insertQna", qna);
	}
	
	//공지글 수정
	public int updateQna(Qna qna) {
		return sqlSessionTemplate.update("qnaMapper.updateQna", qna);
	}
	
	//공지글 삭제
	public int deleteQna(int qnaNo) {
		return sqlSessionTemplate.delete("qnaMapper.deleteQna", qnaNo);
	}

	public ArrayList<Qna> selectSearchTitle(Search search) {
		List<Qna> list = sqlSessionTemplate.selectList("qnaMapper.selectSearchTitle", search);
		return (ArrayList<Qna>)list;
	}

	public ArrayList<Qna> selectSearchTitle(String keyword) {
		// TODO Auto-generated method stub
		List<Qna> list = sqlSessionTemplate.selectList("qnaMapper.selectSearchTitleKeyword", keyword);
		return (ArrayList<Qna>)list;
	}

	public ArrayList<Qna> selectSearchContent(Search search) {
		List<Qna> list = sqlSessionTemplate.selectList("qnaMapper.selectSearchContent", search);
		return (ArrayList<Qna>)list;
	}

	public ArrayList<Qna> selectSearchDate(Search search) {
		List<Qna> list = sqlSessionTemplate.selectList("qnaMapper.selectSearchDate", search);
		return (ArrayList<Qna>)list;
	}

	public int selectSearchTitleCount(String keyword) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("qnaMapper.selectSearchTitleCount", keyword);
	}

	public int selectSearchContentCount(String keyword) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("qnaMapper.selectSearchContentCount", keyword);
	}

	public int selectSearchDateCount(SearchDate date) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("qnaMapper.selectSearchDateCount", date);
	}

	public int downCount(int boardId) {
		return 0;
	}

	public int countComment(int boardId) {
		return sqlSessionTemplate.update("qnaMapper.countComment", boardId);
	}


}
