package com.project.getdrive.board.controller;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

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

import com.project.getdrive.board.model.service.BoardService;
import com.project.getdrive.board.model.vo.Board;
import com.project.getdrive.common.FileNameChange;
import com.project.getdrive.common.Paging;
import com.project.getdrive.common.SearchDate;
import com.project.getdrive.common.SearchPaging;


@Controller
public class BoardController {
	//이 클래스에서 메서드 안의 요청과 반환값들의 결과 출력 확인을 위한 로그 객체 생성
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;
	
	//게시글 원글 페이지 불러오기 메서드
	@RequestMapping("bwrite.do")
	public String boardWrite()	{
		return "board/boardWrite";
	}
	
	// 원글 게시글 수정 페이지로 이동 처리용
	@RequestMapping("bupdate.do")
	public String boardUpdateMethod(
			@RequestParam("bNo") int bNo,
			@RequestParam("tNo") int tNo,
			@RequestParam("page") int currentPage, 
			Model model) {
		
		//수정 페이지에 전달해서 출력할 board 정보 조회함
		Board board = boardService.selectBoard(bNo);
		
		if(board != null) {
			model.addAttribute("board", board);
			model.addAttribute("tNo", tNo);
			model.addAttribute("page", currentPage);
			
			return "board/boardUpdate";
		}else {
			model.addAttribute("message", bNo + "번 게시글 페이지 이동 실패");
			return "common/error";
		}
	}
	
	// 원글 게시글 수정처리용
	@RequestMapping(value="boriginupdate.do", method=RequestMethod.POST)
	public String boardOriginUpdate(
			Board board, HttpServletRequest request,
			@RequestParam(name="page", required=false) String page,
			@RequestParam(name="deleteFlag", required=false) String delFlag,
			@RequestParam(name="upfile", required=false) MultipartFile mfile,
			Model model) {
		
		logger.info("boriginupdate.do: " + board);
		
		int currentPage = 1;
		if(page != null) {
			currentPage = Integer.parseInt(page);
		}
		
		//게시글 원글 첨부파일 저장 폴더 경로 지정
		String savePath = request.getSession().getServletContext().getRealPath("resources/board_upfiles");
		
		//첨부파일이 변경되었을 때의 처리 구간 -----------
		//1. 원래 첨부파일이 있는데, '파일삭제'를 선택한 경우
		//2. 원래 첨부파일이 있는데, 새로운 파일이 업로드 된 경우(첨부파일 변경)
		if(board.getbOriginFileName() != null &&
				((delFlag != null && delFlag.equals("yes")) || !mfile.isEmpty())){
					
					//저장 폴더에서 이전 파일을 삭제함
					new File(savePath + "\\" + board.getbRenameFileName()).delete();
					
					//board 안의 파일정보도 제거함
					board.setbOriginFileName(null);
					board.setbRenameFileName(null);
				}
		
		//3. 새로운 첨부파일이 업로드된 경우
		if(!mfile.isEmpty()) {
			//전송온 첨부파일명 추출함
			String fileName = mfile.getOriginalFilename();
			String renameFileName = null;
			
			//저장 폴더에는 변경된 파일이름으로 파일을 저장함
			//파일 이름 바꾸기함 => 년월일시분초.확장자
			if(fileName != null && fileName.length() > 0) {
				//바꿀 파일명에 대한 문자열 포맷 만들기
				renameFileName = FileNameChange.change(fileName, "yyyyMMddHHmmss");
				logger.info("첨부 파일명 변경 확인 : " + fileName + "," + renameFileName);
				
				try {
					//지정한 저장 폴더에 파일명 바꾸기 처리함
					mfile.transferTo(new File(savePath + "\\" + renameFileName));
				} catch (Exception e) {
					e.printStackTrace()	;
					model.addAttribute("message", "첨부 파일 저장 실패!");
					return "board/boardMain";
				}
			} //파일명 바꾸기
			//board에 첨부파일 정보 저장 처리
			board.setbOriginFileName(fileName);
			board.setbRenameFileName(renameFileName);
		}//첨부파일 있을때
		
		if(boardService.boardOriginUpdate(board) > 0) {
			
			model.addAttribute("bNo", board.getbNo());
			model.addAttribute("page", currentPage);
			model.addAttribute("tNo", board.getTNo());
			
			return"redirect:bdetail.do";
			
		}else {
			model.addAttribute("message", board.getbNo() + "번 글 수정 실패입니다.");
			return "redirect:bdetail.do";
		}
	}
	
