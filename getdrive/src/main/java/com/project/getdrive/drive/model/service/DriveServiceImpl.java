package com.project.getdrive.drive.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.getdrive.drive.model.dao.DriveDao;
import com.project.getdrive.drive.model.vo.Drive;
import com.project.getdrive.team.common.TeamCreator;

@Service("driveService")
public class DriveServiceImpl implements DriveService{

	@Autowired
	private DriveDao driveDao;

	@Override
	public int insertTrashCan(Drive drive) {
		return driveDao.insertTrashCan(drive);
	}

	@Override
	public int checkTrash(Drive drive) {
		return driveDao.checkTrash(drive);
	}

	@Override
	public ArrayList<Drive> selectDriveList(Drive drive) {
		return driveDao.selectDriveList(drive);
	}

	@Override
	public int createDrive(Drive drive) {
		return driveDao.createDrive(drive);
	}

	@Override
	public int selectDriveCount(int tNo) {
		return driveDao.selectDriveCount(tNo);
	}

	@Override
	public Drive selectDrive(int dNo) {
		return driveDao.selectDrive(dNo);
	}
}
