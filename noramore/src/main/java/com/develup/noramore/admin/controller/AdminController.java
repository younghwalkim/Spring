package com.develup.noramore.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.develup.noramore.admin.model.service.AdminService;
import com.develup.noramore.admin.model.vo.BoardManage;
import com.develup.noramore.admin.model.vo.Flow;
import com.develup.noramore.admin.model.vo.ReportMember;
import com.develup.noramore.common.Paging;
import com.develup.noramore.common.Search;
import com.develup.noramore.common.SearchDate;
import com.develup.noramore.member.model.vo.Member;

@Controller
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	//-----------------------------------------------------------------------------------------------
	//페이지별 목록 조회
	
	//관리자 페이지 메인 (가입&탈퇴 유동 목록)
	@RequestMapping("adminPage.do")
	public String selectEnrollandWithdrawalFlowList(HttpServletRequest request, Model model) {

			Flow flow = adminService.selectEWFlowCount();
			int mlistCount = adminService.selectReportedMemListCount();
			int blistCount = adminService.selectReportedListCount();
			model.addAttribute("flow", flow);
			model.addAttribute("mlist", mlistCount);
			model.addAttribute("blist", blistCount);
			
			return "admin/adminView";
	}

	//회원 목록 조회
	@RequestMapping("memberlist.do")
	public String selectMemberManageList(
			@RequestParam(name="page", required=false) String page,
			 @RequestParam(name="limit", required=false) String slimit,  Model model) {
		
		 int currentPage = 1;
		 if(page != null && page.trim().length() > 0) {
			 currentPage = Integer.parseInt(page);
		 }
		 
		 int limit = 10;
		 if(slimit != null && slimit.trim().length() > 0) {
			 limit = Integer.parseInt(slimit); 
		 }
		 
		 int listCount = adminService.selectListCount(); //페이징 계산 처리 실행
		 Paging paging = new Paging(listCount, currentPage, limit, "memberlist.do");
		 paging.calculate();
		 System.out.println(paging.getUrlMapping());
		 //목록 조회
		List<ReportMember> list = adminService.selectMemList(paging);

		if(list != null & list.size() >0) {
			model.addAttribute("list", list);
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			return "admin/memManageView";
		}else {
			model.addAttribute("error", "Error : 회원 목록 조회 실패");
			return "common/error";
		}

	}

	//회원 검색
	@RequestMapping(value="memSearch.do", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView selectSearchMemberList(
			HttpServletRequest request, ModelAndView mv) {
	
		//전송온 값 꺼내기
		String action = request.getParameter("action");
		//필요한 변수 선언
		String keyword = null, begin = null, end = null;
		SearchDate searchDate = null;
		
		if(action.equals("enrolldate")) {
			begin = request.getParameter("begin");
			end = request.getParameter("end");
			searchDate = new SearchDate(Date.valueOf(begin), Date.valueOf(end));
		}else {
			keyword = request.getParameter("keyword");
		}
		
		//검색 결과에 대한 페이징 처리
		//출력할 페이지 지정
		int currentPage = 1;
		//전송온 페이지 값이 있다면 
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		
		//한 페이지에 출력할 목록 갯수 지정
		int limit = 10;
		//전송온 limit 값이 있다면
		if(request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit"));
		}
		System.out.println(action);
		//총 페이지수 계산을 위해 검색 결과가 적용된 총 목록 갯수 조회
		int listCount = 0;
		switch(action) {
		case "id":  		
			listCount = adminService.selectSearchMemberIdCount(keyword); System.out.println(listCount); break;
		case "gender":  	
			listCount = adminService.selectSearchGenderCount(keyword);  break;
		case "enrolldate": 
			listCount = adminService.selectSearchEnrollDateCount(searchDate);  break;
		}
		
		//뷰 페이지와 쿼리문에서 사용할 페이징 관련 값 계산 처리
		Paging paging = new Paging(listCount, currentPage, limit, "memSearch.do");
		paging.calculate();
		
		//서비스 메소드 호출하고 리턴 결과 받기
		List<ReportMember> list = null;
		
		//마이바티스 매퍼 쿼리문으로는 객체 한 개만 전달할 수 있음 => 별도의 클래스 만들어서 사용함
		Search search = new Search();
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());
		
		switch(action) {
		case "id":  		search.setKeyword(keyword);
						list = adminService.selectSearchMemberId(search);  break;
		case "gender":  	search.setKeyword(keyword);
						list = adminService.selectSearchGender(search);  break;
		case "enrolldate":  search.setBegin(Date.valueOf(begin));
		                search.setEnd(Date.valueOf(end));
						list = adminService.selectSearchEnrollDate(search);  break;
		}
		
		//받은 결과에 따라 성공/실패 페이지 내보내기
		if(list != null && list.size() > 0) {
			mv.addObject("list", list);
			mv.addObject("paging", paging);
			mv.addObject("currentPage", currentPage);
			mv.addObject("limit", limit);
			mv.addObject("action", action);
			
			if(keyword != null) {
				mv.addObject("keyword", keyword);
			}else {
				mv.addObject("begin", begin);
				mv.addObject("end", end);
			}
			
			mv.setViewName("admin/memManageView");
		}else {
			if(keyword != null) {
				mv.addObject("error", action + "에 대한 " + keyword 
						+ " 검색결과가 존재하지 않습니다.");
			}
			mv.setViewName("admin/memManageView");
		}
		
		return mv;
		
		
	}
	
	
	//신고 회원 목록 조회
	@RequestMapping("reportedMemlist.do")
	public String selectReportedMemList(
			@RequestParam(name="page", required=false) String page, 
			@RequestParam(name="limit", required=false) String slimit, Model model) {
		 int currentPage = 1;
		 if(page != null && page.trim().length() > 0) {
			 currentPage = Integer.parseInt(page);
		 }
		 
		 int limit = 10;
		 if(slimit != null && slimit.trim().length() > 0) {
			 limit = Integer.parseInt(slimit); 
		 }
		 
		 int listCount = adminService.selectReportedMemListCount(); //페이징 계산 처리 실행
		 Paging paging = new Paging(listCount, currentPage, limit, "reportedMemlist.do");
		 paging.calculate();
		 
		 //목록 조회
		List<ReportMember> list = adminService.selectReportedMemList(paging);
		
		if(list != null & list.size() >0) {
			model.addAttribute("list", list);
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			return "admin/reportedMemView";
		}else {
			model.addAttribute("message", "Error : 신고 회원 목록 조회 실패");
			return "admin/reportedMemView";
		}
		
	}

	
	//신고 게시글 목록 조회
	@RequestMapping("reportedBoardlist.do")
	public String selectRecrBoardManageList(
			@RequestParam(name="page", required=false) String page, 
			@RequestParam(name="limit", required=false) String slimit, Model model) {

		int currentPage = 1;
		if(page != null && page.trim().length() > 0) {
			currentPage = Integer.parseInt(page);
		}
		
		//한 페이지에 게시글 10개씩 출력되게 한다면
		int limit = 10;
		if(slimit != null && slimit.trim().length() > 0) {
			limit = Integer.parseInt(slimit);  //전송받은 한 페이지에 출력할 목록 갯수를 적용
		}
		
		//총페이지수 계산을 위해 게시글 전체 갯수 조회해 옴
		int listCount = adminService.selectReportedListCount();
		//페이징 계산 처리 실행
		Paging paging = new Paging(listCount, currentPage, limit, "reportedBoardlist.do");
		paging.calculate();

		List<BoardManage> list = adminService.selectReportedList(paging);

		if(list != null && list.size() > 0) {
			model.addAttribute("list", list);
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);

		}else {
			model.addAttribute("message", currentPage + " 페이지 목록 조회 실패!");

		}
		return "admin/reportedBoardView";
	}

		
	//-------------------------------------------------------------------------------------------------
	// DML (UPDATE, DELETE)
	
	//회원 활동 제한
	@RequestMapping(value="restrict.do", method=RequestMethod.POST)
	public void updateRestrict(
			@RequestParam("memberID") String memberID, 
			@RequestParam("restrict") String restrict, HttpServletResponse response) throws IOException {

		Member member = new Member();
		member.setMemberID(memberID);
		member.setActLimit(restrict);
		
		String returnStr = null;
		if(adminService.updateRestrict(member) > 0) {
			adminService.updateReport(memberID);
			returnStr = "restrict";
		}else {
			returnStr = "failed";
		}
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.append(returnStr);
		out.flush();
		out.close();
		}
	
	//회원 활동 제한 해제
	@RequestMapping(value="restrictrollback.do", method=RequestMethod.POST)
	public void rollbackRestrict(
			@RequestParam("memberID") String memberID, 
			@RequestParam("restrict") String restrict, HttpServletResponse response) throws IOException {

		
		Member member = new Member();
		member.setMemberID(memberID);
		member.setActLimit(restrict);
		
		String returnStr = null;
		if(adminService.updateRestrictRollback(member) > 0) {
			returnStr = "rollback";
		}else {
			returnStr = "failed";
		}
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.append(returnStr);
		out.flush();
		out.close();
		}
	
}
