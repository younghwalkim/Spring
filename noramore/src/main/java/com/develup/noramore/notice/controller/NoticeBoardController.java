package com.develup.noramore.notice.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.develup.noramore.common.FileNameChange;
import com.develup.noramore.common.Paging;
import com.develup.noramore.common.Search;
import com.develup.noramore.common.SearchDate;
import com.develup.noramore.notice.model.service.NoticeBoardService;
import com.develup.noramore.notice.model.vo.Notice;

@Controller
public class NoticeBoardController {
	// 로그 객체 로거 생성
	private static final Logger logger = LoggerFactory.getLogger(NoticeBoardController.class);

	@Autowired
	private NoticeBoardService noticeBoardService;

	@RequestMapping("noticewrite.do")
	public String insertNoticeBoard() {
		return "notice/noticewrite";
	}//


	//요청 결과 처리용 --------------------------------------------------------------
	@RequestMapping(value="ntop5.do", method=RequestMethod.POST)
	@ResponseBody
	public String noticeNewTop3Method() throws UnsupportedEncodingException {
		//ajax 요청시 리턴방법은 여러가지가 있음
		//response 객체 이용시에는 2가지중 선택 가능
		//1. 출력스트림으로 응답하는 방법 (아이디 중복 체크 예)
		//2. 뷰리졸버로 리턴하는 방법 : response body 에 내보낼 값을 저장함
		//	JSONView 클래스 등록 처리되어 있어야 함 : servlet-context.xml  
		
		//최근 등록된 공지글 3개 조회해 옴
		ArrayList<Notice> list = noticeBoardService.selectTop5();
		
		//전송용 json 객체 준비
		JSONObject sendJson = new JSONObject();
		//list 저장할 json 배열 객체 준비
		JSONArray jarr = new JSONArray();
		
		//list 를 jarr 로 옮기기
		for(Notice notice : list) {
			//notice 의 각 필드값 저장할 json 객체 생성
			JSONObject job = new JSONObject();
			
			job.put("no", notice.getBoardId());
			
			//한글에 대해서는 인코딩해서 json에 담음 (한글 깨짐 방지)
			job.put("title", URLEncoder.encode(notice.getTitle(), "utf-8"));
			//날짜는 반드시 toString() 으로 문자열로 바꿔서 json 에 담아야 함
			job.put("date", notice.getRegistDt().toString());
			
			//job 를 jarr 에 추가함
			jarr.add(job);
		}
		
		//전송용 객체에 jarr 을 담음
		sendJson.put("nlist", jarr);
		
		//전송용 json 을 json string 으로 바꿔서 전송되게 함
		return sendJson.toJSONString();  //뷰리졸버로 리턴함
		//servlet-context.xml 에 jsonString 내보내는 JSONView 라는 뷰리졸버를 추가 등록해야 함
	}
	
	// 공지글 수정페이지로 이동 처리용
	@RequestMapping("nmoveup.do")
	public ModelAndView moveUpdatePage(@RequestParam("boardId") int boardId, ModelAndView mv) {
		// 수정페이지에 출력할 공지글 조회해 옴
		Notice notice = noticeBoardService.selectOne(boardId);

		if (notice != null) {
			mv.addObject("notice", notice);
			mv.setViewName("notice/noticeupdate");
		} else {
			mv.addObject("message", boardId + "번 공지글 수정페이지로 이동 실패!");
			mv.setViewName("common/error");
		}

		return mv;
	}

	@RequestMapping("notice.do")
	public String selectNoticeBoard() {

		return "notice/notice";
	}

	//첨부파일 다운로드 요청 처리용
		@RequestMapping("nfdown.do")
		public ModelAndView fileDownMethod(
				ModelAndView mv, HttpServletRequest request, 
				@RequestParam("ofile") String originalFileName,
				@RequestParam("rfile") String renameFileName) {
			//파일 다운 메소드는 리턴 타입이 ModelAndView 로 정해져 있음
			
			//공지사항 첨부파일 저장 폴더 경로 지정
			String savePath = request.getSession().getServletContext().getRealPath(
					"resources/notice_upfiles");
			
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
	
	@RequestMapping("nlist.do")
	public String noticeListMethod(@RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "limit", required = false) String slimit, Model model) {
		int currentPage = 1;
		if (page != null) {
			currentPage = Integer.parseInt(page);
		}

		// 한 페이지 공지 10개씩 출력되게 한다면
		int limit = 10;
		if (slimit != null) {
			limit = Integer.parseInt(slimit);
		}

		// 총 페이지 수 계산을 위한 공지글 총갯수 조회
		int listCount = noticeBoardService.selectListCount();
		// 페이지 관련 항목 계산 처리
		Paging paging = new Paging(listCount, currentPage, limit, "nlist.do");
		paging.calculate();

		// 페이지에 출력할 목록 조회해 옴
		ArrayList<Notice> list = noticeBoardService.selectList(paging);

			model.addAttribute("list", list);
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);
			
