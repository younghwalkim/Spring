package com.develup.noramore.commentfreeboard.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.develup.noramore.commentfreeboard.model.vo.CommentFreeBoard;

@Repository("commentFreeBoardDao")
public class CommentFreeBoardDao {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	// 수정 해야함
	public int insertFreeComment(CommentFreeBoard commentFreeBoard) {
		return sqlSessionTemplate.insert("commentfreeboard.insertFreeComment", commentFreeBoard);
	}
	
	public ArrayList<CommentFreeBoard> selectFreeComment(int boardId) {
		List<CommentFreeBoard> list = sqlSessionTemplate.selectList("commentfreeboard.selectFreeComment", boardId);
		return (ArrayList<CommentFreeBoard>)list;
	}

	public void upcountcocoment(CommentFreeBoard commentFreeBoard) {
		sqlSessionTemplate.update("commentfreeboard.upcountcocoment", commentFreeBoard);
	}

	public void downcountcoment(CommentFreeBoard commentFreeBoard) {
		sqlSessionTemplate.update("commentfreeboard.downcountcoment", commentFreeBoard);
	}
	
	public ArrayList<CommentFreeBoard> selectFreeCocomment(CommentFreeBoard commentFreeBoard) {
		List<CommentFreeBoard> list = sqlSessionTemplate.selectList("commentfreeboard.selectFreeCocomment", commentFreeBoard);
		return (ArrayList<CommentFreeBoard>)list;
	}


	public int deleteComment(CommentFreeBoard commentFreeBoard) {
		return sqlSessionTemplate.delete("commentfreeboard.deleteComment", commentFreeBoard);
	}

	public void deleteSubComment(CommentFreeBoard commentFreeBoard) {
		sqlSessionTemplate.delete("commentfreeboard.deleteSubComment", commentFreeBoard);
	}
	
	public int updateComment(CommentFreeBoard commentFreeBoard) {
		return sqlSessionTemplate.update("commentfreeboard.updateComment", commentFreeBoard);
	}

	public void deleteBoardComment(int boardId) {
		sqlSessionTemplate.delete("commentfreeboard.deleteBoardComment", boardId);
	}


	
	
	

}
