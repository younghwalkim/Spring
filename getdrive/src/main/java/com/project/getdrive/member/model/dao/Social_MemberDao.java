package com.project.getdrive.member.model.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.getdrive.member.model.vo.Member;
import com.project.getdrive.member.model.vo.Social_Member;

@Repository("social_MemberDao")
public class Social_MemberDao {
	
	@Autowired 
	private SqlSessionTemplate sqlSessionTemplate;

	public int kakaoRegister(Social_Member social_member) {
		return sqlSessionTemplate.insert("socialMemberMapper.kakaoregister",social_member);
	}

	public int seletEmail(String email) {
		return sqlSessionTemplate.selectOne("memberMapper.selectCheckEmail",email);
	}

	public void kakaoNomalRegister(Member loginMember) {
		sqlSessionTemplate.insert("memberMapper.kakaoNomalRegister", loginMember);
	}

	public int selectSocialEmail(String email) {
		return sqlSessionTemplate.selectOne("socialMemberMapper.selectSocialEmail",email);
	}

	public int selectKakao(String id) {
		return sqlSessionTemplate.selectOne("socialMemberMapper.selectKakao",id);
	}

	public int seletAccountNoEmail(String email) {
		return sqlSessionTemplate.selectOne("memberMapper.seletAccountNoEmail",email);
	}

}
