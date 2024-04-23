package com.project.getdrive.search.model.service;

import java.util.ArrayList;

import com.project.getdrive.common.AlarmSch;
import com.project.getdrive.common.CommonSch;
import com.project.getdrive.search.model.vo.Search;

public interface SearchService {

	// 검색결과 갯수
	int selectSearchCount(CommonSch commonSch);

	// 검색결과
	ArrayList<Search> selectSearch(CommonSch commonSch);

	// 알람
	ArrayList<Search> selectAlarmList(AlarmSch alarmSch);

	void selectRead(AlarmSch alarmSch);

}
