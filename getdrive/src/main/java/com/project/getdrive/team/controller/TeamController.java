package com.project.getdrive.team.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.getdrive.member.model.vo.Member;
import com.project.getdrive.team.common.TeamCreator;
import com.project.getdrive.team.model.service.TeamService;
import com.project.getdrive.team.model.vo.Team;
import com.project.getdrive.teamuser.model.service.TeamUserService;

@Controller
public class TeamController {
	private static final Logger logger = LoggerFactory.getLogger(TeamController.class);
	
	@Autowired
	private TeamService teamService;
	@Autowired
	private TeamUserService teamUserService;
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;

	// 메인 화면 입장, 팀 정보 tNo(팀 고유번호)
	@RequestMapping("tmain.do")
	public String moveTeamMain(
			HttpServletRequest request,
			Model model)  {
		
		/* 2024.04.06 kimyh 팀목록/팀초대 추가 */
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");

		// System.out.println("#### Account No: " + loginMember + "########################################");

		// 로그인 후 Session을 통해 회원이 맞다면 소속된 팀 조회, 팀이 없다면 팀 생성 페이지 이동을 돕는 안내 페이지로 이동
		/* 2024.04.06 kimyh 팀목록/팀초대 수정 */
		ArrayList<Team> list = teamService.selectList(loginMember.getAccountNo());

		
		if(list != null && list.size() > 0) {
			model.addAttribute("list", list);
			return "team/teamMain";			
		} else {
			return "team/newTeam";
		}
		
	}
	
	// 팀장일때 관리페이지 노출
	@RequestMapping("tadmin.do")
	public String moveTeamAdmin(@RequestParam("tNo") int tNo, Model model) {
		
		Team team = teamService.selectTeam(tNo);
		
		if(team != null) {
			model.addAttribute("team", team);
			return "team/teamAdmin";			
		} else {
			model.addAttribute("message", "팀 정보 조회 실패");
			return "common/error";
		}
	}
	
	// 팀 생성 페이지
	@RequestMapping("tcreate.do")
	public String createTeam() {
		return "team/createTeam";
	}
	
	// 팀 생성
	@RequestMapping(value="iteam.do", method=RequestMethod.POST) 
	public String insertTeam(Model model,
			@RequestParam("tName") String tName,
			HttpServletRequest request) {
	  
		Member member = (Member) request.getSession().getAttribute("loginMember");
		int accountNo = member.getAccountNo();
		
		// team.common에서 팀장번호와 팀명을 넘겨줄 객체 호출
		TeamCreator teamCreator = new TeamCreator(accountNo, tName);
		
		// 팀 생성
		int teamResult = teamService.insertTeam(teamCreator);
		
		if(teamResult > 0) {
			// 팀 생성과 동시에 팀장으로서 멤버가 됨
			int teamLeaderResult = teamUserService .insertTeamLeader(member);
			
			if(teamLeaderResult > 0) {
				return "redirect:tmain.do";
			} else {
				model.addAttribute("message", "팀장으로서 팀 유저 가입 실패");
				return "common/error";
			}
			
		} else {
			model.addAttribute("message", "팀 목록 조회 실패");
			return "common/error";
		}
	}
	 
	// 팀명 수정
	@RequestMapping("uteam.do")
	public String updateTeam(Model model,
			@RequestParam("tName") String tName,
			@RequestParam("tNo") int tNo) {
		
		TeamCreator teamCreator = new TeamCreator(tNo, tName);
		
		int result = teamService.updateTeamName(teamCreator);
		
		if(result > 0) {
			return "redirect:tmain.do";
		} else {
			model.addAttribute("message", "팀명 수정 실패");
			return "common/error";
		}
	}
	
	// 팀명 중복 검사
	@RequestMapping(value="duplicateCheck.do", method=RequestMethod.POST)
	public void duplicateCheckTeamName(HttpServletResponse response,
			@RequestParam("tName") String tName) throws IOException {
		
		int result = teamService.selectDuplicate(tName);
		
		PrintWriter out = response.getWriter();
		
		if(result > 0) {
			out.append("duplicate");
		} else {
			out.append("ok");
		}
		
		out.flush();
		out.close();
	}
	
	// 팀장의 비밀번호 검사
	@RequestMapping(value="pwdchk.do", method=RequestMethod.POST)
	public void checkPassword(HttpServletRequest request, HttpServletResponse response,
	        @RequestParam("pwd") String pwd) throws IOException {
	    Member member = (Member) request.getSession().getAttribute("loginMember");
	    
	    boolean isMatch = bcryptPasswordEncoder.matches(pwd, member.getPwd());

	    PrintWriter out = response.getWriter();
	    
	    if(isMatch) {
	        out.append("ok");
	    } else {
	        out.append("wrong"); 
	    }
		
		out.flush();
		out.close();
	}
	
	// 팀원이 한 명일때만(팀장만 멤버일 경우) 삭제
	@RequestMapping(value="dteam.do", method=RequestMethod.POST)
	public void deleteTeam(HttpServletResponse response,
			@RequestParam("tNo") int tNo) throws IOException {
		
		int memberCount = teamUserService.selectMemberCount(tNo);
		
		PrintWriter out = response.getWriter();
		
		if(memberCount <= 1) {
			int result = teamService.deleteTeam(tNo);
			
			if(result > 0) {
				out.append("success");
			}
		} else {
			out.append("failed");
		}
		
		out.flush();
		out.close();
	}
	
}