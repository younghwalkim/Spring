package com.develup.noramore.recrboard.controller;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
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
import org.springframework.web.util.WebUtils;

import com.develup.noramore.category.model.service.CategoryService;
import com.develup.noramore.category.model.vo.Category;
import com.develup.noramore.commentrecrboard.service.CommentRecrBoardService;
import com.develup.noramore.common.FileNameChange;
import com.develup.noramore.common.Paging;
import com.develup.noramore.common.Search;
import com.develup.noramore.recrappl.model.service.RecrApplService;
import com.develup.noramore.recrappl.model.vo.RecrAppl;
import com.develup.noramore.recrboard.model.service.RecrBoardService;
import com.develup.noramore.recrboard.model.vo.RecrBoard;

// 모집테이블 전체 조회
@Controller("recrBoardController")
public class RecrBoardController {
	private static final Logger logger = LoggerFactory.getLogger(RecrBoardController.class);
	@Autowired
	private RecrBoardService recrBoardService;
	@Autowired
	private CommentRecrBoardService commentRecrBoardService;
	@Autowired
	private RecrApplService recrApplService;
	@Autowired
	private CategoryService categoryService;
	
	// 테이블 리스트
	@RequestMapping("rblist.do")
	public ModelAndView selectRecrBoard(ModelAndView mv, @RequestParam(name = "page", required = false) String page, 
										@RequestParam(name="categoryId", required = false) String categoryId1) throws JsonGenerationException, JsonMappingException, IOException {
		ArrayList<Category> categoryList = categoryService.selectAll();
		
		
		int currentPage = 1;
		if (page != null) {
			currentPage = Integer.parseInt(page);
		}
		int limit = 10;
		int categoryId = 1;
		if(categoryId1 != null) {
			categoryId = Integer.parseInt(categoryId1);
		} 
		
		ArrayList<RecrBoard> locationList = recrBoardService.selectLocation(categoryId);
		int listCount = recrBoardService.selectListcount(categoryId);
		
		ObjectMapper objectMapper = new ObjectMapper();

        String locationListJson = objectMapper.writeValueAsString(locationList);
        mv.addObject("locationListJSON", locationListJson);
        
		

		Paging paging = new Paging(listCount, currentPage, limit, "rblist.do");
		paging.calculate();

		// 한 페이지에 출력할 검색 결과 적용된 목록 조회
		Search search = new Search();
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());
		search.setCategoryId(categoryId);
		
		ArrayList<RecrBoard> list = recrBoardService.selectSearchList(search);

