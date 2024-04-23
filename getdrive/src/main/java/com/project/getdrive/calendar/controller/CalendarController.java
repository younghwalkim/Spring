package com.project.getdrive.calendar.controller;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.getdrive.calendar.service.CalendarService;
import com.project.getdrive.calendar.vo.Calendar;
import com.project.getdrive.common.CommonCL;
import com.project.getdrive.member.model.vo.Member;


//캘린더 전체 출력
@Controller
public class CalendarController {
	
	
	@Autowired
	private CalendarService calendarService;
	
	//달력창 호출
	@RequestMapping(value = "calendar.do", method = RequestMethod.GET)
	public String calendarMain() {			
		return "calendar/calendar";		
	}
	
	//스케줄 표시 처리용
	@SuppressWarnings("unchecked")
	@RequestMapping(value="cllist.do", method=RequestMethod.POST)
	@ResponseBody
	public String scheduleListMethod(
		@RequestParam("year") int year,
		@RequestParam("month") int month,
		@RequestParam("tNo") int tNo,
		HttpServletResponse response) throws UnsupportedEncodingException {
		
		//두개 이상의 객체를 사용하기 위해 CommonCL 클래스에 합침
		CommonCL commonCL = new CommonCL();
		commonCL.setMonth(month);
		commonCL.setYear(year);
		commonCL.settNo(tNo);
		
		Member member= new Member();
		
		member.getAccountNo();
			
		ArrayList<Calendar> list = calendarService.scheduleList(commonCL);
		
		//list 를 json 배열에 옮기기
		JSONArray jarr = new JSONArray();
		
		for(Calendar calendar : list) {

	        JSONObject job = new JSONObject();
	        
	        job.put("clnum", calendar.getCalendarNo());
	        // 한글에 대해서는 인코딩해서 json에 담음 (한글 깨짐 방지)
	        job.put("cltitle", URLEncoder.encode(calendar.getCalendarTitle(), "utf-8"));
	        
	        job.put("cldate", URLEncoder.encode(calendar.getCalendarDate(), "utf-8"));
	                
	        job.put("clday", calendar.getDay());
	        
	        // job를 jarr에 추가
	        jarr.add(job);

		}
		//전송용 json 객체 준비
		JSONObject sendJson = new JSONObject();
		//전송용 객체에 jarr 을 담음
		sendJson.put("list", jarr);
		
		//전송용 json 을 json string 으로 바꿔서 전송되게 함
		return sendJson.toJSONString();
		
	}
	
	//등록된 스케줄 일정보기
	@RequestMapping("cldetail.do")
	public String scheduleDetailMethod(
		@RequestParam("clnum") int calendarNo,
		@RequestParam(name="page", 
		required=false)
		String page, Model model) {

		//조회 처리용
		Calendar calendar = calendarService.scheduleView(calendarNo);
		
		
		if(calendar != null) {
			model.addAttribute("calendar", calendar);
				
			return "calendar/calendarDetailView";
				
		}else {
			model.addAttribute("message", calendarNo + "번 게시글 상세보기 요청 실패!");
			return "common/error";
		}
	}
		
	//스케줄 등록 페이지 내보내기용
	@RequestMapping("clinsertform.do")
	public String scheduleInsertPage(
	   @RequestParam("year") int year,
	   @RequestParam("month") int month,
	   @RequestParam("day") int day, 
	   Model model) {
 
	    // 모델에 commonCL 객체 추가
	    model.addAttribute("year", year);
	    model.addAttribute("month", month);
	    model.addAttribute("day", day);
	    
	    // 스케줄 등록 페이지 뷰 이름 반환
	    return "calendar/calendarInsertForm";
	}
	
		
	//스케줄 등록 처리용
	@RequestMapping("clinsert.do")
	public String scheduleInsertMethod(
		Calendar calendar, 
		@RequestParam("year") int year,
		@RequestParam("month") int month,		
		Model model) {
			
		if(calendarService.scheduleInsert(calendar) > 0) {
			//게시글 등록 성공시 목록 보기 페이지로 이동
			return "redirect:calendar.do?year="+ year +"&month="+ (month - 1) +"&tNo=" + calendar.getCalendarTid() ;
		} else {
			model.addAttribute("message", "스케줄 등록 실패!");
			return "common/error";	
	    }    	
	}
	
	// 스케줄 삭제 처리용
	@RequestMapping("cldelete.do")
	public String scheduleDeleteMethod(
		@RequestParam("clnum") int calendarNo,
		@RequestParam("tNo") int tNo,
		@RequestParam("year") int year,
		@RequestParam("month") int month,			
		Model model, 
		HttpServletRequest request) {
		
		if(calendarService.scheduleDelete(calendarNo) > 0) {
			//삭제 성공시 목록 페이지로 이동
			return "redirect:calendar.do?tNo=" + tNo + "&year="+ year + "&month="+ (month - 1) ;			
		} else {
			 model.addAttribute("message", calendarNo + "스케줄 삭제 실패! 권한이 없습니다 ");
			 return "common/error";
		}	
	}
	
	//스케줄 수정페이지 내보내기용
	@RequestMapping("clupview.do")
	public String scheduleUpdatePage(
		@RequestParam("clnum") int calendarNo,
		Model model) {
		
		//수정 페이지에 전달해서 출력할 스케줄 정보 조회함
		Calendar calendar = calendarService.scheduleSelect(calendarNo);
		
		if(calendar != null) {
			model.addAttribute( "calendar" ,calendar);
				
			return "calendar/calendarUpdateView";
		} else {
			model.addAttribute( "message" , calendarNo + "수정페이지로 이동 실패!");
			return "common/error";
		}
	}			
	
	//스케줄 수정 처리용
	@RequestMapping(value="clupdate.do", method=RequestMethod.POST)
	public String scheduleUpdateMethod(
		Calendar calendar, 
		HttpServletRequest request,
	    Model model) {
	      
		if(calendarService.scheduleUpdate(calendar) > 0) {
	         //수정 성공시 다시 상세보기가 보여지게 처리
	         model.addAttribute("clnum", calendar.getCalendarNo());
	         model.addAttribute("year", calendar.getYear());
	         model.addAttribute("month", calendar.getMonth());
	         
	         
			/* return "calendar/calendarDetailView"; */
	         return "redirect:cldetail.do"; //리다이렉트를 사용하면 주소창이 바뀜
	         
	    } else {
	         model.addAttribute("message", calendar.getCalendarNo() + "스케줄 수정 권한이 없습니다!");
	        return "common/error";
	    }

	}

}
