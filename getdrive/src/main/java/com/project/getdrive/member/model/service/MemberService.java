package com.project.getdrive.member.model.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.project.getdrive.member.model.vo.Member;

public interface MemberService {

	int register(Member member);
	Member login(Member member);
	int selectCheckEmail(String userId);
	boolean accountCheck(Member member);
	Member selectMember(String email);
	void updatePassword(Member member);
	Member selectCheckPwd(String email);
	int updateMember(Member loginMember);
	int deleteMember(String email);

}
