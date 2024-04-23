package com.develup.noramore.category.model.vo;

import java.sql.Date;

public class Category implements java.io.Serializable{

	private static final long serialVersionUID = -6177081089970916598L;
	
	private int categoryId;
	private String categoryName;
	private Date registDate;
	private String originalFileName;
	private String renameFileName;
	
	public Category() {
		super();
	}

	public Category(int categoryId, String categoryName, Date registDate, String originalFileName,
			String renameFileName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.registDate = registDate;
		this.originalFileName = originalFileName;
		this.renameFileName = renameFileName;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getRenameFileName() {
		return renameFileName;
	}

	public void setRenameFileName(String renameFileName) {
		this.renameFileName = renameFileName;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", registDate=" + registDate
				+ ", originalFileName=" + originalFileName + ", renameFileName=" + renameFileName + "]";
	}
	
	
	
	
}//