			return "notice/notice";
		
	}

	@RequestMapping("noticedetail.do")
	public ModelAndView noticeDetailMethod(@RequestParam("no") int boarId, ModelAndView mv, HttpSession session) {

		Notice notice = noticeBoardService.selectOne(boarId);

		noticeBoardService.updateAddReadCount(boarId);

		if (notice != null) {
			mv.addObject("notice", notice);

			mv.setViewName("notice/noticedetail");
		}

		return mv;
	}

	// 새 공지글 등록 요청 처리용 (파일 업로드 기능 추가)

	@RequestMapping(value = "ninsert.do", method = RequestMethod.POST)
	public String noticeInsertMethod(Notice notice, Model model, HttpServletRequest request,
			@RequestParam(name = "ofile", required = false) MultipartFile mfile) {
		logger.info("ninsert.do : " + notice);

		if (notice.getmemberID() == null) {
			notice.setmemberID("guest");
		}

		// 공지사항 첨부파일 저장 폴더 경로 지정
		String savePath = "resources/notice_upfiles"; // 상대 경로 사용

		// 서버상의 실제 경로를 얻어옴
		String realPath = request.getSession().getServletContext().getRealPath("/");
		File uploadDir = new File(realPath, savePath);

		// 업로드 디렉토리가 존재하지 않으면 생성
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		// 첨부파일이 있을 때
		if (mfile != null && !mfile.isEmpty()) {
			// 전송된 파일 이름 추출
			String fileName = mfile.getOriginalFilename();
			String renameFileName = null;

			// 파일 이름 변경
			if (fileName != null && fileName.length() > 0) {
				// 변경된 파일명 생성
				renameFileName = FileNameChange.change(fileName, "yyyyMMddHHmmss");
				logger.info("첨부파일명 확인 : " + fileName + ", " + renameFileName);

				try {
					// 파일 저장
					mfile.transferTo(new File(uploadDir, renameFileName));

					// 공지사항 객체에 파일 정보 저장
					notice.setOriginalFilePath(fileName);
					notice.setRenameFilePath(renameFileName);
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("message", "첨부파일 저장 실패!");
					return "common/error";
				}
			}
		}

		// 공지사항 등록 처리
		if (noticeBoardService.insertNotice(notice) > 0) {
			// 공지글 등록 성공시 목록 보기 페이지로 이동
			return "redirect:nlist.do";
		} else {
			model.addAttribute("message", "새 공지글 등록 실패!");
			return "common/error";
		}
	}

	// 공지글 삭제 요청 처리용
	@RequestMapping("ndelete.do")
	public String noticeDeleteMethod(@RequestParam("boardId") int boardId,
			@RequestParam(name = "rfile", required = false) String renameFileName, HttpServletRequest request,
			Model model) {

		if (noticeBoardService.deleteNotice(boardId) > 0) {
			// 공지글 삭제 성공시 저장 폴더에 있는 첨부파일도 삭제함
			if (renameFileName != null) {
				// 공지사항 첨부파일 저장 폴더 경로 지정
				String savePath = request.getSession().getServletContext().getRealPath("resources/notice_upfiles");
				// 저장 폴더에서 파일 삭제함
				new File(savePath + "\\" + renameFileName).delete();
			}

			return "redirect:nlist.do";
		} else {
			model.addAttribute("message", boardId + "번 공지 삭제 실패!");
			return "common/error";
		}
	}

	@RequestMapping(value = "nupdate.do", method = RequestMethod.POST)
	public String noticeUpdateMethod(Notice notice, Model model, HttpServletRequest request,
			@RequestParam(name = "deleteFlag", required = false) String delFlag,
			@RequestParam(name = "upfile", required = false) MultipartFile mfile) {
		// notice 객체가 null이면 처리하지 않고 진행
		if (notice == null) {
			model.addAttribute("message", "공지 정보를 가져올 수 없습니다.");
			return "common/error";
		}

		// 공지사항 첨부파일 저장 폴더 경로 지정
		String savePath = request.getSession().getServletContext().getRealPath("resources/notice_upfiles");

		// 첨부파일이 변경된 경우의 처리 --------------------------------------------------------
		// 원래 첨부파일이 있는데 '파일삭제'를 선택한 경우
		// 또는 원래 첨부파일이 있는데 새로운 첨부파일이 업로드된 경우
		if (notice.getOriginalFilePath() != null && (delFlag != null && delFlag.equals("yes"))
				|| mfile != null && !mfile.isEmpty()) {
			// 저장 폴더에서 파일 삭제함
			new File(savePath + "\\" + notice.getRenameFilePath()).delete();
			// notice 안의 파일정보도 제거함
			notice.setOriginalFilePath(null);
			notice.setRenameFilePath(null);
		}

		// 새로운 첨부파일이 있을 때 (공지글 첨부파일은 1개임)
		if (mfile != null && !mfile.isEmpty()) {
			// 전송온 파일이름 추출함
			String fileName = mfile.getOriginalFilename();
			String renameFileName = null;

			// 저장폴더에는 변경된 이름을 저장 처리함
			// 파일 이름바꾸기함 : 년월일시분초.확장자
			if (fileName != null && fileName.length() > 0) {
				// 바꿀 파일명에 대한 문자열 만들기
				renameFileName = FileNameChange.change(fileName, "yyyyMMddHHmmss");
				try {
					// 저장 폴더에 파일명 바꾸기 처리
					mfile.transferTo(new File(savePath + "\\" + renameFileName));

				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("message", "첨부파일 저장 실패!");
					return "common/error";
				}
			} // 파일명 바꾸기
				// notice 객체에 첨부파일 정보 저장 처리
			notice.setOriginalFilePath(fileName);
			notice.setRenameFilePath(renameFileName);
		} // 첨부파일 있을 때

		if (noticeBoardService.updateNotice(notice) > 0) {
			// 공지글 수정 성공시 목록 보기 페이지로 이동
			return "redirect:nlist.do";
		} else {
			model.addAttribute("message", notice.getBoardId() + "번 공지 수정 실패!");
			return "common/error";
		}
	}
	
	// notice 제목 검색용 (페이징 처리 포함)
	@RequestMapping(value="nsearchTitle.do", method= RequestMethod.GET)
	public ModelAndView noticeSearchTitleMethod(
	        @RequestParam("action") String action,            
	        @RequestParam("keyword") String keyword,
	        @RequestParam(name="limit", required=false, defaultValue="10") String slimit,
	        @RequestParam(name="page", required=false, defaultValue="1") String page,
	        ModelAndView mv) {

	    // 검색 키워드나 페이지, limit 값이 빈 문자열이면 기본값으로 설정
	    if (keyword == null || keyword.isEmpty()) {
	        keyword = ""; // 빈 문자열로 설정하거나 다른 기본값 설정
	    }
	    if (slimit == null || slimit.isEmpty()) {
	        slimit = "10"; // 빈 문자열이면 기본값으로 설정
	    }
	    if (page == null || page.isEmpty()) {
	        page = "1"; // 빈 문자열이면 기본값으로 설정
	    }

	    //검색결과에 대한 페이징 처리
	    //출력할 페이지 지정
	    int currentPage = Integer.parseInt(page);
	    
	    //한 페이지당 출력할 목록 갯수 지정
	    int limit = Integer.parseInt(slimit);

	    //총 페이지수 계산을 위한 검색 결과 적용된 총 목록 갯수 조회
	    int listCount = noticeBoardService.selectSearchTitleCount(keyword);
	    
	    //뷰 페이지에 사용할 페이징 관련 값 계산 처리
	    Paging paging = new Paging(listCount, currentPage, limit, "nsearchTitle.do");
	    paging.calculate();
	    
	    //서비스 메소드 호출하고 리턴결과 받기      
	    Search search = new Search();
	    search.setStartRow(paging.getStartRow());
	    search.setEndRow(paging.getEndRow());
	    search.setKeyword(keyword);
	    
	    ArrayList<Notice> list = noticeBoardService.selectSearchTitle(search);
	    
	    //받은 결과에 따라 성공/실패 페이지 내보내기
	    if (list != null && list.size() > 0) {
	        mv.addObject("list", list);
	        mv.addObject("paging", paging);
	        mv.addObject("currentPage", currentPage);
	        mv.addObject("limit", limit);
	        mv.addObject("action", action);
	        mv.addObject("keyword", keyword);            
	        
	        mv.setViewName("notice/notice");
	    } else {
	        mv.addObject("message", action + "에 대한 " 
	                    + keyword + " 검색 결과가 존재하지 않습니다.");            
	        mv.setViewName("common/error");
	    }
	    
	    return mv;
	}

	// 공지글 내용 검색용 (페이징 처리 포함)
	@RequestMapping(value="nsearchContent.do", method= RequestMethod.GET)
	public ModelAndView noticeSearchContentMethod(
	        @RequestParam("action") String action,            
	        @RequestParam("keyword") String keyword,
	        @RequestParam(name="limit", required=false, defaultValue="10") String slimit,
	        @RequestParam(name="page", required=false, defaultValue="1") String page,
	        ModelAndView mv) {

	    // 검색 키워드나 페이지, limit 값이 빈 문자열이면 기본값으로 설정
	    if (keyword == null || keyword.isEmpty()) {
	        keyword = ""; // 빈 문자열로 설정하거나 다른 기본값 설정
	    }
	    if (slimit == null || slimit.isEmpty()) {
	        slimit = "10"; // 빈 문자열이면 기본값으로 설정
	    }
	    if (page == null || page.isEmpty()) {
	        page = "1"; // 빈 문자열이면 기본값으로 설정
	    }

	    //검색결과에 대한 페이징 처리
	    //출력할 페이지 지정
	    int currentPage = Integer.parseInt(page);
	    
	    //한 페이지당 출력할 목록 갯수 지정
	    int limit = Integer.parseInt(slimit);

	    //총 페이지수 계산을 위한 검색 결과 적용된 총 목록 갯수 조회
	    int listCount = noticeBoardService.selectSearchContentCount(keyword);
	    
	    //뷰 페이지에 사용할 페이징 관련 값 계산 처리
	    Paging paging = new Paging(listCount, currentPage, limit, "nsearchContent.do");
	    paging.calculate();
	    
	    //서비스 메소드 호출하고 리턴결과 받기      
	    Search search = new Search();
	    search.setStartRow(paging.getStartRow());
	    search.setEndRow(paging.getEndRow());
	    search.setKeyword(keyword);
	    
	    ArrayList<Notice> list = noticeBoardService.selectSearchContent(search);
	    
	    //받은 결과에 따라 성공/실패 페이지 내보내기
	    if (list != null && list.size() > 0) {
	        mv.addObject("list", list);
	        mv.addObject("paging", paging);
	        mv.addObject("currentPage", currentPage);
	        mv.addObject("limit", limit);
	        mv.addObject("action", action);
	        mv.addObject("keyword", keyword);            
	        
	        mv.setViewName("notice/notice");
	    } else {
	        mv.addObject("message", action + "에 대한 " 
	                    + keyword + " 검색 결과가 존재하지 않습니다.");            
	        mv.setViewName("common/error");
	    }
	    
	    return mv;
	}

	// 공지글 등록날짜로 검색용 (페이징 처리 포함)
	@RequestMapping(value="nsearchDate.do", method= RequestMethod.GET)
	public ModelAndView noticeSearchDateMethod(
	        SearchDate searchDate,
	        @RequestParam("action") String action,
	        @RequestParam(name="limit", required=false, defaultValue="10") String slimit,
	        @RequestParam(name="page", required=false, defaultValue="1") String page,
	        ModelAndView mv) {

	    //검색결과에 대한 페이징 처리
	    //출력할 페이지 지정
	    int currentPage = 1;
	    //전송온 페이지 값이 있다면 추출함
	    if(page != null && !page.isEmpty()) {
	        currentPage = Integer.parseInt(page);
	    }
	    
	    //한 페이지당 출력할 목록 갯수 지정
	    int limit = 10;
	    //전송 온 limit 값이 있다면
	    if(slimit != null && !slimit.isEmpty()) {
	        limit = Integer.parseInt(slimit);
	    }
	    
	    //총 페이지수 계산을 위한 검색 결과 적용된 총 목록 갯수 조회
	    int listCount = noticeBoardService.selectSearchDateCount(searchDate);
	    
	    //뷰 페이지에 사용할 페이징 관련 값 계산 처리
	    Paging paging = new Paging(listCount, currentPage, limit, "nsearchDate.do");
	    paging.calculate();
	    
	    //서비스 메소드 호출하고 리턴결과 받기      
	    Search search = new Search();
	    search.setStartRow(paging.getStartRow());
	    search.setEndRow(paging.getEndRow());
	    search.setBegin(searchDate.getBegin());
	    search.setEnd(searchDate.getEnd());
	    
	    ArrayList<Notice> list = noticeBoardService.selectSearchDate(search);
	    
	    //받은 결과에 따라 성공/실패 페이지 내보내기
	    if (list != null && list.size() > 0) {
	        mv.addObject("list", list);
	        mv.addObject("paging", paging);
	        mv.addObject("currentPage", currentPage);
	        mv.addObject("limit", limit);
	        mv.addObject("action", action);
	        mv.addObject("begin", searchDate.getBegin());
	        mv.addObject("end", searchDate.getEnd());            
	        
	        mv.setViewName("notice/notice");
	    } else {
	        mv.addObject("message", action + "에 대한 " + searchDate.getBegin() + "부터 "
	                + searchDate.getEnd() + " 기간 사이에 가입한 회원 정보가 존재하지 않습니다.");        
	        mv.setViewName("common/error");
	    }
	    
	    return mv;
	}
}
