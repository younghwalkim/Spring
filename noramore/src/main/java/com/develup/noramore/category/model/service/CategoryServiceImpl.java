package com.develup.noramore.category.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develup.noramore.category.model.dao.CategoryDao;
import com.develup.noramore.category.model.vo.Category;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public ArrayList<Category> selectAll() {
		return categoryDao.selectAll();
	}//

	@Override
	public int insertCategory(Category category) {
		return categoryDao.insertCategory(category);
	}

	@Override
	public int nextCategoryId() {
		return categoryDao.nextCategoryId();
	}

	@Override
	public ArrayList<Category> selectCategory() {
		return categoryDao.selectCategory();
	}
	
}//