		mv.addObject("list", list);
		mv.setViewName("recrBoard/RecrBoardList");
		mv.addObject("currentPage", currentPage);
		mv.addObject("paging", paging);
		mv.addObject("categoryId", categoryId);
		mv.addObject("categoryList", categoryList);
		mv.addObject("locationList", locationList);
		return mv;
	}//
	
	// 제목으로 검색
	@RequestMapping("searchrecrtitle.do")
	public ModelAndView searchRecrTitle(Search search, 
			@RequestParam(name="limit", required=false) String limit1,
			@RequestParam(name="page", required=false) String page, ModelAndView mv,
			@RequestParam("categoryId") int categoryId) throws JsonGenerationException, 
			JsonMappingException, IOException {
		int currentPage = 1;
		if (page != null) {
			currentPage = Integer.parseInt(page);
		}
		int limit = 10;
		if(limit1 != null) {
			limit = Integer.parseInt(limit1);
		}
		if(search.getCategoryId() != 0) {
			categoryId = search.getCategoryId();
		}
		
		int listCount = recrBoardService.searchtitlecount(search);
		Paging paging = new Paging(listCount, currentPage, limit, "searchrecrtitle.do");
		paging.calculate();
		
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());
		
		ArrayList<RecrBoard> list = recrBoardService.searchtitleList(search);


		ArrayList<RecrBoard> locationList = recrBoardService.selectLocation(categoryId);
		ObjectMapper objectMapper = new ObjectMapper();
        String locationListJson = objectMapper.writeValueAsString(locationList);
        mv.addObject("locationListJSON", locationListJson);
		
		
		mv.addObject("list", list);
		mv.setViewName("recrBoard/RecrBoardList");
		mv.addObject("currentPage", currentPage);
		mv.addObject("paging", paging);
		mv.addObject("categoryId", categoryId);
		mv.addObject("keyword", search.getKeyword());
		mv.addObject("href", "searchrecrtitle.do");
		return mv;
	}
	
		// 아이디로 검색
		@RequestMapping("searchrecrwriter.do")
		public ModelAndView searchRecrWriter(Search search, 
				@RequestParam(name="limit", required=false) String limit1,
				@RequestParam(name="page", required=false) String page, ModelAndView mv, 
				@RequestParam("categoryId") int categoryId) throws JsonGenerationException, 
				JsonMappingException, IOException {
			int currentPage = 1;
			if (page != null) {
				currentPage = Integer.parseInt(page);
			}
			int limit = 10;
			if(limit1 != null) {
				limit = Integer.parseInt(limit1);
			}
			if(search.getCategoryId() != 0) {
				categoryId = search.getCategoryId();
			}
			
			int listCount = recrBoardService.searchwritercount(search);
			Paging paging = new Paging(listCount, currentPage, limit, "searchrecrwriter.do");
			paging.calculate();
			
			search.setStartRow(paging.getStartRow());
			search.setEndRow(paging.getEndRow());
			
			ArrayList<RecrBoard> list = recrBoardService.searchwriterList(search);

			
			ArrayList<RecrBoard> locationList = recrBoardService.selectLocation(categoryId);
			ObjectMapper objectMapper = new ObjectMapper();
	        String locationListJson = objectMapper.writeValueAsString(locationList);
	        mv.addObject("locationListJSON", locationListJson);
			

			mv.addObject("list", list);
			mv.setViewName("recrBoard/RecrBoardList");
			mv.addObject("currentPage", currentPage);
			mv.addObject("paging", paging);
			mv.addObject("categoryId", categoryId);
			mv.addObject("keyword", search.getKeyword());
			mv.addObject("href", "searchrecrwriter.do");
			return mv;
		}

	// 모집테이블 생성
	@RequestMapping(value = "insertrb.do", method = RequestMethod.POST)
	public String insertRecrBoard(RecrBoard recrBoard, HttpServletRequest request, Model model,
			@RequestParam(name = "upfile", required = false) MultipartFile mfile,
			@RequestParam("maxRecr1") String maxRecr,
			@RequestParam(name = "ageMinCondition1", required = false) String ageMinCondition,
			@RequestParam(name = "ageMaxCondition1", required = false) String ageMaxCondition,
			@RequestParam("page") String page, 
			@RequestParam("categoryId") int categoryId) {
		// input을 number 로 해도 이쪽으로 보낼 때는 String 형태로 넘어와서 취한 조취
		if (!ageMaxCondition.isEmpty()) {
			recrBoard.setAgeMaxCondition(Integer.parseInt(ageMaxCondition));
		}
		if (!ageMinCondition.isEmpty()) {
			recrBoard.setAgeMinCondition(Integer.parseInt(ageMinCondition));
		}
		if (!maxRecr.isEmpty()) {
			recrBoard.setMaxRecr(Integer.parseInt(maxRecr));
		}

		String savePath = request.getSession().getServletContext().getRealPath("resources/recrboard_upfiles");

		if (!mfile.isEmpty()) {
			// 전송온 첨부파일명 추출함
			String fileName = mfile.getOriginalFilename();
			String renameFileName = null;

			// 저장 폴더는 변경된 파일 이름으로 파일 저장
			// 파일 이름 바꾸기 => 년월일시분초.확장자
			if (fileName != null && fileName.length() > 0) {
				// 바꿀 파일명에 대한 문자열 포멧 만들기
				renameFileName = FileNameChange.change(fileName, "yyyyMMddhhmmss");
				logger.info("파일명 변경 : " + fileName + " => " + renameFileName);

				try {
					// 지정한 저장 폴더에 파일명 바꾸기 처리함
					mfile.transferTo(new File(savePath + "\\" + renameFileName));
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("message", "첨부파일 파일명 변환 중 에러가 발생했습니다. ");
					return "recerBoarad/RecrBoardList";
				}
			} /// 파일명 바꾸기

			recrBoard.setRecrOriginalFilename(fileName);
			recrBoard.setRecrRenameFilename(renameFileName);
		}

		if (recrBoardService.insertRecrBoard(recrBoard) > 0) {
			model.addAttribute("message", " 글이 등록되었습니다.");
			model.addAttribute("RecrBoard", recrBoard);
			model.addAttribute("currentPage", page);
			model.addAttribute("categoryId", categoryId);
			return "redirect:rblist.do";
		} else {
			model.addAttribute("categoryId", categoryId);
			model.addAttribute("currentPage", page);
			model.addAttribute("message", " 글 등록에 실패하였습니다.");
			return "redirect:rblist.do";
		}
		
	}//

	// 모집 테이블 수정
	@RequestMapping(value = "updaterb.do", method = RequestMethod.POST)
	public String updateRecrBoard(RecrBoard recrBoard, HttpServletRequest request, Model model,
			@RequestParam(name = "upfile", required = false) MultipartFile mfile,
			@RequestParam(name = "deleteFlag", required = false) String delFlag,
			@RequestParam("maxRecr1") String maxRecr,
			@RequestParam(name = "ageMinCondition1", required = false) String ageMinCondition,
			@RequestParam(name = "ageMaxCondition1", required = false) String ageMaxCondition,
			@RequestParam("page") String page, @RequestParam("categoryId") int categoryId) {
		int currentPage = 1;
		if (!page.isEmpty()) {
			currentPage = Integer.parseInt(page);
		}
		// input을 number 로 해도 이쪽으로 보낼 때는 String 형태로 넘어와서 취한 조취
		if (!ageMaxCondition.isEmpty()) {
			recrBoard.setAgeMaxCondition(Integer.parseInt(ageMaxCondition));
		}
		if (!ageMinCondition.isEmpty()) {
			recrBoard.setAgeMinCondition(Integer.parseInt(ageMinCondition));
		}
		if (!maxRecr.isEmpty()) {
			recrBoard.setMaxRecr(Integer.parseInt(maxRecr));
		}

		String savePath = request.getSession().getServletContext().getRealPath("resources/recrboard_upfiles");

		// 1. 첨부파일이 있었지만 '파일 삭제' 를 선택한 경우
		if (recrBoard.getRecrOriginalFilename() != null
				&& ((delFlag != null && delFlag.equals("yes")) || mfile.isEmpty())) {
			// 저장 폴더에서 이전 폴더 삭제
			new File(savePath + "\\" + recrBoard.getRecrRenameFilename()).delete();
			// board 안의 파일 정보도 제거함
			recrBoard.setRecrOriginalFilename(null);
			recrBoard.setRecrRenameFilename(null);
		}

		// 2. 첨부파일이 있었지만 새로운 파일이 업로드 된 경우
		if (recrBoard.getRecrOriginalFilename() != null
				&& ((delFlag != null && delFlag.equals("yes")) && !mfile.isEmpty())) {
			// 저장 폴더에서 이전 폴더 삭제
			new File(savePath + "\\" + recrBoard.getRecrRenameFilename()).delete();

			String fileName = mfile.getOriginalFilename();
			String renameFileName = null;
			// 바뀐 파일정보 넎기
			if (fileName != null && fileName.length() > 0) {
				// 바꿀 파일명에 대한 문자열 포멧 만들기
				renameFileName = FileNameChange.change(fileName, "yyyyMMddhhmmss");
				logger.info("파일명 변경 : " + fileName + " => " + renameFileName);

				try {
					// 지정한 저장 폴더에 파일명 바꾸기 처리함
					mfile.transferTo(new File(savePath + "\\" + renameFileName));
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("message", "첨부파일 파일명 변환 중 에러가 발생했습니다. ");
					model.addAttribute("categoryId", categoryId);
					model.addAttribute("page", page);
					return "recerBoarad/RecrBoardList";
				}
			} /// 파일명 바꾸기

			recrBoard.setRecrOriginalFilename(fileName);
			recrBoard.setRecrRenameFilename(renameFileName);
		}

		// 3. 첨부파일이 없었는데 새로운 파일이 업로드 된 경우
		if (!mfile.isEmpty()) {
			// 전송온 첨부파일명 추출함
			String fileName = mfile.getOriginalFilename();
			String renameFileName = null;

			// 저장 폴더에는 변경된 파일 이름으로 파일 저장
			// 파일 이름 바꾸기 => 년월일시분초.확장자
			if (fileName != null && fileName.length() > 0) {
				// 바꿀 파일명에 대한 문자열 포멧 만들기
				renameFileName = FileNameChange.change(fileName, "yyyyMMddhhmmss");
				logger.info("파일명 변경 : " + fileName + " => " + renameFileName);

				try {
					// 지정한 저장 폴더에 파일명 바꾸기 처리함
					mfile.transferTo(new File(savePath + "\\" + renameFileName));
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("message", "첨부파일 저장 실패");
					return "common/error";
				}
			} /// 파일명 바꾸기

			recrBoard.setRecrOriginalFilename(fileName);
			recrBoard.setRecrRenameFilename(renameFileName);
		}

		// 입력한 정보 update
		if (recrBoardService.updateBoard(recrBoard) > 0) {
			model.addAttribute("page", currentPage);
			model.addAttribute("boardId", recrBoard.getBoardId());
			model.addAttribute("categoryId", categoryId);
			return "redirect: rbdetail.do";
		} else {
			model.addAttribute("page", page);
			model.addAttribute("boardId", recrBoard.getBoardId());
			model.addAttribute("message", "오류! 수정에 실패하였습니다");
			model.addAttribute("categoryId", categoryId);
			return "redirect: rbdetail.do";

		}

	}//

	
	@RequestMapping("deleteboard.do")
	public String deleteBoard(RecrBoard recrBoard, @RequestParam("page") String page, Model model, 
			@RequestParam("categoryId") int categoryId) {
		int currentPage = 1;
		if (!page.isEmpty()) {
			currentPage = Integer.parseInt(page);
		}
		commentRecrBoardService.deleteBoardComment(recrBoard.getBoardId());
		if(recrBoardService.deleteBoard(recrBoard.getBoardId()) > 0 ) {
			model.addAttribute("message", "글이 삭제되었습니다");
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("categoryId", categoryId);
			return"redirect:rblist.do";
		}else {
			model.addAttribute("boardId", recrBoard.getBoardId());
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("message", "error! 글 삭제에 실패하였습니다!");
			model.addAttribute("categoryId", categoryId);
			return"redirect:rblist.do";
		}
	}//
	
	
	// 글 신고 처리
	@RequestMapping("rbreport.do")
	public String rbreport(@RequestParam("page") String page, Model model, 
			@RequestParam("categoryId") int categoryId, @RequestParam("boardId") int boardId, 
			HttpServletRequest request, HttpServletResponse response) {
			
			String articleId = "RecrBoardReport_" + boardId;
			boolean isReported = false;
			// 쿠키에서 해당 게시물의 신고 여부를 확인
			        Cookie viewCookie = WebUtils.getCookie(request, articleId);
			        if (viewCookie != null) {
			        	isReported = true;
			        }

			        if (!isReported) {
			            // 여기에 신고 증가 처리
			            if(recrBoardService.boardReport(boardId) > 0) {
			            	// 새 쿠키 생성 및 설정
				            Cookie newCookie = new Cookie(articleId, "true");
				            newCookie.setMaxAge(3 * 24 * 60 * 60); // 쿠키 유효 시간: 3일
				            newCookie.setPath("/");
				            response.addCookie(newCookie);
				            model.addAttribute("message", "신고 처리되었습니다.");
			            }else {
				            model.addAttribute("message", "신고 처리중 오류가 발생하였습니다.");
			            }

			            
			        }else {
			        	model.addAttribute("message", "이미 신고 처리된 게시물 입니다.");
			        }
		
		RecrBoard recrBoard = recrBoardService.selectBoardId(boardId);
		model.addAttribute("RecrBoard", recrBoard);
		model.addAttribute("currentPage", page);
		model.addAttribute("categoryId", categoryId);		     
		return "recrBoard/RecrBoardDetail";
	}//
	
	
	
	// 파일 다운로드 처리
	@RequestMapping("rbdown.do")
	public ModelAndView fileDown(@RequestParam("ofile") String originalFileName,
			@RequestParam("rfile") String renameFileName, ModelAndView mv, HttpServletRequest request) {
		String savePath = request.getSession().getServletContext().getRealPath("resources/recrboard_upfiles");

		File readFile = new File(savePath + File.separator + renameFileName);

		File originFile = new File(originalFileName);

		mv.setViewName("filedown");
		mv.addObject("renameFile", readFile);
		mv.addObject("originFile", originFile);
		return mv;
	}
	
	// 글 모집 종료 처리 
	@RequestMapping("closerecr.do")
	public String closerecr(@RequestParam("page") int page, 
			@RequestParam("categoryId") int categoryId, 
			@RequestParam("boardId") int boardId,
			Model model) {
		model.addAttribute("boardId", boardId);
		model.addAttribute("page", page);
		model.addAttribute("categoryId", categoryId);
		if(recrBoardService.closerecr(boardId) > 0) {
			model.addAttribute("message", "모집이 종료되었습니다.");
			return "redirect:rbdetail.do";
		}else {
			model.addAttribute("message", "error! 글 모집 종료에 실패하였습니다!");
			return "redirect:rbdetail.do";
		}
			
	}//

	// ****************************** 이동용 *********************************

	// 글 수정 페이지로 이동
	@RequestMapping("updateboard.do")
	public String moveUpdateBoard(@RequestParam("boardId") String boardID, Model model, @RequestParam("categoryId") int categoryId, 
									@RequestParam("page") int page) {
		int boardId = Integer.parseInt(boardID);

		RecrBoard recrBoard = recrBoardService.selectBoardId(boardId);
		model.addAttribute("RecrBoard", recrBoard);
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("page", page);
		return "recrBoard/RecrBoardUpdateForm";
	}

	// 자세히 보기 페이지로 이동
	@RequestMapping("rbdetail.do")
	public String moveRecrBoardDetail(Model model, @RequestParam("boardId") int boardId,
			@RequestParam("page") String currentPage1, @RequestParam("categoryId") String categoryId1, 
			HttpServletRequest request, HttpServletResponse response, @RequestParam(name="message", required = false) String message) {
		int currentPage = 1;
		if (currentPage1 != null) {
			currentPage = Integer.parseInt(currentPage1);
		}
		int categoryId = 1;
		if(categoryId1 != null) {
			categoryId = Integer.parseInt(categoryId1);
		}
		
		String articleId = "RecrBoard" + boardId;
		boolean isViewed = false;
		
		Cookie viewCookie = WebUtils.getCookie(request, articleId);
        if (viewCookie != null) {
            isViewed = true;
        }

        if (!isViewed) {
        	// 조회수 증가 처리
        	recrBoardService.upReadCount(boardId);
        	
            // 새 쿠키 생성 및 설정
            Cookie newCookie = new Cookie(articleId, "true");
            newCookie.setMaxAge(24 * 60 * 60); // 쿠키 유효 시간: 24시간
            newCookie.setPath("/");
            response.addCookie(newCookie);
        }
		
        ArrayList<RecrAppl> applList = recrApplService.selectBoardId(boardId);
		
		
		RecrBoard recrBoard = recrBoardService.selectBoardId(boardId);
		model.addAttribute("applList", applList);
		model.addAttribute("RecrBoard", recrBoard);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("message", message);
		return "recrBoard/RecrBoardDetail";
	}

	// 글 작성 폼으로 이동
	@RequestMapping("rbwriteform.do")
	public String moveRecrBoardWriteForm(@RequestParam("page") String page, Model model, @RequestParam("categoryId") int categoryId) {
		model.addAttribute("page", page);
		model.addAttribute("categoryId", categoryId);
		return "recrBoard/RecrBoardWriteForm";
	}

//	Board board, Model model, HttpServletRequest request, 
//	@RequestParam(name="upfile", required=false) MultipartFile mfile

}// CLASS
