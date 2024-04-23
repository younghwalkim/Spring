package com.develup.noramore.forbidden.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.develup.noramore.common.Paging;
import com.develup.noramore.common.Search;
import com.develup.noramore.forbidden.model.service.ForbiddenService;
import com.develup.noramore.forbidden.model.vo.Forbidden;

@Controller
public class ForbiddenController {
	private static final Logger logger = LoggerFactory.getLogger(ForbiddenController.class);
	@Autowired
	private ForbiddenService forbiddenService;
	
	//금지어 전체 조회
	@RequestMapping("fblist.do")
	public String forbiddenList(
						 @RequestParam(name="page", required=false) String page,
						 @RequestParam(name="limit", required=false) String slimit, Model model) {

		 int currentPage = 1;
		 if(page != null && page.trim().length() > 0) {
			 currentPage = Integer.parseInt(page);
		 }
		 
		 //한 페이지에 게시글 10개씩 출력되게 한다면
		 int limit = 10;
		 if(slimit != null && slimit.trim().length() > 0) {
			 limit = Integer.parseInt(slimit); //전송받은 한 페이지에 출력할 목록 갯수를 적
		 }
		 
		 //총페이지수 계산을 위해 게시글 전체 갯수 조회해 옴
		 int listCount = forbiddenService.selectListCount(); //페이징 계산 처리 실행
		 Paging paging = new Paging(listCount, currentPage, limit, "fblist.do");
		 paging.calculate();
		 
		 //출력할 페이지에 대한 목록 조회
		 ArrayList<Forbidden> list = forbiddenService.selectList(paging);
		 
		//받은 결과로 성공/실패 페이지 내보냄
		if(list != null && list.size() > 0) {
			model.addAttribute("list", list);
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);
			
			return "forbidden/forbiddenListView";
		}else {
			model.addAttribute("message", currentPage + " 페이지 목록 조회 실패!");
			return "common/error";
		}
	}
	
	//금지어 검색
	@RequestMapping(value="fbsearch.do", method= {RequestMethod.POST, RequestMethod.GET})
	public String forbiddenSearch(
			@RequestParam("action") String action,
			@RequestParam("keyword") String keyword,
			@RequestParam(name="page", required=false) String page,
			 @RequestParam(name="limit", required=false) String slimit, Model model) {
		
		int currentPage = 1;
		 if(page != null && page.trim().length() > 0) {
			 currentPage = Integer.parseInt(page);
		 }
		 
		 //한 페이지에 게시글 10개씩 출력되게 한다면
		 int limit = 10;
		 if(slimit != null && slimit.trim().length() > 0) {
			 limit = Integer.parseInt(slimit); //전송받은 한 페이지에 출력할 목록 갯수를 적
		 }
		 
		 //총페이지수 계산을 위해 게시글 전체 갯수 조회해 옴
		 int listCount = forbiddenService.selectSearchForbiddenCount(keyword); //페이징 계산 처리 실행
			
		 Paging paging = new Paging(listCount, currentPage, limit, "fbsearch.do");
		 paging.calculate();
		 
		 //출력할 페이지에 대한 목록 조회
		Search search = new Search();
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());
		search.setKeyword(keyword);

		 ArrayList<Forbidden> list = forbiddenService.selectSearchForbidden(search);
		 
		 if(list != null && list.size() > 0 && keyword != "") {
			model.addAttribute("list", list);
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("action", action);
			model.addAttribute("keyword", keyword);
			model.addAttribute("limit", limit);
			return "forbidden/forbiddenListView";
		 }else if(keyword == ""){
			 return "redirect:fblist.do";
		 }else {
			model.addAttribute("message", keyword + "에 해당하는 검색결과가 없습니다.");
			return "forbidden/forbiddenListView";
		}

	}
	
	//금지어 추가시 중복검사
	@RequestMapping(value="fbchk.do", method=RequestMethod.POST)
	public void dupCheckIdMethod(@RequestParam("fbWord") String fbWord, 
			HttpServletResponse response) throws IOException {
		int fbCount = forbiddenService.selectCheckFb(fbWord);
		
		String returnStr = null;
		if(fbWord != "" && fbCount == 0) {		// 빈칸 아니면서 중복값 없을 때
			returnStr = "ok";
		}else if(fbWord != "" && fbCount != 0){		// 입력값이 중복값일 때
			returnStr = "dup";
		}else {					//빈칸인 경우
			returnStr = "none";
		}
		
		//response 를 이용해서 클라이언트와 출력스트림을 열어서 문자열값 내보냄
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.append(returnStr);
		out.flush();
		out.close();
	}
	
	//금지어 추가(팝업)
	@RequestMapping("fbinsert.do")
	public String forbiddenInsert(@RequestParam("fbWord") String fbWord) {
		logger.info("fbinsert.do : " + fbWord);
		
			if(forbiddenService.insertForbidden(fbWord) > 0) {
				return "redirect:fblist.do";
			}else {
				//중복 상황 제외 실패
				return "common/error";
		}
	}
	
	//금지어 삭제
	@RequestMapping("fbdelete.do")
	public void forbiddenDelete(
			@RequestParam("fbWord") String fbWord,
			HttpServletResponse response) throws IOException {
		
		String returnStr = null;
		if(forbiddenService.selectCheckFb(fbWord) == 1) {
			forbiddenService.deleteForbidden(fbWord);
			returnStr = "delete";
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
