package com.project.getdrive.file.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.getdrive.file.model.vo.File;

@Repository("fileDao")
public class FileDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public ArrayList<File> selectFileList(int fdNo) {
		List<File> list = sqlSessionTemplate.selectList("fileMapper.selectFileList", fdNo);
		return (ArrayList<File>) list;
	}

	public int uploadFile(File setFile) {
		return sqlSessionTemplate.insert("fileMapper.uploadFile", setFile);
	}

	public File selectFile(int flNo) {
		return sqlSessionTemplate.selectOne("fileMapper.selectFile", flNo);
	}
}
