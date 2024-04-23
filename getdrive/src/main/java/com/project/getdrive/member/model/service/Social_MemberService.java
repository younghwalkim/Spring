package com.project.getdrive.member.model.service;

import com.project.getdrive.member.model.vo.Member;
import com.project.getdrive.member.model.vo.Social_Member;

public interface Social_MemberService {

	int kakaoRegister(Social_Member social_member);
	int seletEmail(String email);
	int selectSocialEmail(String email);
	int selectKakao(String id);
	void kakaoNomalRegister(Member loginMember);
	int seletAccountNoEmail(String email);

}
