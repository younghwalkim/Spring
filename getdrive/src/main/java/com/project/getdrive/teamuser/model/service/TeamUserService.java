package com.project.getdrive.teamuser.model.service;

import java.util.ArrayList;

import com.project.getdrive.member.model.vo.Member;
import com.project.getdrive.team.common.Invit;
import com.project.getdrive.team.model.vo.Team;
import com.project.getdrive.teamuser.model.vo.TeamUser;

public interface TeamUserService {

	int insertTeamLeader(Member member);

	int selectMemberCount(int tNo);

	ArrayList<TeamUser> selectMembers(int tNo);

	// 2024.04.10 minsik 기능 추가
	int insertTeamUser(TeamUser teamUser);

	int updateInvitedTeams(TeamUser teamUser);

	int deleteTeamUser(Team team);

	int removeAuth(Team teamLeader);

	int upgradeAuth(Team teamUser);

	// 2024.04.10 minsik 기능 추가
	Member selectMemberNo(String email);

	int checkInvitation(Invit invit);

}
