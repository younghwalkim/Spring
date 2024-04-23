package com.project.getdrive.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.project.getdrive.mail.MailHandler;
import com.project.getdrive.member.model.service.MemberService;
import com.project.getdrive.member.model.vo.Member;

@Controller
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MailHandler mailHandler;

	@Autowired
	private MemberService memberService;

	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;

	// 메인 이동
	@RequestMapping(value = "mainPage.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String moveMainPage() {
		return "common/main";
	}

	// 약관페이지 이동
	@RequestMapping(value = "contractPage.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String moveContractPage() {
		return "member/contract";
	}

	// 회원가입페이지 이동
	@RequestMapping(value = "registerPage.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String moveRegisterPage() {
		return "member/register";
	}

	// 계정설정페이지 이동
	@RequestMapping(value = "accountSettingPage.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String moveAccountSetting() {
		return "member/accountSetting";
	}

	// 비밀번호찾기페이지 이동
	@RequestMapping(value = "getPasswordFindPage.do", method = RequestMethod.GET)
	public String getPasswordFindPage() {
		return "member/passwordFind";
	}

	/* 보내는사람, 받는사람, 제목, 내용 */
	public void mailSend(String from, String to, String title, String contents) {
		// mailHandler.sendEmail("classgetdrive@gmail.com", "junstin119@gmail.com",
		// "제목테스트", "내용테스트");
		mailHandler.sendEmail(from, to, title, contents);
	}
	

	// 비밀번호 찾기
	@RequestMapping(value = "passwordGo.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String passwordFind(@RequestParam("email") String email, Member member, Model model, HttpServletResponse response) throws IOException {
		
		System.out.println(email);
		
		Member loginMember = memberService.selectMember(member.getEmail());
		
		String pw = "";
		for (int i = 0; i < 12; i++) {
			pw += (char) ((Math.random() * 26) + 97);
		}
		
		System.out.println(pw);
		
		loginMember.setPwd(this.bcryptPasswordEncoder.encode(pw));

		String returnStr = null;

		if (memberService.updateMember(loginMember) > 0) {
			returnStr = "ok";
		} else {
			returnStr = "dup";
		}
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.append(returnStr);
		out.flush();
		out.close();

		// 비밀번호 변경
		
		mailSend("classgetdrive@gmail.com", email, "Classgetdrive 임시비밀번호 발송드립니다.", "임시 비밀번호는" + pw + "입니다.");
	
		return "common/main";

	}

	//회원가입완료페이지 이동
  
	@RequestMapping(value = "registerCompletePage.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String moveRegisterCompletePage() {
		return "member/registerComplete";
	}

	// 로그인
	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public String loginMethod(Member member, HttpSession session, SessionStatus status, Model model) {
		logger.info("login.do : " + member.toString());

		Member loginMember = memberService.selectMember(member.getEmail());

		if (loginMember != null && this.bcryptPasswordEncoder.matches(member.getPwd(), loginMember.getPwd())) {

			session.setAttribute("loginMember", loginMember);
			status.setComplete();
			return "common/main";
		} else {
			model.addAttribute("message", "이메일 또는 비밀번호를 다시 확인하세요.");
			return "common/main";
		}

	}

	// 로그아웃
	@RequestMapping("logout.do")
	public String logoutMethod(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		// 세션 객체가 있으면 리턴받고, 없으면 null 리턴
		if (session != null) {
			session.invalidate();
			return "common/main";
		} else {
			model.addAttribute("message", "로그인 세션이 존재하지 않습니다.");
			return "common/error";
		}
	}

	// 회원가입 처리
	@RequestMapping(value = "register.do", method = { RequestMethod.POST })
	public String register(Member member, Model model) {
		logger.info("register.do : " + member);

		if (memberService.register(member) > 0) {
			return "member/registerComplete";
		} else {
			model.addAttribute("message", "입력 정보를 다시한번 확인해주세요.");
			return "commom/error";

		}

	}

	// 로그인시 아이디 비밀번호 일치여부 확인
	@RequestMapping(value = "accountchk.do", method = RequestMethod.POST)
	public void accountCheck(@RequestParam("email") String email, @RequestParam("pwd") String password,
			HttpServletResponse response) throws IOException {
		Member member = new Member();
		member.setEmail(email);
		member.setPwd(password);

		boolean AccountValid = memberService.accountCheck(member);

		String returnStr = AccountValid ? "ok" : "dup";
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.append(returnStr);
		out.flush();
		out.close();
	}

	// 이메일 중복확인처리용
	@RequestMapping(value = "idchk.do", method = RequestMethod.POST)
	public void dupCheckEmailMethod(@RequestParam("email") String email, HttpServletResponse response)
			throws IOException {
		// 메소드 매개변수 영역에서 사용하는 어노테이션 중에
		// @RequestParam("전송온이름") 자료형 값저장변수명
		// 자료형 값저장변수명 = request.getParameter("전송온이름"); 코드와 같음

		int idCount = memberService.selectCheckEmail(email);

		String returnStr = null;
		if (idCount == 0) {
			returnStr = "ok";
		} else {
			returnStr = "dup";
		}

		// response 를 이용해서 클라이언트와 출력스트림을 열어서 문자열값 내보냄
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.append(returnStr);
		out.flush();
		out.close();

	}

	// 비밀번호 일치확인처리용
	@RequestMapping(value = "pwdchk2.do", method = { RequestMethod.GET, RequestMethod.POST })
	public void dupCheckPwd(Member member, @RequestParam("nowPwd") String pwd, HttpServletRequest request,
			HttpServletResponse response, Model model) throws IOException {
		logger.info("pwdchk2.do : " + member.toString());

		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember"); // 세션에서 loginMember 객체를 가져옴

		loginMember.getEmail();

		System.out.println(loginMember.getEmail());

		String email = loginMember.getEmail(); // 이메일 값을 변수에 저장
		System.out.println(email); // 이메일 값 출력

		// 이메일 값을 사용하여 회원 정보를 조회하는 메서드 호출
		memberService.selectMember(email);

		String returnStr = null;

		if (this.bcryptPasswordEncoder.matches(pwd, loginMember.getPwd())) {
			returnStr = "ok";
		} else {
			returnStr = "dup";
		}
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.append(returnStr);
		out.flush();
		out.close();

	}

	// 비밀번호 일치확인처리용
	@RequestMapping(value = "pwdchk3.do", method = { RequestMethod.GET, RequestMethod.POST })
	public void dupCheckPwd2(Member member, @RequestParam("nowPwd3") String pwd, HttpServletRequest request,
			HttpServletResponse response, Model model) throws IOException {
		logger.info("pwdchk3.do : " + member.toString());

		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember"); // 세션에서 loginMember 객체를 가져옴

		loginMember.getEmail();

		System.out.println(loginMember.getEmail());

		String email = loginMember.getEmail(); // 이메일 값을 변수에 저장
		System.out.println(email); // 이메일 값 출력

		// 이메일 값을 사용하여 회원 정보를 조회하는 메서드 호출
		memberService.selectMember(email);

		String returnStr = null;

		if (this.bcryptPasswordEncoder.matches(pwd, loginMember.getPwd())) {
			returnStr = "ok";
		} else {
			returnStr = "dup";
		}
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.append(returnStr);
		out.flush();
		out.close();

	}

	// 비밀번호 변경 처리
	@RequestMapping(value = "passwordChange.do", method = { RequestMethod.GET, RequestMethod.POST })
	public void passwordSetting(Member member, @RequestParam("pwd") String pwd, HttpServletResponse response,
			HttpServletRequest request, Model model) throws IOException {

		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember"); // 세션에서 loginMember 객체를 가져옴

		loginMember.setPwd(this.bcryptPasswordEncoder.encode(pwd));

		String returnStr = null;

		if (memberService.updateMember(loginMember) > 0) {
			returnStr = "ok";
		} else {
			returnStr = "dup";
		}
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.append(returnStr);
		out.flush();
		out.close();

	}

	// 회원탈퇴 처리
	@RequestMapping("mdel.do")
	public String memberDeleteMethod(Member member, HttpServletResponse response, HttpServletRequest request,
			Model model) {

		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");

		loginMember.getDdate();
		loginMember.getEmail();

		if (memberService.deleteMember(loginMember.getEmail()) > 0) {
			// 컨트롤러 또는 다른 컨트롤러의 메소드를 직접 호출할 경우
			return "redirect:logout.do";
		} else {
			model.addAttribute("message", "회원 탈퇴 실패!");
			return "common/error";
		}

	}

}
