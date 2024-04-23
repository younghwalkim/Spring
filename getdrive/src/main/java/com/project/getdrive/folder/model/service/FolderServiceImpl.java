package com.project.getdrive.folder.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.getdrive.drive.model.vo.Drive;
import com.project.getdrive.folder.model.dao.FolderDao;
import com.project.getdrive.folder.model.vo.Folder;

@Service("folderService")
public class FolderServiceImpl implements FolderService{

	@Autowired
	private FolderDao folderDao;
	
	@Override
	public int createFolder(Drive drive) {
		return folderDao.createFolder(drive);
	}

	@Override
	public ArrayList<Folder> selectFolderList(int dNo) {
		return folderDao.selectFolderList(dNo);
	}

	@Override
	public Folder selectFolder(int fdNo) {
		return folderDao.selectFolder(fdNo);
	}
}
