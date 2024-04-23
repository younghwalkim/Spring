package com.project.getdrive.meeting.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.getdrive.common.Paging;
import com.project.getdrive.common.SearchPaging;
import com.project.getdrive.meeting.model.dao.MeetingDao;
import com.project.getdrive.meeting.model.vo.Meeting;

@Service("meetingService")
public class MeetingServiceImpl implements MeetingService {
	@Autowired
	private MeetingDao meetingDao;

	@Override
	public int selectListCount(int tNo) {
		return meetingDao.getListCount(tNo);
	}

	@Override
	public ArrayList<Meeting> selectList(Paging paging) {
		return meetingDao.selectList(paging);
	}

	@Override
	public int insertMeeting(Meeting meeting) {
		return meetingDao.insertMeeing(meeting);
	}

	@Override
	public Meeting selectOne(int mtId) {
		return meetingDao.selectOne(mtId);
	}

	@Override
	public void updateAddReadCount(int mtId) {
		meetingDao.addReadCount(mtId);	
	}

	@Override
	public int updateMeeting(Meeting meeting) {
		return meetingDao.updateMeeting(meeting);
	}

	@Override
	public int deleteMeeting(int mtId) {
		return meetingDao.deleteMeeting(mtId);
	}

	@Override
	public int selectSearchTitleCount(String keyword) {
		return meetingDao.getSearchTitleCount(keyword);
	}

	@Override
	public ArrayList<Meeting> selectSearchTitle(SearchPaging search) {
		return meetingDao.selectSearchTitle(search);
	}

	@Override
	public int selectSearchContentCount(String keyword) {
		return meetingDao.getSearchContentCount(keyword);
	}

	@Override
	public ArrayList<Meeting> selectSearchContent(SearchPaging search) {
		return meetingDao.selectSearchContent(search);
	}

	@Override
	public int selectSearchDateCount(SearchPaging date) {
		return meetingDao.getSearchDateCount(date);
	}

	@Override
	public ArrayList<Meeting> selectSearchDate(SearchPaging search) {
		return meetingDao.selectSearchDate(search);
	}

	
	
	
	
	
	
}
