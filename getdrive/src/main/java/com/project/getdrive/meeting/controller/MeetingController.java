package com.project.getdrive.meeting.controller;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.project.getdrive.common.FileNameChange;
import com.project.getdrive.common.Paging;
import com.project.getdrive.common.SearchPaging;
import com.project.getdrive.meeting.model.service.MeetingService;
import com.project.getdrive.meeting.model.vo.Meeting;

@Controller
public class MeetingController {
	//컨트롤러 메소드 작동 확인을 위한 로그 객체
	private static final Logger logger = LoggerFactory.getLogger(MeetingController.class);
	
	@Autowired
	private MeetingService meetingService;
	
	//뷰 페이지 내보내기용 ----------------------------------------------------------------------------
	//새 공지글 등록 페이지 이동 처리용
	@RequestMapping("movewrite.do")
	public String meetMoveWritePage() {
		return "meeting/meetingWriteForm";
	}
	
	//회의글 수정페이지로 이동 처리용 ---------------------------------------------------------------------
	@RequestMapping("mmoveup.do")
	public ModelAndView moveUpdatePage(
			@RequestParam("mtId") int mtId, ModelAndView mv) {
		//수정페이지에 출력할 공지글 조회해 옴
		Meeting meeting = meetingService.selectOne(mtId);
		
		if( meeting != null) {
			mv.addObject("meeting", meeting);
			mv.setViewName("meeting/meetingUpdateView");
		}else {
			mv.addObject("message", mtId + "번 공지글 수정페이지로 이동 실패!");
			mv.setViewName("common/error");
		}
		
		return mv;
	}	
		
