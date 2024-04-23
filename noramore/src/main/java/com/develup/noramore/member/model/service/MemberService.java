package com.develup.noramore.member.model.service;


import com.develup.noramore.member.model.vo.Member;


public interface MemberService {


	Member selectMember(String memberID);

	int insertMember(Member member);

	int selectCheckId(String memberid);

	int selectCheckEmail(String email);

	Member selectFindId(Member member);

	int updatePw(Member member);

	Member selectKakaoLogin(String kid);

//	void insertKakaoMember(Member kakaovo);

	int selectCheckNicname(String memberNicname);

	int updateMember(Member member);

	int selectPwMatch(Member member);

	int updateResign(String memberID);

	int updatePhotofileName(Member member);




	


}
