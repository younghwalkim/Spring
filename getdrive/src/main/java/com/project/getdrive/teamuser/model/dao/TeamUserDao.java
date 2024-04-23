package com.project.getdrive.teamuser.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.getdrive.member.model.vo.Member;
import com.project.getdrive.team.common.Invit;
import com.project.getdrive.team.model.vo.Team;
import com.project.getdrive.teamuser.model.vo.TeamUser;

@Repository("teamUserDao")
public class TeamUserDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public int insertTeamLeader(Member member) {
		return sqlSessionTemplate.insert("teamUserMapper.insertTeamLeader", member);
	}

	public int selectMemberCount(int tNo) {
		return sqlSessionTemplate.selectOne("teamUserMapper.selectMemberCount", tNo);
	}

	public ArrayList<TeamUser> selectMembers(int tNo) {
		List<TeamUser> list= sqlSessionTemplate.selectList("teamUserMapper.selectMembers", tNo);
		return (ArrayList<TeamUser>) list;
	}

	// 2024.04.10 minsik 기능 추가
	public int insertTeamUser(TeamUser teamUser) {
		return sqlSessionTemplate.insert("teamUserMapper.insertTeamUser", teamUser);
	}

	public int updateInvitedTeams(TeamUser teamUser) {
		return sqlSessionTemplate.update("teamUserMapper.updateInvitedTeams", teamUser);
	}

	public int deleteTeamUser(Team team) {
		return sqlSessionTemplate.update("teamUserMapper.deleteTeamUser", team);
	}

	public int removeAuth(Team teamLeader) {
		return sqlSessionTemplate.update("teamUserMapper.removeAuth", teamLeader);
	}

	public int upgradeAuth(Team teamUser) {
		return sqlSessionTemplate.update("teamUserMapper.upgradeAuth", teamUser);
	}

	// 2024.04.10 minsik 기능 추가
	public Member selectMemberNo(String email) {
		return sqlSessionTemplate.selectOne("teamUserMapper.selectMemberNo", email);
	}

	public int checkInvitation(Invit invit) {
		return sqlSessionTemplate.selectOne("teamUserMapper.checkInvitation", invit);
	}
}
