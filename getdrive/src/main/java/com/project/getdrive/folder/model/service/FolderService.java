package com.project.getdrive.folder.model.service;

import java.util.ArrayList;

import com.project.getdrive.drive.model.vo.Drive;
import com.project.getdrive.folder.model.vo.Folder;

public interface FolderService {

	int createFolder(Drive drive);

	ArrayList<Folder> selectFolderList(int dNo);

	Folder selectFolder(int fdNo);

}
