package com.project.getdrive.file.model.service;

import java.util.ArrayList;

import com.project.getdrive.file.model.vo.File;

public interface FileService {

	ArrayList<File> selectFileList(int fdNo);

	int uploadFile(File setFile);

	File selectFile(int flNo);

}
