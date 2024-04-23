package com.project.getdrive.file.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.getdrive.file.model.dao.FileDao;
import com.project.getdrive.file.model.vo.File;

@Service("fileService")
public class FileServiceImpl implements FileService{

	@Autowired
	private FileDao fileDao;

	@Override
	public ArrayList<File> selectFileList(int fdNo) {
		return fileDao.selectFileList(fdNo);
	}

	@Override
	public int uploadFile(File setFile) {
		return fileDao.uploadFile(setFile);
	}

	@Override
	public File selectFile(int flNo) {
		return fileDao.selectFile(flNo);
	}
}
