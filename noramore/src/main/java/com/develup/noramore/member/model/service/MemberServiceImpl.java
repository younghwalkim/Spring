package com.develup.noramore.member.model.service;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.develup.noramore.member.model.dao.MemberDao;
import com.develup.noramore.member.model.vo.Member;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;
	
	

	@Override
	public Member selectMember(String memberID) {
		return memberDao.selectMember(memberID);
	}

	@Override
	public int insertMember(Member member) {
		return memberDao.insertMember(member);
	}

	@Override
	public int selectCheckId(String memberid) {
		return memberDao.selectCheckId(memberid);
	}

	@Override
	public int selectCheckEmail(String email) {
		return memberDao.selectCheckEmail(email);
	}

	@Override
	public Member selectFindId(Member member) {
		return memberDao.selectFindId(member);
	}

	@Override
	public int updatePw(Member member) {
		return memberDao.updatePw(member);
	}

	@Override
	public Member selectKakaoLogin(String kid) {
		return memberDao.selectKakaoLogin(kid);
	}

//	@Override
//	public void insertKakaoMember(Member kakaovo) {
//		 memberDao.insertKakaoMember(kakaovo);
//		
//	}

	@Override
	public int selectCheckNicname(String memberNicname) {
		return memberDao.selectCheckNicname(memberNicname);
	}

	@Override
	public int updateMember(Member member) {
		return memberDao.updateMember(member);
	}

	@Override
	public int selectPwMatch(Member member) {
		return memberDao.selectPwMatch(member);
	}

	@Override
	public int updateResign(String memberID) {
		return memberDao.updateResign(memberID);
		
	}

	@Override
	public int updatePhotofileName(Member member) {
		return memberDao.updatePhotofileName(member);
	}
	
	
	
	
	
}
