package com.project.getdrive.folder.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.getdrive.drive.model.vo.Drive;
import com.project.getdrive.folder.model.vo.Folder;

@Repository("folderDao")
public class FolderDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public int createFolder(Drive drive) {
		return sqlSessionTemplate.insert("folderMapper.createFolder", drive);
	}

	public ArrayList<Folder> selectFolderList(int dNo) {
		List<Folder> list = sqlSessionTemplate.selectList("folderMapper.selectFolderList", dNo);
		return (ArrayList<Folder>) list;
	}

	public Folder selectFolder(int fdNo) {
		return sqlSessionTemplate.selectOne("folderMapper.selectFolder", fdNo);
	}
}