	//회의 전체 목록보기 요청 처리용 --------------------------------------------------------------------
	@RequestMapping("mlist.do")
	public String meetingListMethod(
			@RequestParam(name="page", required=false) String page, 
			@RequestParam(name="limit", required=false) String slimit, 
			Model model, HttpServletRequest request) {
		
		// 세션에서 tNo를 받아옴
		int tNo = (int) request.getSession().getAttribute("tNo");	
		
		int currentPage = 1;
		if (page != null) {
			currentPage = Integer.parseInt(page);
		}
		
		//한 페이지 공지 10개씩 출력되게 한다면
		int limit = 10;
		if (slimit != null) {
			limit = Integer.parseInt(slimit);
		}
		
		//총 페이지 수 계산을 위한 공지글 총갯수 조회
		int listCount = meetingService.selectListCount(tNo);
		//페이지 관련 항목 계산 처리
		Paging paging = new Paging(listCount, currentPage, limit, "mlist.do");
		paging.setTNo(tNo);
		paging.calculate();
		
		//페이지에 출력할 목록 조회해 옴
		ArrayList<Meeting> list = meetingService.selectList(paging);
		
		if(list != null && list.size() > 0) {
			model.addAttribute("list", list);
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);
			
			return "meeting/meetingListView";
		}else {
			model.addAttribute("message", "글이 없습니다. ");
			return "meeting/meetingListView";
		}
	}
	
	
	//회의록 상세보기 요청 처리용 --------------------------------------------------------------------
	@RequestMapping("mdetail.do")
	public ModelAndView meetingDetailMethod(
			@RequestParam("no") int mtId, ModelAndView mv, HttpSession session) {
		//관리자용 상세보기 페이지와 일반회원 | 비회원 상세보기 페이지 구분해서 내보냄
		//관리자인지 확인하기 위해 session 매개변수 추가함
		Meeting meeting = meetingService.selectOne(mtId);
		
		//조회수 1증가 처리
		meetingService.updateAddReadCount(mtId);
		
		if(meeting != null) {
			mv.addObject("meeting", meeting);
			mv.setViewName("meeting/meetingDetailView");
		}else {
			mv.addObject("message", mtId + "번 회의글 상세보기 조회 실패!");
			mv.setViewName("common/error");
		}
		
		return mv;
	}
	
	//첨부파일 다운로드 요청 처리용 ------------------------------------------------------------------
		@RequestMapping("mfdown.do")
		public ModelAndView fileDownMethod(
				ModelAndView mv, HttpServletRequest request, 
				@RequestParam("ofile") String originalFileName,
				@RequestParam("rfile") String renameFileName) {
			//파일 다운 메소드는 리턴 타입이 ModelAndView 로 정해져 있음
			
			//공지사항 첨부파일 저장 폴더 경로 지정
			String savePath = request.getSession().getServletContext().getRealPath(
					"resources/meeting_upfiles");
			
			//저장 폴더에서 읽을 파일에 대한 파일 객체 생성함
			File renameFile = new File(savePath + "\\" + renameFileName);
			//파일 다운시 브라우저 내보낼 원래 파일이름에 대한 파일 객체 생성함
			File originFile = new File(originalFileName);
			
			//파일 다운로드용 뷰로 전달할 정보 저장 처리
			mv.setViewName("filedown");   //등록된 파일다운로드용 뷰클래스의 id명
			mv.addObject("renameFile", renameFile);
			mv.addObject("originFile", originFile);
			
			return mv;
		}
	
	
	//새 회의록 등록 요청 처리용 (파일 업로드 기능 보유)
		@RequestMapping(value="minsert.do", method=RequestMethod.POST)
		public String meetingInsertMethod(
				Meeting meeting, 
				Model model, 
				HttpServletRequest request, 
				@RequestParam(name="ofile", required=false) MultipartFile mfile) {
			
			logger.info("minsert.do : " + meeting);
			
			//공지사항 첨부파일 저장 폴더 경로 지정
			String savePath = request.getSession().getServletContext().getRealPath(
					"resources/meeting_upfiles");
			
			//첨부파일이 있을 때
			if(!mfile.isEmpty()) {
				//전송온 파일이름 추출함
				String fileName = mfile.getOriginalFilename();
				String renameFileName = null;
				
				//저장폴더에는 변경된 이름을 저장 처리함
				//파일 이름바꾸기함 : 년월일시분초.확장자
				if(fileName != null && fileName.length() > 0) {				
					//바꿀 파일명에 대한 문자열 만들기
					renameFileName = FileNameChange.change(fileName, 	"yyyyMMddHHmmss");
					logger.info("첨부파일명 확인 : " + fileName + ", " + renameFileName);
					try {	
						//저장 폴더에 파일명 바꾸기 처리
						mfile.transferTo(new File(savePath + "\\" + renameFileName));
					
					}catch(Exception e) {
						e.printStackTrace();
						model.addAttribute("message", "첨부파일 저장 실패!");
						return "common/error";
					}
				}  //파일명 바꾸기
				//notice 객체에 첨부파일 정보 저장 처리
				meeting.setMtOriginalFileName(fileName);
				meeting.setMtRenameFileName(renameFileName);
			} //첨부파일 있을 때		
			
			if(meetingService.insertMeeting(meeting) > 0) {
				//공지글 등록 성공시 목록 보기 페이지로 이동
				return "redirect:mlist.do";
			}else {
				model.addAttribute("message", "새 회의록 등록 실패!");
				return "common/error";
			}
		}
		


	//회의글 수정 요청 처리용 (파일 업로드 기능 보유) -----------------------------------------------------------
	@RequestMapping(value="mupdate.do", method=RequestMethod.POST)
	public String meetingUpdateMethod(Meeting meeting, Model model, 
			HttpServletRequest request, 
			@RequestParam(name="deleteFlag", required=false) String delFlag,
			@RequestParam(name="upfile", required=false) MultipartFile mfile) {
		logger.info("mupdate.do : " + meeting);
		
		//공지사항 첨부파일 저장 폴더 경로 지정
		String savePath = request.getSession().getServletContext().getRealPath(
				"resources/meeting_upfiles");
		
		//첨부파일이 변경된 경우의 처리 
		//1. 원래 첨부파일이 있는데 '파일삭제'를 선택한 경우
		//   또는 원래 첨부파일이 있는데 새로운 첨부파일이 업로드된 경우
		if(meeting.getMtOriginalFileName() != null && 
				(delFlag != null && delFlag.equals("yes")) || !mfile.isEmpty()) {
			//저장 폴더에서 파일 삭제함
			new File(savePath + "\\" + meeting.getMtRenameFileName()).delete();
			//meeting 안의 파일정보도 제거함
			meeting.setMtOriginalFileName(null);
			meeting.setMtRenameFileName(null);
		}
		
		//2. 새로운 첨부파일이 있을 때 (공지글 첨부파일은 1개임)
		if(!mfile.isEmpty()) {			
			//전송온 파일이름 추출함
			String fileName = mfile.getOriginalFilename();
			String renameFileName = null;
			
			//저장폴더에는 변경된 이름을 저장 처리함
			//파일 이름바꾸기함 : 년월일시분초.확장자
			if(fileName != null && fileName.length() > 0) {				
				//바꿀 파일명에 대한 문자열 만들기
				renameFileName = FileNameChange.change(fileName, 	"yyyyMMddHHmmss");
				logger.info("첨부파일명 확인 : " + fileName + ", " + renameFileName);
				try {	
					//저장 폴더에 파일명 바꾸기 처리
					mfile.transferTo(new File(savePath + "\\" + renameFileName));
				
				}catch(Exception e) {
					e.printStackTrace();
					model.addAttribute("message", "첨부파일 저장 실패!");
					return "common/error";
				}
			}  //파일명 바꾸기
			//notice 객체에 첨부파일 정보 저장 처리
			meeting.setMtOriginalFileName(fileName);
			meeting.setMtRenameFileName(renameFileName);
		} //첨부파일 있을 때	
		
		if(meetingService.updateMeeting(meeting) > 0) {
			//공지글 수정 성공시 목록 보기 페이지로 이동
			return "redirect:mlist.do";
		}else {
			model.addAttribute("message", meeting.getMtId() + "번 회의록 수정 실패!");
			return "common/error";
		}
	}
	
	//회의글 삭제 요청 처리용 ------------------------------------------------------------------------------
	@RequestMapping("mdelete.do")
	public String noticeDeleteMethod(
			@RequestParam("mtId") int mtId,
			@RequestParam(name="rfile", required=false) String mtRenameFileName,
			HttpServletRequest request, Model model) {
		
		if(meetingService.deleteMeeting(mtId) > 0) {
			//공지글 삭제 성공시 저장 폴더에 있는 첨부파일도 삭제함
			if(mtRenameFileName != null) {
				//공지사항 첨부파일 저장 폴더 경로 지정
				String savePath = request.getSession().getServletContext().getRealPath(
						"resources/meeting_upfiles");
				//저장 폴더에서 파일 삭제함
				new File(savePath + "\\" + mtRenameFileName).delete();
			}
			
			return "redirect:mlist.do";
		}else {
			model.addAttribute("message", mtId + "번 회의글 삭제 실패!");
			return "common/error";
		}
	}
	
	//회의글 제목 검색용 (페이징 처리 포함) -------------------------------------------------------------------------
		@RequestMapping(value="msearchTitle.do", method= RequestMethod.GET)
		public ModelAndView meetingSearchTitleMethod(
				@RequestParam("action") String action,			
				@RequestParam("keyword") String keyword,
				@RequestParam(name="limit", required=false) String slimit,
				@RequestParam(name="page", required=false) String page,
				ModelAndView mv) {
			
			//검색결과에 대한 페이징 처리
			//출력할 페이지 지정
			int currentPage = 1;
			//전송온 페이지 값이 있다면 추출함
			if(page != null) {
				currentPage = Integer.parseInt(page);
			}
			
			//한 페이지당 출력할 목록 갯수 지정
			int limit = 10;
			//전송 온 limit 값이 있다면
			if(slimit != null) {
				limit = Integer.parseInt(slimit);
			}
			
			//총 페이지수 계산을 위한 검색 결과 적용된 총 목록 갯수 조회
			int listCount = meetingService.selectSearchTitleCount(keyword);
			
			//뷰 페이지에 사용할 페이징 관련 값 계산 처리
			Paging paging = new Paging(listCount, currentPage, limit, "msearchTitle.do");
			paging.calculate();
			
			//서비스 메소드 호출하고 리턴결과 받기		
			SearchPaging search = new SearchPaging();
			search.setStartRow(paging.getStartRow());
			search.setEndRow(paging.getEndRow());
			search.setKeyword(keyword);
			
			ArrayList<Meeting> list = meetingService.selectSearchTitle(search);
			
			//받은 결과에 따라 성공/실패 페이지 내보내기
			if(list != null && list.size() > 0) {
				mv.addObject("list", list);
				mv.addObject("paging", paging);
				mv.addObject("currentPage", currentPage);
				mv.addObject("limit", limit);
				mv.addObject("action", action);
				mv.addObject("keyword", keyword);			
				
				mv.setViewName("meeting/meetingListView");
			}else {
				mv.addObject("message", action + "에 대한 " 
							+ keyword + " 검색 결과가 존재하지 않습니다.");			
				mv.setViewName("common/error");
			}
			
			return mv;
		}
		
		//회의글 내용 검색용 (페이징 처리 포함)
		@RequestMapping(value="msearchContent.do", method= RequestMethod.GET)
		public ModelAndView meetingSearchContentMethod(
				@RequestParam("action") String action,			
				@RequestParam("keyword") String keyword,
				@RequestParam(name="limit", required=false) String slimit,
				@RequestParam(name="page", required=false) String page,
				ModelAndView mv) {
			
			//검색결과에 대한 페이징 처리
			//출력할 페이지 지정
			int currentPage = 1;
			//전송온 페이지 값이 있다면 추출함
			if(page != null) {
				currentPage = Integer.parseInt(page);
			}
			
			//한 페이지당 출력할 목록 갯수 지정
			int limit = 10;
			//전송 온 limit 값이 있다면
			if(slimit != null) {
				limit = Integer.parseInt(slimit);
			}
			
			//총 페이지수 계산을 위한 검색 결과 적용된 총 목록 갯수 조회
			int listCount = meetingService.selectSearchContentCount(keyword);
			
			//뷰 페이지에 사용할 페이징 관련 값 계산 처리
			Paging paging = new Paging(listCount, currentPage, limit, "msearchContent.do");
			paging.calculate();
			
			//서비스 메소드 호출하고 리턴결과 받기		
			SearchPaging search = new SearchPaging();
			search.setStartRow(paging.getStartRow());
			search.setEndRow(paging.getEndRow());
			search.setKeyword(keyword);
			
			ArrayList<Meeting> list = meetingService.selectSearchContent(search);
			
			//받은 결과에 따라 성공/실패 페이지 내보내기
			if(list != null && list.size() > 0) {
				mv.addObject("list", list);
				mv.addObject("paging", paging);
				mv.addObject("currentPage", currentPage);
				mv.addObject("limit", limit);
				mv.addObject("action", action);
				mv.addObject("keyword", keyword);			
				
				mv.setViewName("meeting/meetingListView");
			}else {
				mv.addObject("message", action + "에 대한 " 
							+ keyword + " 검색 결과가 존재하지 않습니다.");			
				mv.setViewName("common/error");
			}
			
			return mv;
		}
		
		//회의글 등록날짜로 검색용 (페이징 처리 포함)
		@RequestMapping(value="msearchDate.do", method= RequestMethod.GET)
		public ModelAndView meetingsearchMethod(
				SearchPaging search,
				@RequestParam("action") String action,
				@RequestParam(name="limit", required=false) String slimit,
				@RequestParam(name="page", required=false) String page,
				ModelAndView mv) {
			
			//검색결과에 대한 페이징 처리
			//출력할 페이지 지정
			int currentPage = 1;
			//전송온 페이지 값이 있다면 추출함
			if(page != null) {
				currentPage = Integer.parseInt(page);
			}
			
			//한 페이지당 출력할 목록 갯수 지정
			int limit = 10;
			//전송 온 limit 값이 있다면
			if(slimit != null) {
				limit = Integer.parseInt(slimit);
			}
			
			//총 페이지수 계산을 위한 검색 결과 적용된 총 목록 갯수 조회
			int listCount = meetingService.selectSearchDateCount(search);
			
			//뷰 페이지에 사용할 페이징 관련 값 계산 처리
			Paging paging = new Paging(listCount, currentPage, limit, "msearchDate.do");
			paging.calculate();
			
			//서비스 메소드 호출하고 리턴결과 받기		
			/* Search search = new Search(); */
			search.setStartRow(paging.getStartRow());
			search.setEndRow(paging.getEndRow());
			search.setBegin(search.getBegin());
			search.setEnd(search.getEnd());
			
			ArrayList<Meeting> list = meetingService.selectSearchDate(search);
			
			//받은 결과에 따라 성공/실패 페이지 내보내기
			if(list != null && list.size() > 0) {
				mv.addObject("list", list);
				mv.addObject("paging", paging);
				mv.addObject("currentPage", currentPage);
				mv.addObject("limit", limit);
				mv.addObject("action", action);
				mv.addObject("begin", search.getBegin());
				mv.addObject("end", search.getEnd());			
				
				mv.setViewName("meeting/meetingListView");
			}else {
				mv.addObject("message", action + "에 대한 " + search.getBegin() + "부터 "
						+ search.getEnd() + " 기간 사이에 가입한 회원 정보가 존재하지 않습니다.");		
				mv.setViewName("common/error");
			}
			
			return mv;
		}
		
}
