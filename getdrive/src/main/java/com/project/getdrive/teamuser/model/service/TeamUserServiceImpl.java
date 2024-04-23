package com.project.getdrive.teamuser.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.getdrive.member.model.vo.Member;
import com.project.getdrive.team.common.Invit;
import com.project.getdrive.team.model.vo.Team;
import com.project.getdrive.teamuser.model.dao.TeamUserDao;
import com.project.getdrive.teamuser.model.vo.TeamUser;

@Service("teamUserService")
public class TeamUserServiceImpl implements TeamUserService{

	@Autowired
	private TeamUserDao teamUserDao;

	@Override
	public int insertTeamLeader(Member member) {
		return teamUserDao.insertTeamLeader(member);
	}

	@Override
	public int selectMemberCount(int tNo) {
		return teamUserDao.selectMemberCount(tNo);
	}

	@Override
	public ArrayList<TeamUser> selectMembers(int tNo) {
		return teamUserDao.selectMembers(tNo);
	}

	// 2024.04.10 minsik 기능 추가
	@Override
	public int insertTeamUser(TeamUser teamUser) {
		return teamUserDao.insertTeamUser(teamUser);
	}

	@Override
	public int updateInvitedTeams(TeamUser teamUser) {
		return teamUserDao.updateInvitedTeams(teamUser);
	}

	@Override
	public int deleteTeamUser(Team team) {
		return teamUserDao.deleteTeamUser(team);
	}

	@Override
	public int removeAuth(Team teamLeader) {
		return teamUserDao.removeAuth(teamLeader);
	}

	@Override
	public int upgradeAuth(Team teamUser) {
		return teamUserDao.upgradeAuth(teamUser);
	}

	// 2024.04.10 minsik 기능 추가
	@Override
	public Member selectMemberNo(String email) {
		return teamUserDao.selectMemberNo(email);
	}

	@Override
	public int checkInvitation(Invit invit) {
		return teamUserDao.checkInvitation(invit);
	}


}
