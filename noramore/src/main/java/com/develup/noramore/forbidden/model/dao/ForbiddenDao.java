package com.develup.noramore.forbidden.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.develup.noramore.common.Paging;
import com.develup.noramore.common.Search;
import com.develup.noramore.forbidden.model.vo.Forbidden;

@Repository("forbiddenDao")
public class ForbiddenDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public ArrayList<Forbidden> selectList(Paging paging) {
		List<Forbidden> list = sqlSessionTemplate.selectList("forbidden.selectList", paging);
		return (ArrayList<Forbidden>)list;
	}

	public int selectListCount() {
		return sqlSessionTemplate.selectOne("forbidden.selectListCount");
	}

	public int insertForbidden(String fbWord) {
		return sqlSessionTemplate.insert("forbidden.insertForbidden", fbWord);
	}

	public int selectCheckFb(String fbWord) {
		return sqlSessionTemplate.selectOne("forbidden.selectCheckFb", fbWord);
	}

	public int deleteForbidden(String fbWord) {
		return sqlSessionTemplate.delete("forbidden.deleteForbidden", fbWord);
	}

	public int selectSearchForbiddenCount(String keyword) {
		return sqlSessionTemplate.selectOne("forbidden.selectSearchForbiddenCount", keyword);
	}

	public ArrayList<Forbidden> selectSearchForbidden(Search search) {
		List<Forbidden> list = sqlSessionTemplate.selectList("forbidden.selectSearchForbidden", search);
		return (ArrayList<Forbidden>)list;
	}
}
