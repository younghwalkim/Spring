package com.develup.noramore.qna.controller;

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

import com.develup.noramore.common.FileNameChange;
import com.develup.noramore.common.Paging;
import com.develup.noramore.common.Search;
import com.develup.noramore.common.SearchDate;
import com.develup.noramore.qna.model.service.QnaService;
import com.develup.noramore.qna.model.vo.Qna;

@Controller
public class QnaController {

	private static final Logger logger = LoggerFactory.getLogger(QnaController.class);

	@Autowired
	private QnaService qnaService;

	@RequestMapping("qnawrite.do")
	public String insertQna() {
		return "qna/qnawrite";
	}//

	@RequestMapping("qna.do")
	public String selectQna() {

		return "qna/qna";
	}//

	// Qna 수정페이지로 이동
	@RequestMapping("qmoveup.do")
	public ModelAndView moveUpdatePage(@RequestParam("boardId") int boardId, ModelAndView mv) {
		// 수정페이지에 출력할 공지글 조회해 옴
		Qna qna = qnaService.selectOne(boardId);

		if (qna != null) {
			mv.addObject("qna", qna);
			mv.setViewName("qna/qnaupdate");
		} else {
			mv.addObject("message", boardId + "번 공지글 수정페이지로 이동 실패!");
			mv.setViewName("common/error");
		}

		return mv;
	}

	// 첨부파일 다운로드 요청 처리용
	@RequestMapping("qfdown.do")
	public ModelAndView fileDownMethod(ModelAndView mv, HttpServletRequest request,
			@RequestParam("ofile") String originalFileName, @RequestParam("rfile") String renameFileName) {
		// 파일 다운 메소드는 리턴 타입이 ModelAndView 로 정해져 있음

		// 공지사항 첨부파일 저장 폴더 경로 지정
		String savePath = request.getSession().getServletContext().getRealPath("resources/qna_upfiles");

		// 저장 폴더에서 읽을 파일에 대한 파일 객체 생성함
		File renameFile = new File(savePath + "\\" + renameFileName);
		// 파일 다운시 브라우저 내보낼 원래 파일이름에 대한 파일 객체 생성함
		File originFile = new File(originalFileName);

		// 파일 다운로드용 뷰로 전달할 정보 저장 처리
		mv.setViewName("filedown"); // 등록된 파일다운로드용 뷰클래스의 id명
		mv.addObject("renameFile", renameFile);
		mv.addObject("originFile", originFile);

		return mv;
	}

	@RequestMapping("qlist.do")
	public String qnaListMethod(@RequestParam(name = "page", required = false) String page,
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
		int listCount = qnaService.selectListCount();
		// 페이지 관련 항목 계산 처리
		Paging paging = new Paging(listCount, currentPage, limit, "qlist.do");
		paging.calculate();

		// 페이지에 출력할 목록 조회해 옴
		ArrayList<Qna> list = qnaService.selectList(paging);

			model.addAttribute("list", list);
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);

