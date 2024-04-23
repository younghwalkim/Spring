package com.project.getdrive.team.model.service;

import java.util.ArrayList;

import com.project.getdrive.team.common.TeamCreator;
import com.project.getdrive.team.model.vo.Team;

public interface TeamService {

	/* 2024.04.06 팀목록/팀초대 수정 */
	ArrayList<Team> selectList(int mNo);

	/* 2024.04.06 나의 팀목록 추가 */	
	ArrayList<Team> myTeamList(int mNo);	

	int insertTeam(TeamCreator teamCreator);

	Team selectTeam(int tNo);

	int updateTeamName(TeamCreator teamCreator);

	int selectDuplicate(String tName);

	int deleteTeam(int tNo);
}