	//새 게시글 등록 페이지 요청 처리용 (첨부파일 업로드 기능 추가)
	@RequestMapping(value="binsert.do", method=RequestMethod.POST)
	public String boardInsertMethod(
			Board board, Model model, 
			HttpServletRequest request,
			@RequestParam(name="upfile", required=false) MultipartFile mfile) {
		logger.info("binsert.do : " + board);
		
		//게시글 첨부파일 저장용 폴더 지정 : 톰캣(WAS)이 구동하고 있는 애플리케이션 프로젝트 안의 폴더 지정
		//el 절대경로 표기인 ${ pageContext.servletContext.contextPage } 와 같은 의미의 코드임
		String savePath = request.getSession().getServletContext().getRealPath("resources/board_upfiles");
		
		//첨부파일 있을때
		if(!mfile.isEmpty()) {
			//전송온 첨부파일명 추출함
			String fileName = mfile.getOriginalFilename();
			String renameFileName = null;
			
			//저장 폴더에는 변경된 파일 이름으로 파일을 저장함
			//파일 이름 바꾸기함 => 년월일시분초.확장자
			if(fileName != null && fileName.length() > 0) {
				//바꿀 파일명에 대한 문자열 포맷 만들기
				renameFileName = FileNameChange.change(fileName, "yyyyMMddHHmmss");
				logger.info("첨부 파일명 변경 확인 : " + fileName + ", " + renameFileName);
				
				try {
					//지정한 저장 폴더에 파일명 바꾸기 처리함
					mfile.transferTo(new File(savePath + "\\" + renameFileName));
				} catch (Exception e) {
					 e.printStackTrace();
					 model.addAttribute("message", "첨부파일 저장 실패!");
					 return "common/error";
				}
			}
			//파일명 바꾸기
			//board 에 첨부파일 정보 저장처리
			board.setbOriginFileName(fileName);
			board.setbRenameFileName(renameFileName);
		}//첨부파일 있을 때
		
		if(boardService.insertOriginBoard(board) > 0) {
			
			//게시글 등록 성공시 목록 보기 페이지로 이동
			return "redirect:bmain.do?tNo=" + board.getTNo();
		}else {
			model.addAttribute("message", "새 게시글 등록 실패!!!!");
			return "common/error";
		}
		
	}

	//게시글 전체 조회	
	@RequestMapping("bmain.do")
	public String boardMain(
			@RequestParam(name="page", required=false) String page,
			@RequestParam(name="limit", required=false) String slimit,
			@RequestParam("tNo") int tNo,
			 Model model) {
		
		int currentPage = 1;
		if(page != null && page.trim().length() > 0) {
			currentPage = Integer.parseInt(page);
		}
		
		//한 페이지에 게시글 5개씩 출력되게 하기
		int limit = 5;
		if(slimit != null && slimit.trim().length() > 0) {
			limit = Integer.parseInt(slimit); //전송받은 한 페이지에 출력할 목록 갯수 적용
		}
		
		//총 페이지수 계산을 위해 전체 갯수 조회해 옴
		int listCount = boardService.selectListCount(tNo);
		
		//페이징 계산 처리하고 실행하기
		Paging paging = new Paging(listCount, currentPage, limit, "bmain.do", tNo);
		paging.calculate();
		paging.getStartRow();
		paging.getEndRow();
		paging.getTNo();
		
		//출력할 페이지에 대한 목록 조회
		ArrayList<Board> list= boardService.selectList(paging);
			
		logger.info("bamain.do " + list.toString());
		
		if(list != null && list.size() > 0) {
			model.addAttribute("list", list);
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("limit", limit);
			model.addAttribute("tNo", tNo);
			
			return "board/boardMain";
		}else {
			model.addAttribute("message", "페이지 목록 조회 실패");
			return "board/boardMain";
		}
	}
	
//게시글 제목으로 조회
	@RequestMapping(value="bsearchTitle.do", method={RequestMethod.POST, RequestMethod.GET})
	public ModelAndView boardSearchTitleMethod(
		@RequestParam("action") String action,
		@RequestParam("keyword") String keyword,
		@RequestParam(name="limit", required=false) String slimit,
		@RequestParam(name="page", required=false) String page,
		@RequestParam("tNo") int tNo,
		ModelAndView mv) {
		
		//검색 결과에 대한 페이징 처리를 위한 페이지 지정
		int currentPage=1;
		if(page != null) {
			currentPage=Integer.parseInt(page);
		}
		
		int limit = 5;
		if(slimit != null) {
			limit=Integer.parseInt(slimit);
		}
		
		//검색 결과가 적용된 총 페이지 계산을 위한 총 목록 갯수 조회
		int listCount = boardService.selectSearchTitleCount(keyword);
		
		//뷰 페이지에서 사용할 페이징 관련 값들 계산 처리
		Paging paging= new Paging(listCount, currentPage, limit, "bsearchTitle.do", tNo);
		paging.calculate();
		
		//한 페이지에 출력할 검색 결과 적용된 목록 조회
		SearchPaging search = new SearchPaging();
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());
		search.setKeyword(keyword);
		