			return "qna/qna";

	}

	@RequestMapping(value = "qinsert.do", method = RequestMethod.POST)
	public String qnaInsertMethod(Qna qna, Model model, HttpServletRequest request,
			@RequestParam(name = "ofile", required = false) MultipartFile mfile) {
		logger.info("qna.do : " + qna);

		if (qna.getMemberID() == null) {
			qna.setMemberID("guest");
		}

		// 공지사항 첨부파일 저장 폴더 경로 지정
		String savePath = "resources/qna_upfiles"; // 상대 경로 사용

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
					qna.setOriginalFilePath(fileName);
					qna.setRenameFilePath(renameFileName);
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("message", "첨부파일 저장 실패!");
					return "common/error";
				}
			}
		}

		// 공지사항 등록 처리
		if (qnaService.insertQna(qna) > 0) {
			// 공지글 등록 성공시 목록 보기 페이지로 이동
			return "redirect:qlist.do";
		} else {
			model.addAttribute("message", "새 공지글 등록 실패!");
			return "common/error";
		}
	}

	@RequestMapping("qnadetail.do")
	public ModelAndView qnaDetailMethod(@RequestParam("no") int boarId, ModelAndView mv, HttpSession session) {

		Qna qna = qnaService.selectOne(boarId);

		qnaService.updateAddReadCount(boarId);

		if (qna != null) {
			mv.addObject("qna", qna);

			mv.setViewName("qna/qnadetail");
		}

		return mv;
	}
	
	@RequestMapping("qdelete.do")
	public String qnaDeleteMethod(@RequestParam("boardId") int boardId,
			@RequestParam(name = "rfile", required = false) String renameFileName, HttpServletRequest request,
			Model model) {

		if (qnaService.deleteQna(boardId) > 0) {
			// 공지글 삭제 성공시 저장 폴더에 있는 첨부파일도 삭제함
			if (renameFileName != null) {
				// 공지사항 첨부파일 저장 폴더 경로 지정
				String savePath = request.getSession().getServletContext().getRealPath("resources/qna_upfiles");
				// 저장 폴더에서 파일 삭제함
				new File(savePath + "\\" + renameFileName).delete();
			}

			return "redirect:qlist.do";
		} else {
			model.addAttribute("message", boardId + "번 공지 삭제 실패!");
			return "common/error";
		}
	}

	@RequestMapping(value = "qupdate.do", method = RequestMethod.POST)
	public String qnaUpdateMethod(Qna qna, Model model, HttpServletRequest request,
			@RequestParam(name = "deleteFlag", required = false) String delFlag,
			@RequestParam(name = "upfile", required = false) MultipartFile mfile) {
		// qna 객체가 null이면 처리하지 않고 진행
		if (qna == null) {
			model.addAttribute("message", "공지 정보를 가져올 수 없습니다.");
			return "common/error";
		}

		// 공지사항 첨부파일 저장 폴더 경로 지정
		String savePath = request.getSession().getServletContext().getRealPath("resources/qna_upfiles");

		// 첨부파일이 변경된 경우의 처리 --------------------------------------------------------
		// 원래 첨부파일이 있는데 '파일삭제'를 선택한 경우
		// 또는 원래 첨부파일이 있는데 새로운 첨부파일이 업로드된 경우
		if (qna.getOriginalFilePath() != null && (delFlag != null && delFlag.equals("yes"))
				|| mfile != null && !mfile.isEmpty()) {
			// 저장 폴더에서 파일 삭제함
			new File(savePath + "\\" + qna.getRenameFilePath()).delete();
			// qna 안의 파일정보도 제거함
			qna.setOriginalFilePath(null);
			qna.setRenameFilePath(null);
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
				// qna 객체에 첨부파일 정보 저장 처리
			qna.setOriginalFilePath(fileName);
			qna.setRenameFilePath(renameFileName);
		} // 첨부파일 있을 때

		if (qnaService.updateQna(qna) > 0) {
			// 공지글 수정 성공시 목록 보기 페이지로 이동
			return "redirect:qlist.do";
		} else {
			model.addAttribute("message", qna.getBoardId() + "번 공지 수정 실패!");
			return "common/error";
		}
	}
	
	//  search part
	
	
	// Qna 제목 검색용 (페이징 처리 포함)
	@RequestMapping(value="qsearchTitle.do", method= RequestMethod.GET)
	public ModelAndView qnaSearchTitleMethod(
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
	    int listCount = qnaService.selectSearchTitleCount(keyword);
	    
	    //뷰 페이지에 사용할 페이징 관련 값 계산 처리
	    Paging paging = new Paging(listCount, currentPage, limit, "qsearchTitle.do");
	    paging.calculate();
	    
	    //서비스 메소드 호출하고 리턴결과 받기      
	    Search search = new Search();
	    search.setStartRow(paging.getStartRow());
	    search.setEndRow(paging.getEndRow());
	    search.setKeyword(keyword);
	    
	    ArrayList<Qna> list = qnaService.selectSearchTitle(search);
	    
	    //받은 결과에 따라 성공/실패 페이지 내보내기
	    if (list != null && list.size() > 0) {
	        mv.addObject("list", list);
	        mv.addObject("paging", paging);
	        mv.addObject("currentPage", currentPage);
	        mv.addObject("limit", limit);
	        mv.addObject("action", action);
	        mv.addObject("keyword", keyword);            
	        
	        mv.setViewName("qna/qna");
	    } else {
	        mv.addObject("message", action + "에 대한 " 
	                    + keyword + " 검색 결과가 존재하지 않습니다.");            
	        mv.setViewName("common/error");
	    }
	    
	    return mv;
	}

	// 공지글 내용 검색용 (페이징 처리 포함)
	@RequestMapping(value="qsearchContent.do", method= RequestMethod.GET)
	public ModelAndView qnaSearchContentMethod(
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
	    int listCount = qnaService.selectSearchContentCount(keyword);
	    
	    //뷰 페이지에 사용할 페이징 관련 값 계산 처리
	    Paging paging = new Paging(listCount, currentPage, limit, "nsearchContent.do");
	    paging.calculate();
	    
	    //서비스 메소드 호출하고 리턴결과 받기      
	    Search search = new Search();
	    search.setStartRow(paging.getStartRow());
	    search.setEndRow(paging.getEndRow());
	    search.setKeyword(keyword);
	    
	    ArrayList<Qna> list = qnaService.selectSearchContent(search);
	    
	    //받은 결과에 따라 성공/실패 페이지 내보내기
	    if (list != null && list.size() > 0) {
	        mv.addObject("list", list);
	        mv.addObject("paging", paging);
	        mv.addObject("currentPage", currentPage);
	        mv.addObject("limit", limit);
	        mv.addObject("action", action);
	        mv.addObject("keyword", keyword);            
	        
	        mv.setViewName("qna/qna");
	    } else {
	        mv.addObject("message", action + "에 대한 " 
	                    + keyword + " 검색 결과가 존재하지 않습니다.");            
	        mv.setViewName("common/error");
	    }
	    
	    return mv;
	}

	// 공지글 등록날짜로 검색용 (페이징 처리 포함)
	@RequestMapping(value="qsearchDate.do", method= RequestMethod.GET)
	public ModelAndView qnaSearchDateMethod(
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
	    int listCount = qnaService.selectSearchDateCount(searchDate);
	    
	    //뷰 페이지에 사용할 페이징 관련 값 계산 처리
	    Paging paging = new Paging(listCount, currentPage, limit, "qsearchDate.do");
	    paging.calculate();
	    
	    //서비스 메소드 호출하고 리턴결과 받기      
	    Search search = new Search();
	    search.setStartRow(paging.getStartRow());
	    search.setEndRow(paging.getEndRow());
	    search.setBegin(searchDate.getBegin());
	    search.setEnd(searchDate.getEnd());
	    
	    ArrayList<Qna> list = qnaService.selectSearchDate(search);
	    
	    //받은 결과에 따라 성공/실패 페이지 내보내기
	    if (list != null && list.size() > 0) {
	        mv.addObject("list", list);
	        mv.addObject("paging", paging);
	        mv.addObject("currentPage", currentPage);
	        mv.addObject("limit", limit);
	        mv.addObject("action", action);
	        mv.addObject("begin", searchDate.getBegin());
	        mv.addObject("end", searchDate.getEnd());            
	        
	        mv.setViewName("qna/qna");
	    } else {
	        mv.addObject("message", action + "에 대한 " + searchDate.getBegin() + "부터 "
	                + searchDate.getEnd() + " 기간 사이에 가입한 회원 정보가 존재하지 않습니다.");        
	        mv.setViewName("common/error");
	    }
	    
	    return mv;
	}
}
