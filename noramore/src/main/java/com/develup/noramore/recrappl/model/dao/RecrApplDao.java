package com.develup.noramore.recrappl.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.develup.noramore.recrappl.model.vo.RecrAppl;

@Repository
public class RecrApplDao {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public int insertAppl(RecrAppl recrAppl) {
		return sqlSessionTemplate.insert("recrappl.insertAppl", recrAppl);
	}


	public int searchAppl(RecrAppl recrAppl) {
		return sqlSessionTemplate.selectOne("recrappl.searchAppl", recrAppl);
	}


	public ArrayList<RecrAppl> selectBoardId(int boardId) {
		List<RecrAppl> list = sqlSessionTemplate.selectList("recrappl.selectBoardId", boardId);
		return (ArrayList<RecrAppl>)list;
	}


	public int cancelAppl(RecrAppl recrAppl) {
		return sqlSessionTemplate.update("recrappl.cancelAppl", recrAppl);
	}


	public int applyAppl(RecrAppl recrAppl) {
		return sqlSessionTemplate.update("recrappl.applyAppl", recrAppl);
	}

}//
