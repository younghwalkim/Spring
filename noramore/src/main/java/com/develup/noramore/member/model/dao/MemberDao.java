package com.develup.noramore.member.model.dao;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.develup.noramore.member.model.vo.Member;

@Repository("memberDao")
public class MemberDao {

	@Autowired   //root-context.xml 에서 생성한 객체를 자동 연결함
	private SqlSessionTemplate sqlSessionTemplate;
	
	public Member selectMember(String memberID) {
		return sqlSessionTemplate.selectOne("memberMapper.selectMember", memberID);
	}

	public int insertMember(Member member) {
		return sqlSessionTemplate.insert("memberMapper.insertMember", member);
	}

	public int selectCheckId(String memberid) {
		return sqlSessionTemplate.selectOne("memberMapper.selectCheckId", memberid);
	}

	public int selectCheckEmail(String email) {
		return sqlSessionTemplate.selectOne("memberMapper.selectCheckEmail", email);
	} 

	public Member selectFindId(Member member) {  //이름, 이메일
		return sqlSessionTemplate.selectOne("memberMapper.selectFindId", member);
	}

	public int updatePw(Member member) {
		return sqlSessionTemplate.update("memberMapper.updatePw", member);
	}

	public Member selectKakaoLogin(String kid) {
		return sqlSessionTemplate.selectOne("memberMapper.selectKakaoLogin", kid);
	}

	public int selectCheckNicname(String memberNicname) {
		return sqlSessionTemplate.selectOne("memberMapper.selectCheckNicname", memberNicname);
	}

	public int updateMember(Member member) {
		return sqlSessionTemplate.update("memberMapper.updateMember", member);
	}

	public int selectPwMatch(Member member) {
		return sqlSessionTemplate.selectOne("memberMapper.selectPwMatch", member);
	}

	public int updateResign(String memberID) {
		return sqlSessionTemplate.update("memberMapper.updateResign", memberID);
	}

	public int updatePhotofileName(Member member) {
		return sqlSessionTemplate.update("memberMapper.updatePhotofileName", member);
	}



	


	
	
}

