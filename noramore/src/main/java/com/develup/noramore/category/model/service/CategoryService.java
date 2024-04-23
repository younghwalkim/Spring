package com.develup.noramore.category.model.service;

import java.util.ArrayList;

import com.develup.noramore.category.model.vo.Category;

public interface CategoryService {

	ArrayList<Category> selectAll();

	int insertCategory(Category category);

	int nextCategoryId();

	ArrayList<Category> selectCategory();

}
