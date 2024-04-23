package com.project.getdrive.team.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.getdrive.team.common.TeamCreator;
import com.project.getdrive.team.model.dao.TeamDao;
import com.project.getdrive.team.model.vo.Team;

@Service("teamService")
public class TeamServiceImpl implements TeamService{

	@Autowired
	private TeamDao teamDao;
	
	/* 2024.04.06 팀목록/팀초대 수정 */	
	@Override
	public ArrayList<Team> selectList(int mNo) {
		return teamDao.selectList(mNo);
	}
	
	/* 2024.04.06 나의 팀목록 조회 */	
	@Override
	public ArrayList<Team> myTeamList(int mNo) {
		return teamDao.myTeamList(mNo);
	}	

	@Override
	public int insertTeam(TeamCreator teamCreator) {
		return teamDao.insertTeam(teamCreator);
	}

	@Override
	public Team selectTeam(int tNo) {
		return teamDao.selectTeam(tNo);
	}

	@Override
	public int updateTeamName(TeamCreator teamCreator) {
		return teamDao.updateTeamName(teamCreator);
	}

	@Override
	public int selectDuplicate(String tName) {
		return teamDao.selectDuplicate(tName);
	}

	@Override
	public int deleteTeam(int tNo) {
		return teamDao.deleteTeam(tNo);
	}
	
}
