package com.develup.noramore.category.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.develup.noramore.category.model.vo.Category;

@Repository("categoryDao")
public class CategoryDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public ArrayList<Category> selectAll() {
		List<Category> list = sqlSessionTemplate.selectList("category.selectAll");
		return (ArrayList<Category>)list;
	}

	public int insertCategory(Category category) {
		return sqlSessionTemplate.insert("category.insertCategory", category);
	}

	public int nextCategoryId() {
		return sqlSessionTemplate.selectOne("category.nextCategoryId");
	}

	public ArrayList<Category> selectCategory() {
		List<Category> list = sqlSessionTemplate.selectList("category.selectCategory");
		return (ArrayList<Category>)list;
	}

}
