package com.develup.noramore.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.develup.noramore.alarm.model.service.AlarmService;
import com.develup.noramore.chatting.model.service.ChattingService;
import com.develup.noramore.chatting.vo.Message;
import com.develup.noramore.memadd.model.service.MemAddService;
import com.develup.noramore.member.model.service.MemberService;
import com.develup.noramore.member.model.vo.Member;
import com.develup.noramore.member.naver.api.NaverLoginAuth;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.scribejava.core.model.OAuth2AccessToken;

@Controller
public class MemberController {
	// 이 클래스 안에 메소드들이 동작이 잘 되는지 또는 전달값이나 리턴값을 확인하기 위한 용도로 로그객체를 생성함
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class); // 다른 클래스를 통해서 얻어냄,
																							// org.slf4j.Logger로
																							// import하기

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemAddService memAddService;
	
	@Autowired
	private AlarmService alarmService;

   @Autowired
   private ChattingService chattingService;
	
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder; // spring-security.xml에서의 id를 가져다 변수 명으로 사용
	// 암호를 암호화 함.


	@Autowired
	private KakaoLoginAuth kakaoLoginAuth;


	@Autowired
	private NaverLoginAuth naverLoginAuth;

	
	private String apiResult = null;
	
	
	// 뷰 페이지 내보내기용 메소드 ---------------------------------------------------
	
	
	@RequestMapping(value = "moveLoginPage.do", method = { RequestMethod.GET, RequestMethod.POST })
	// RequestMethod.GET : get방식으로 전송오면 받음, RequestMethod.POST : post방식으로 전송오면 받음
	public String moveLoginPage(Model model, HttpSession session) {
		
		// 카카오 로그인 접속을 위한 인증 url 정보 생성
		String kakaoAuthURL = kakaoLoginAuth.getAuthorizationUrl(session);
		logger.info(kakaoAuthURL);

		// 네이버 로그인 접속을 위한 인증 url 정보 생성
		String naverAuthURL = naverLoginAuth.getAuthorizationUrl(session);

//		// 구글 로그인 접속을 위한 인증 url 정보 생성
//		String googleAuthURL = googleLoginAuth.getAuthorizationUrl(session);

		// 모델에 각각의 url 정보 저장
		model.addAttribute("kakaourl", kakaoAuthURL);
//		model.addAttribute("googleourl", googleAuthURL);
		model.addAttribute("naverurl", naverAuthURL);

		return "/member/moveLoginPage";

	}
	

	

	@RequestMapping(value = "enrollPage.do", method = { RequestMethod.GET, RequestMethod.POST })
	// RequestMethod.GET : get방식으로 전송오면 받음, RequestMethod.POST : post방식으로 전송오면 받음
	public String enrollPage() {
		return "/member/moveEnrollPage";

	}

	@RequestMapping(value = "findIDPage.do", method = { RequestMethod.GET, RequestMethod.POST })
	// RequestMethod.GET : get방식으로 전송오면 받음, RequestMethod.POST : post방식으로 전송오면 받음
	public String findIDPage() {
		return "/member/findIDPage";

	}

	@RequestMapping(value = "findPWPage.do", method = { RequestMethod.GET, RequestMethod.POST })
	// RequestMethod.GET : get방식으로 전송오면 받음, RequestMethod.POST : post방식으로 전송오면 받음
	public String findPWPage() {
		return "/member/findPWPage";

	}
	
	
	@RequestMapping(value = "findPW2.do", method = { RequestMethod.GET, RequestMethod.POST })
	// RequestMethod.GET : get방식으로 전송오면 받음, RequestMethod.POST : post방식으로 전송오면 받음
	public String findPWPage2() {
		return "/member/findPWPage2";

	}
	
	
	

	@RequestMapping(value = "my.do", method = { RequestMethod.GET, RequestMethod.POST })
	// RequestMethod.GET : get방식으로 전송오면 받음, RequestMethod.POST : post방식으로 전송오면 받음
	public String mypage(HttpServletRequest request, Model model) {
	      
	      model.addAttribute("memberID", request.getSession().getAttribute("memberID"));
		
		
		
		return "/member/myArticlePage";
	}
	
	

	// 로그인 처리용
	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public String loginMethod(Member member, 
								HttpSession session, 
								SessionStatus status, 
								Model model,
								RedirectAttributes ra) { // HttpSession : 세션 자동 생성, SessionStatus : 세션 상태 파악
																										
		logger.info("login.do : " + member.toString());

		Member loginMember = memberService.selectMember(member.getMemberID());

		if (loginMember != null
				&& this.bcryptPasswordEncoder.matches(member.getMemberPWD(), loginMember.getMemberPWD())) {
			
			session.setAttribute("loginMember", loginMember); // 세션이름에 loginMember을 저장함
		      int listCount = alarmService.selectNewCount(member.getMemberID());
		      
		      Message message = chattingService.selectChatRequest(member.getMemberID());
	            if(message != null) {
	               session.setAttribute("chatOn", "chat request exist");
	            }
		      
		      if(listCount > 0) {
		         session.setAttribute("alarmCount", listCount);
		      }
			status.setComplete(); // 로그인 성공 요청결과로 HttpStatus code 200 보냄
			return "redirect:home.do";
		} else {
		    model.addAttribute("message", "아이디 또는 비밀번호가 틀렸거나 탈퇴회원입니다.다시 입력해 주세요.");
			/* String message = "없는 회원입니다. 다시 확인해 주세요."; */
			/* return "redirect:moveLoginPage.do?message=" + message; */
		    return "member/moveLoginPage";
		}
	}
	
	
	
	

	// 로그아웃 처리용 메소드

	@RequestMapping("logout.do")
	public String logoutMethod(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		// 세션 객체가 있으면 리턴받고, 없으면 null 리턴
		if (session != null) {
			session.invalidate();
			return "redirect:home.do";
		} else {
			model.addAttribute("message", "로그인 세션이 존재하지 않습니다.");
			return "redirect:moveLoginPage.do";
		}
	}
	
	
	

	
	// 카카오 로그인 요청 처리용
		// (카카오 로그인 클릭시 전달된 kakaourl 에 의해 실행됨)
		@RequestMapping(value = "kcallback.do", produces = "application/json", method =  RequestMethod.GET )
		public String kakaoLogin(@RequestParam String code, Model model, HttpSession session) {
			logger.info("0. kcallback.do : " + code);  //카카오 OAuth 인증 코드
			
			//로그인 결과값을 node에 담아줌     //카카오 OAuth 인증 코드를 통해 액세스 토큰을 받아옴
			JsonNode node = kakaoLoginAuth.getAccessToken(code); 
			
			// accessToken에 사용자의 로그인한 모든 정보가 들어있음
			JsonNode accessToken = node.get("access_token");
			logger.info("2. kcallback.do :    du" + accessToken);
			
			// 사용자 정보 추출  //json으로 반환됨
			JsonNode memberInfo = kakaoLoginAuth.getKakaoUserInfo(accessToken);
			logger.info("3. kcallback.do : " + memberInfo);
			
			// db table 에 기록할 회원정보 추출함 : 카카오 회원가입시
			//userInfo 에서 properties 정보 추출
			JsonNode properties = node.get("properties");
			logger.info("4. kcallback.do : " + properties);
			
			//path("kakao_account") 메서드는 userInfo에서 "kakao_account" 필드에 해당하는 하위 노드를 찾아서 그 값을 반환함
			JsonNode kakao_account = memberInfo.path("kakao_account"); //사용자의 카카오계정 정보를 kakao_account변수에 저장함.
			String id = memberInfo.path("id").asText();     //사용자의 카카오 id 필드에 해당하는 문자열 값을 kid변수에 저장함. 
			 
			//asText() : 해당필드의 값을 문자열로 변환함
			logger.info("5. kcallback.do : " + kakao_account);  
			System.out.println("5. kcallback.do : " + kakao_account);
			//등록된 카카오 회원 테이블에서 회원 정보 조회해 옴
			Member kmember = memberService.selectKakaoLogin(id);
			System.out.println("6. kcallback.do : " + memberService.selectKakaoLogin(id));
			
			Member loginMember = null;
			
			//처음 로그인 요청시 카카오 회원 테이블에 회원 정보 저장
			if(kmember == null) {  //회원테이블에 회원 정보가 없다면
				Member kakaovo = new Member();
				//properties 에서 하나씩 꺼내서 member 에 저장 처리
				/* kakaovo.setMemberID(id); */
				
				/* kakaovo.setMemberName((String)properties.get("profile_image").asText()); */
				String email = ((String)kakao_account.get("email").asText());
				String signType = "kakao";
				logger.info("7. kcallback.do : " + id);
				logger.info("7. kcallback.do : " + email);
				
				/* model.addAttribute("kakaovo", kakaovo); */
				return "redirect:enrollPage.do?id=" + id + "&email=" + email + "&signType=" + signType;
				
		
				
			}else {// 카카오 로그인 성공시
				loginMember = kmember;

					session.setAttribute("loginMember", loginMember);
					return "redirect:home.do";
			}
			
			
			
		}

		// 네이버 로그인 요청 처리용
				// (네이버 로그인 클릭시 전달된 naverurl 에 의해 실행됨)
	@RequestMapping(value = "ncallback.do", method = { RequestMethod.GET, 	RequestMethod.POST })
	public String naverLogin(Model model, 
							HttpSession session, 
							@RequestParam String code, 
							@RequestParam String state ) throws IOException, ParseException, org.json.simple.parser.ParseException {

		System.out.println("session : " + session);
		System.out.println("session : " + state);
		
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginAuth.getAccessToken(session, code, state);
		System.out.println("session//// : " + session);
		System.out.println("oautnToken : " + oauthToken);
		
		//1. 로그인 사용자 정보를 읽어온다.
		apiResult = naverLoginAuth.getUserProfile(oauthToken); //String형식의 json데이터
		
		
		
		/** apiResult json 구조
		{"resultcode":"00",
		"message":"success",
		"response":{"id":"33666449","nickname":"shinn****","age":"20-29","gender":"M","email":"sh@naver.com","name":"\uc2e0\ubc94\ud638"}}
		**/
		
		//2. String형식인 apiResult를 json형태로 바꿈
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;
		
		//3. 데이터 파싱
		//Top레벨 단계 _response 파싱
		JSONObject response_obj = (JSONObject)jsonObj.get("response");
		
		
		
		//response의 값 파싱
		String id = (String)response_obj.get("id");
		/* String name = (String)response_obj.get("name"); */
		String email = (String)response_obj.get("email");
		/*
		 * String gender = (String)response_obj.get("gender"); String birthyear =
		 * (String)response_obj.get("birthyear"); String birthday =
		 * (String)response_obj.get("birthday");
		 */

		String signType = "naver";
		
		
		System.out.println("id라네 : " + id);
		
		//4.파싱된걸 세션으로 저장
		session.setAttribute("sessionId",id); 
		/* session.setAttribute("sessionName",name); */
		session.setAttribute("sessionEmail",email);
		session.setAttribute("sessionSignType",signType);
		/*
		 * session.setAttribute("sessionGender",gender);
		 * session.setAttribute("sessionBirthyear",birthyear);
		 * session.setAttribute("sessionBirthday",birthday);
		 */


		
		Member nmember = memberService.selectKakaoLogin(id);
		
		
		
		
		Member loginMember = null;
		
		if(nmember == null) {  //회원테이블에 회원 정보가 없다면
			
			
			return "redirect:enrollPage.do?id="
					+ id /* + "&name=" + name */
					+ "&email=" + email + "&signType=" + signType; /*+"&gender=" + gender +"&birthyear=" + birthyear 
					+"&birthday=" + birthday;*/
			
		}else {
			loginMember = nmember;

				session.setAttribute("loginMember", loginMember);
				return "redirect:home.do";
		}
	}




		
		
	

	// 회원 가입 요청 처리용 메소드
	@RequestMapping(value = "enroll.do", method = RequestMethod.POST)
	public String memberInsertMethod(Member member,
			@RequestParam("photoFile") MultipartFile file,
			@RequestParam("road") String road, @RequestParam("street") String street,
			@RequestParam("detail") String detail, @RequestParam("ref") String ref, Model model) throws Exception {
		logger.info("enroll.do : " + member);

		
		String filename = file.getOriginalFilename();
		
		member.setPhotoFilename(filename);
		
		// 패스워드 암호화 처리
		member.setMemberPWD(bcryptPasswordEncoder.encode(member.getMemberPWD()));
		logger.info("after encode : " + member.getMemberPWD());
		logger.info("pwd length : " + member.getMemberPWD().length());

		// 주소 합치기
		if (road != null && street != null && detail != null) {
			String addressAdd = road + " " + street + " " + detail + " " + ref;
			member.setAddress(addressAdd);
		}
		
		int dup = memberService.selectCheckId(member.getMemberID());
		
	
		if (memberService.insertMember(member) > 0 && dup == 0 ) {
			return "redirect:moveLoginPage.do";
		} else {
			model.addAttribute("message", "회원 가입 실패! 확인하고 다시 가입해 주세요.");
			return "redirect:moveEnrollPage.do";
		}
	}

	
	
	
	
	
	
	// ajax 통신으로 가입할 아이디 중복 확인 요청 처리용 메소드
	@RequestMapping(value = "idchk.do", method = RequestMethod.POST)
	public void dupCheckId(@RequestParam("memberID") String memberid, HttpServletResponse response) throws IOException {

		String returnStr = null;
		
		
		if(memberid.length() == 0) {
			returnStr = "null";
		}else {
			int idCount = memberService.selectCheckId(memberid);

			if (idCount == 0) {
				returnStr = "ok";
			} else {
				returnStr = "dup";
			}
		}
		// response 를 이용해서 클라이언트와 출력스트림을 열어서 문자열값 내보냄
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.append(returnStr);
		out.flush();
		out.close();
	}

	
	
	// ajax 통신으로 가입할 닉네임 중복 확인 요청 처리용 메소드
	@RequestMapping(value = "nicnamechk.do", method = RequestMethod.POST)
	public void dupNicnameCheck(@RequestParam("memberNicname") String memberNicname, HttpServletResponse response) throws IOException {
		
		
		String returnStr = null;
		int nicnameCount = memberService.selectCheckNicname(memberNicname);
		
		if(memberNicname != "" && nicnameCount == 0) {
			returnStr = "ok";
		}
		if (memberNicname != "" && nicnameCount != 0) {
		
				returnStr = "dup";
		}
		if (memberNicname == ""){
			returnStr = "null";
		}
		// response 를 이용해서 클라이언트와 출력스트림을 열어서 문자열값 내보냄
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.append(returnStr);
		out.flush();
		out.close();
	}

	
	
	
	
	
	
	
	// ajax 통신으로 가입할 이메일 중복 확인 요청 처리용 메소드
	@RequestMapping(value = "emailchk.do", method = RequestMethod.POST)
	public void dupCheckEmail(@RequestParam("email") String email, HttpServletResponse response) throws IOException {

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

	// 아이디 찾기에서 이름, 이메일 같은회원 > 아이디확인 페이지로 감
	@RequestMapping(value = "emailIdChk.do", method = RequestMethod.POST)
	public String emailIdChk(Member member, Model model) throws Exception {
		logger.info("emailIdChk.do : " + member);
		
		Member member2 = memberService.selectFindId(member);
		
		if ( member2 != null) {
			model.addAttribute("member2",member2);
			logger.info("member2 : " + member2.getMemberID());
			return "member/idChkPage";
		} else {
			model.addAttribute("message", "없는 회원입니다. 다시 확인해 주세요!");
			return "member/findIDPage";
		}
	}
	
	
	// ajax 통신으로 찾을 비밀번호의 아이디 확인 요청 처리용 메소드
	@RequestMapping(value = "dupIdCheck.do", method = RequestMethod.POST)
	public void dupIdCheck(@RequestParam("memberID") String memberid, HttpServletResponse response) throws IOException {

		int idCount = memberService.selectCheckId(memberid);

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
	
	
	
	//비밀번호 찾기에서 이름, 이메일 같은 회원 > 아이디확인도하고 비번 재설정 페이지로 감
	@RequestMapping(value = "emailIdChk2.do", method = RequestMethod.POST)
	public String emailIdChk2(Member member, Model model) throws Exception {
		System.out.println("emailIdChk.do : " + member);
		
		Member member2 = memberService.selectFindId(member);
		
		if ( member2 != null) {
			model.addAttribute("member2",member2);
			logger.info("member2 : " + member2);
			return "member/pwUpdatePage";
		} else {
			model.addAttribute("message", "없는 회원입니다. 다시 확인해 주세요!");
			return "member/findPWPage2";
		}
	}

	

	
	
	
		
	//비밀번호 재설정
	@RequestMapping(value = "pwChange.do", method = RequestMethod.POST)
	public String pwChange(Member member, Model model) throws Exception {
		System.out.println("pwChange.do : " + member);
		
		
		// 패스워드 암호화 처리
		member.setMemberPWD(bcryptPasswordEncoder.encode(member.getMemberPWD()));
		System.out.println("after encode : " + member.getMemberPWD());
		System.out.println("pwd length : " + member.getMemberPWD().length());
		
		
		logger.info("반환값 : " + memberService.updatePw(member));
		
		
		System.out.println("반환값 : " + memberService.updatePw(member));
		
		if ( memberService.updatePw(member) > 0) {

			return "member/moveLoginPage";
		} else {
			model.addAttribute("message", "없는 회원입니다. 다시 확인해 주세요!");
			return "member/pwUpdatePage";
		}
	}
		
	
	
	
	@Autowired
	JavaMailSenderImpl mailSender;

	// 이메일 인증
	@PostMapping("emailAuth.do")
	@ResponseBody
	public String emailAuth(String email) {

		logger.info("전달 받은 이메일 주소 : " + email);

		// 난수의 범위 111111 ~ 999999 (6자리 난수)
		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111;

		// 이메일 보낼 양식
		String setFrom = "noramore12@naver.com"; // 2단계 인증 x, 메일 설정에서 POP/IMAP 사용 설정에서 POP/SMTP 사용함으로 설정o
		String toMail = email;
		String title = "회원가입 인증 이메일 입니다.";
		String content = "인증 코드는 " + checkNum + " 입니다." + "<br>" + "해당 인증 코드를 인증 코드 확인란에 기입하여 주세요.";

		System.out.println("메일 내용 : " + content + "보낸에메일 : " + setFrom);
		try {
			MimeMessage message = mailSender.createMimeMessage(); // Spring에서 제공하는 mail API
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content, true);
			mailSender.send(message);
			logger.info("랜덤숫자!!! : " + checkNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONArray jarr = new JSONArray();
		JSONObject checkObject = new JSONObject();
		checkObject.put("code", checkNum);

		logger.info("랜덤숫자 : " + checkNum);
		return checkObject.toJSONString();
	}

	// 이메일 인증 >> 아이디 찾기
	@PostMapping("emailAuth2.do")
	@ResponseBody
	public String emailAuth2(String email) {

		logger.info("전달 받은 이메일 주소 : " + email);

		// 난수의 범위 111111 ~ 999999 (6자리 난수)
		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111;

		// 이메일 보낼 양식
		String setFrom = "noramore12@naver.com"; // 2단계 인증 x, 메일 설정에서 POP/IMAP 사용 설정에서 POP/SMTP 사용함으로 설정o
		String toMail = email;
		String title = "회원가입 인증 이메일 입니다.";
		String content = "인증 코드는 " + checkNum + " 입니다." + "<br>" + "해당 인증 코드를 인증 코드 확인란에 기입하여 주세요.";

		System.out.println("메일 내용 : " + content + "보낸에메일 : " + setFrom);
		try {
			MimeMessage message = mailSender.createMimeMessage(); // Spring에서 제공하는 mail API
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content, true);
			mailSender.send(message);
			logger.info("랜덤숫자!!! : " + checkNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONArray jarr = new JSONArray();
		JSONObject checkObject = new JSONObject();
		checkObject.put("code", checkNum);

		logger.info("랜덤숫자 : " + checkNum);
		return checkObject.toJSONString();
	}
	
	
	//내 프로필 가기
	@RequestMapping(value = "myinfo.do", method = { RequestMethod.GET, RequestMethod.POST })
	// RequestMethod.GET : get방식으로 전송오면 받음, RequestMethod.POST : post방식으로 전송오면 받음
	public String memberDetailPage(@RequestParam("memberID") String memberid, Model model) {
		
		Member member = memberService.selectMember(memberid);
		member.getBirth().toString();
		if (member != null) {
			model.addAttribute("member", member); // 꺼낼 때는 여기서 저장한 이름으로 꺼냄
			return "member/memberDetailPage";
		} else {
			return "redirect:home.do";
		}
	}
	
		
		

	//회원정보 수정페이지로 이동
	@RequestMapping(value = "updatePage.do", method = { RequestMethod.GET, RequestMethod.POST })
	// RequestMethod.GET : get방식으로 전송오면 받음, RequestMethod.POST : post방식으로 전송오면 받음
	public String updatePage(Member member, Model model) {
		
		
		String memberid = member.getMemberID();
		Member member1 = memberService.selectMember(memberid);
			
	
		if( member != null) { 
			model.addAttribute("member", member1); // 꺼낼 때는 여기서 저장한 이름으로 꺼냄
			return "/member/memberUpdatePage";
		
		}else {
			model.addAttribute("message", "비밀번호를 다시 입력해주세요.");
			return "member/memberDetailPage";
		} 
	}
		
	
		
		

	//회원정보 수정페이지 처리
	@RequestMapping(value = "memberUpdate.do", method = RequestMethod.POST)
	// RequestMethod.GET : get방식으로 전송오면 받음, RequestMethod.POST : post방식으로 전송오면 받음
	public String memberUpdate(Member member, Model model,
			@RequestParam("memberNicname") String memberNicname,
			@RequestParam("originPwd") String originPwd,
			@RequestParam("road") String road, @RequestParam("street") String street,
			@RequestParam("detail") String detail, @RequestParam("ref") String ref) {
		
		
		System.out.println("originPwd : " + originPwd);
	
		String addressAdd = road + street + detail + ref;
		
		System.out.println("주소 : " + member.getAddress());
		
		
//		if(memberNicname == member.getMemberNicname())
//			member.setMemberNicname(memberNicname);
			
		
		//주소
		if (addressAdd == "" || addressAdd == null) {
			/* String address = member.getAddress(); */
			member.setAddress(member.getAddress());
			
				
		}else {	
			member.setAddress(addressAdd);
		}
	
		
		System.out.println("id라네" + member.getMemberID());
		
		//새로운 암호가 전송이 왔다면, 패스워드 암호화 처리함
		String memberPWD = member.getMemberPWD().trim();
		logger.info("새로운 암호 : " + memberPWD + ", " + memberPWD.length());
		
		if(memberPWD != null && memberPWD.length() > 0) {
			//암호화된 기존의 패스워드와 새로운 패스워드를 비교해서 다른 값이면
			if(!this.bcryptPasswordEncoder.matches(memberPWD, originPwd)) {
				//member 에 새로운 패스워드를 암호화해서 저장함
			 member.setMemberPWD(this.bcryptPasswordEncoder.encode(memberPWD));
				System.out.println("member!!!! : " + member);
			}
		}else {  //새로운 암호가 null 또는 글자갯수가 0일때는
			//기존 암호이면, 원래 암호화된 패스워드를 저장함
			member.getMemberPWD();
		System.out.println("member.getMemberPWD();" + member.getMemberPWD());
		}
		
		if(memberService.updateMember(member) > 0) {
			//뷰리졸버로 리턴하지 않고, 바로 컨트롤러의 다른 메소드를 실행할 경우
			return "member/memberDetailPage";
			//만약, 회원정보 수정 성공시 마이페이지로 이동되게 한다면
			//return "redirect:myinfo.do?userId=" + member.getUserId();
		}else {
			model.addAttribute("message", "회원 정보 수정 실패!");
			return "member/memberUpdatePage";
		}
	}


		
	//회원프로필 변경처리
	@RequestMapping(value = "profileUpdate.do", method =  RequestMethod.POST )
	// RequestMethod.GET : get방식으로 전송오면 받음, RequestMethod.POST : post방식으로 전송오면 받음
	public String updatePhotofile(Member member, 
								@RequestParam("photoFile") MultipartFile file,
								@RequestParam("memberID") String memberid,
								
									Model model) {
		
		System.out.println(" 파일이지롱 : " + file + "  " + memberid);
		
		String filename = file.getOriginalFilename();
		
		member.setPhotoFilename(filename);
		
		
		member.setMemberID(memberid);
		System.out.println("member : " + member);
		
		int photofileNameUpdate = memberService.updatePhotofileName(member);
			
	
		if( photofileNameUpdate > 0) { 
			
			return "/member/memberUpdatePage";
		
		}else {
			model.addAttribute("message", "프로필사진 저장에 실패하였습니다. 다시 넣어주세요.");
			return "member/memberDetailPage";
		} 
	}

		
		
	//활동기록 페이지
	@RequestMapping(value = "articel.do", method = { RequestMethod.GET, RequestMethod.POST })
	// RequestMethod.GET : get방식으로 전송오면 받음, RequestMethod.POST : post방식으로 전송오면 받음
	public String myArticlePage(@RequestParam("memberID") String memeberid, Model model) {
		
		Member member = memberService.selectMember(memeberid);
		
		if (member != null) {
			model.addAttribute("member", member); // 꺼낼 때는 여기서 저장한 이름으로 꺼냄
			return "member/myArticlePage";
		} else {
			return "redirect:home.do";
		}
	}
		
		
		
//	@RequestMapping(value="otherMember.do", method=RequestMethod.POST)
//	@ResponseBody      //response 객체에 JSONString 담아 보내기 위함
//	public String otherMemberBring(@RequestParam("memberId") String otherID, Model model) {
//	 
//		System.out.println("otherID: " + otherID);
//		
//		Member member = memberService.selectMember(otherID);
//		
//		MemAdd memAdd = memAddService.selectMemAdd(otherID); 
//		  
//		
//		JSONObject job = new JSONObject(); 
//		job.put("grade", memAdd.getGrade());
//		job.put("heart", memAdd.getHeartBeat());
//		job.put("id", member.getMemberID());
//		job.put("photoFile", member.getPhotoFilename());
//
//	
//		return job.toJSONString();
//	}
	
	
	//siderbar로 필요한 정보 가져옴
	@RequestMapping(value="memberProfile.do", method=RequestMethod.POST)
	@ResponseBody      //response 객체에 JSONString 담아 보내기 위함
	public String memberProfile(@RequestParam("memberid") String memberid, Model model) {
	 
		System.out.println("otherID: " + memberid);
		
		Member member = memberService.selectMember(memberid);
		
		  
		
		JSONObject job = new JSONObject(); 
		job.put("id", member.getMemberID());
		job.put("photoFile", member.getPhotoFilename());
		job.put("membernicname", member.getMemberNicname());
	
		System.out.println("member.getMemberID() :  " + member.getMemberID());
		System.out.println("member.getMemberNicname() :  " + member.getMemberNicname());
		
		
		return job.toJSONString();
	}	
	
	
	
	//회원탈퇴 페이지로 이동
		@RequestMapping(value = "moveResign.do", method = { RequestMethod.GET, RequestMethod.POST })
		// RequestMethod.GET : get방식으로 전송오면 받음, RequestMethod.POST : post방식으로 전송오면 받음
		public String moveResignPage(@RequestParam("memberID") String memberid, Model model) {
			System.out.println("memberid : " + memberid);
			System.out.println();
			
			
			Member member = memberService.selectMember(memberid);
			
			System.out.println("member : " + member);
			if (member != null) { 
				model.addAttribute("member", member); // 꺼낼 때는 여기서 저장한 이름으로 꺼냄
				return "member/resignPage";
			} else {
				return "redirect:home.do";
			}
		}

		//회원탈퇴 처리용
		@RequestMapping(value = "ResignProcess.do", method = { RequestMethod.GET, RequestMethod.POST })
		// RequestMethod.GET : get방식으로 전송오면 받음, RequestMethod.POST : post방식으로 전송오면 받음
		public String ResignProcess(Member member, Model model,
									@RequestParam("originPWD") String originPWD) {
				
			String memberPWD = member.getMemberPWD();  //입력한 암호

			String memberid = member.getMemberID();
			int updateResign = memberService.updateResign(memberid); //탈퇴필드 업데이트

			System.out.println("updateResign111 : " + updateResign);
			
			if (this.bcryptPasswordEncoder.matches(memberPWD, originPWD) && updateResign > 0) { 

				
				return "redirect:logout.do"; 
			} else {
				model.addAttribute("message", "비밀번호가 틀렸거나 관리자에게 문의해 주세요"); 
				return "member/resignPage";
			}
		}
		
		
		
		
		
		

}
