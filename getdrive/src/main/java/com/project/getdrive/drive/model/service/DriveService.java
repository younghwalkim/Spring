package com.project.getdrive.drive.model.service;

import java.util.ArrayList;

import com.project.getdrive.drive.model.vo.Drive;

public interface DriveService {

	int insertTrashCan(Drive drive);

	int checkTrash(Drive drive);

	ArrayList<Drive> selectDriveList(Drive drive);

	int createDrive(Drive drive);

	int selectDriveCount(int tNo);

	Drive selectDrive(int dNo);

}
