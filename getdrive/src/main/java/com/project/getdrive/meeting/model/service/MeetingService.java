package com.project.getdrive.meeting.model.service;

import java.util.ArrayList;

import com.project.getdrive.common.Paging;
import com.project.getdrive.common.SearchPaging;
import com.project.getdrive.meeting.model.vo.Meeting;

public interface MeetingService {

	int selectListCount(int tNo);

	ArrayList<Meeting> selectList(Paging paging);

	int insertMeeting(Meeting meeting);

	Meeting selectOne(int mtId);

	void updateAddReadCount(int mtId);

	int updateMeeting(Meeting meeting);

	int deleteMeeting(int mtId);

	int selectSearchTitleCount(String keyword);

	ArrayList<Meeting> selectSearchTitle(SearchPaging search);

	int selectSearchContentCount(String keyword);

	ArrayList<Meeting> selectSearchContent(SearchPaging search);

	int selectSearchDateCount(SearchPaging search);

	ArrayList<Meeting> selectSearchDate(SearchPaging search);

}