		ArrayList<Board> list = boardService.selectSearchTitle(search);
		
		//받은 결과값에 따라 성공 / 실패 페이지 내보내기
		if(list != null && list.size() > 0) {
			mv.addObject("list", list);
			mv.addObject("paging", paging);
			mv.addObject("currentPage", currentPage);
			mv.addObject("action", action);
			mv.addObject("keyword", keyword);
			mv.addObject("limit", limit);
			mv.addObject("tNo", tNo);
			
			mv.setViewName("board/boardMain");
		}else {
			mv.addObject("message", action + "에 대한 " + keyword + "검색 결과가 존재하지 않습니다"	);
			mv.setViewName("board/boardMain");
		}
		return mv;
	}
	
	//게시글 작성자로 검색용
	@RequestMapping(value="bsearchWriter.do", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView boardSearchWriterMethod(
			@RequestParam("action") String action,
			@RequestParam("keyword") String keyword,
			@RequestParam(name="limit", required=false) String slimit,
			@RequestParam(name="page", required=false) String page,
			@RequestParam("tNo") int tNo,
			ModelAndView mv) {
		
		//검색 결과에 대한 페이징 처리를 위한 페이지 지정
		int currentPage = 1;
		if(page != null) {
			currentPage = Integer.parseInt(page);
		}

		int limit = 5;
		if(slimit != null) {
			limit = Integer.parseInt(slimit);
		}
		
		//검색 결과가 적용된 총 페이지 계산을 위한 총 목록 갯수 조회해 옴
		int listCount = boardService.selectSearchWriterCount(keyword);
		
		//뷰 페이지에 사용팔 페이징 관련 값들 계산 처리함
		Paging paging = new Paging(listCount, currentPage, limit, "bsearchWriter.do", tNo);
		paging.calculate();
		
		//한 페이지에 출력할 검색 결과 적용된 목록 조회
		SearchPaging search = new SearchPaging();
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());
		search.setKeyword(keyword);
		
		ArrayList<Board> list = boardService.selectSearchWriter(search);
		
		//받은 결과에 따라 성공/실패 페이지 내보내기
		if(list != null && list.size() > 0) {
			
			mv.addObject("list", list);
			mv.addObject("paging", paging);
			mv.addObject("currentPage", currentPage);
			mv.addObject("action", action);
			mv.addObject("keyword", keyword);
			mv.addObject("limit", limit);
			mv.addObject("tNo", tNo);
			
			mv.setViewName("board/boardMain");
		}else {
			mv.addObject("message",
					action + "에 대한 " + keyword + "검색 결고가 존재하지 않습니다.");
			mv.setViewName("board/boardMain");
		}
		
		return mv;
	}
	
	//게시글 등록날짜로 검색
	@RequestMapping(value="bsearchDate.do", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView boardSearchDateMethod(
			SearchDate searchDate,
			@RequestParam("action") String action,
			@RequestParam(name="limit", required=false) String slimit,
			@RequestParam(name="page", required=false) String page,
			@RequestParam("tNo") int tNo,
			ModelAndView mv) {
		
		//검색결과에 대한 페이징 처리를 위한 페이지 지정
		int currentPage = 1;
		if(page != null) {
			currentPage = Integer.parseInt(page);
		}
		
		int limit = 5;
		if(slimit != null) {
			limit = Integer.parseInt(slimit);
		}
		
		//검색결과가 적용된 총 페이지 계산을 위한 총 목록 갯수 조회해 옴
		int listCount = boardService.selectSearchDateCount(searchDate);
		
		//뷰 페이지에 사용할 페이징 관련 값들 계산 처리
		Paging paging = new Paging(listCount, currentPage, limit, "bsearchDate.do", tNo);
		paging.calculate();
		
		//한 페이지에 출력할 검색 결과 적용된 목록 조회
		SearchPaging search = new SearchPaging();
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());
		search.setBegin(searchDate.getBegin());
		search.setEnd(searchDate.getEnd());
		search.settNo(tNo);
		
		logger.info("bsearchDate.do" + searchDate);
		
		ArrayList<Board> list = boardService.selectSearchDate(search);
		
		//받은 결과에 따라 성공/실패 페이지 내보내기
		if(list != null && list.size() > 0) {
			mv.addObject("list", list);
			mv.addObject("paging", paging);
			mv.addObject("currentPage", currentPage);
			mv.addObject("action", action);
			mv.addObject("limit", limit);
			mv.addObject("tNo", tNo);
			mv.addObject("begin", searchDate.getBegin());
			mv.addObject("end", searchDate.getEnd());
			
			mv.setViewName("board/boardMain");
		}else {
			mv.addObject("message",
					action + "에 대한" + searchDate.getBegin() + "부터" + searchDate.getEnd() +
					"기간 사이에 등록된 게시글 검색 결과가 존재하지 않습니다.");
			mv.setViewName("board/boardMain");
		}
		
		return mv;
	}
	

 //게시글 클릭시 상세보기
	@RequestMapping("bdetail.do")
	public String boardDetailMethod(
			@RequestParam("bNo") int boardNo,
			@RequestParam("tNo") int tNo,
			@RequestParam(name="page", required=false) String page, Model model) {
		
		//목록으로 돌아갈 때의 페이지를 기억저장
		int currentPage = 1;
		if(page != null) {
			currentPage = Integer.parseInt(page);
		}
		
		//클릭한 글번호의 게시글 조회
		Board board = boardService.selectBoard(boardNo);
		
		if(board != null) {
			model.addAttribute("board", board);
			model.addAttribute("tNo", tNo);
			model.addAttribute("currentPage", currentPage);
			
			return "board/boardDetail";
		}else {
			model.addAttribute("message", boardNo + "요청한 게시글 조회 실패입니다.");
			return "common/error";
		}
	}
	
	//원글 삭제하기 페이지
	@RequestMapping(value="bdelete.do", method= {RequestMethod.POST, RequestMethod.GET})
	public String boardDeleteMethod(
			Board board, 
			Model model, 
			HttpServletRequest request,
			@RequestParam("tNo") String tNo) {
				
		if(boardService.deleteBoard(board) > 0) {
			
			return"redirect:bmain.do?tNo=" + tNo;
		}else {
			model.addAttribute("message", board.getbNo() + "번 게시글 삭제 실패입니다.");
			return "common/error";
		}
	}
	
	//첨부파일 다운로드 요청 처리용
	//파일 다운로드 처리용 메서드는 리턴 타입이 ModelAndView 로 정해져있다.
	@RequestMapping("bdown.do")
	public ModelAndView fileDownMethod(
			@RequestParam("ofile") String originalFileName,
			@RequestParam("rfile") String renameFileName,
			ModelAndView mv, HttpServletRequest request) {
		
		//게시물 첨부파일 저장 폴더 지정
		String savePath = request.getSession().getServletContext().getRealPath("resources/board_upfiles");
		
		//저장 폴더에서 읽을 파일에 대한 File 객체 생성함
		File readFile = new File(savePath + "\\" + renameFileName);
		//파일 다운시 브라우저 내보낼 원래 파일명에 대한 FIle 객체 생성
		File originFile = new File(originalFileName);
		
		//스프링에서는 파일 다운로드를 처리하는 뷰 클래스를 별도로 작성하도록 되어있음
		//스프링이 제공하는 View 클래스를 상속받은 후손 클래스로 만들어야 한다.
		
		//파일 다운로드용 뷰로 전달할 정보 저장 처리
		mv.setViewName("filedown"); //등록된 파일다운로드용 뷰클래스의 id명
		mv.addObject("renameFile", readFile);
		mv.addObject("originFile", originFile);
		
		return mv;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}














