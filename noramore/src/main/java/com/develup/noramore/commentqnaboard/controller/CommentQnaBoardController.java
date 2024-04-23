package com.develup.noramore.commentqnaboard.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.develup.noramore.commentqnaboard.model.service.CommentQnaBoardService;
import com.develup.noramore.commentqnaboard.model.vo.CommentQnaBoard;
import com.develup.noramore.commentrecrboard.model.vo.CommentRecrBoard;
import com.develup.noramore.qna.model.service.QnaService;

@Controller
public class CommentQnaBoardController {
	
	@Autowired
	CommentQnaBoardService commentQnaBoardService;
	
	@Autowired
	private QnaService qnaBoardServie;
	
	// 댓글 달기
		@RequestMapping(value = "insertqnacomment.do", method = RequestMethod.POST)
		public String insertqnarCocoment(CommentQnaBoard commentqnarBoard, Model model,
				@RequestParam("page") String page) {
			
			if (commentQnaBoardService.insertQnaComment(commentqnarBoard) > 0) {
				qnaBoardServie.countcoment(commentqnarBoard);
				model.addAttribute("message", "댓글이 등록되었습니다.");
				model.addAttribute("no", commentqnarBoard.getBoardId());
				model.addAttribute("page", page);
				return "redirect:qnadetail.do";
			} else {
				model.addAttribute("message", "error! 댓글이 등록에 실패하였습니다.");
				model.addAttribute("no", commentqnarBoard.getBoardId());
				model.addAttribute("page", page);
				return "redirect:qnadetail.do";
			}
		}
	
		
		//대댓글 달기
		@RequestMapping(value = "insertqnacocomment.do", method = RequestMethod.POST)
		public String insertRecrComment(@RequestParam("boardId") String boardId, @RequestParam("memberId") String memberId, Model model, @RequestParam("page") String page, 
										@RequestParam("refCommentId1") int refCommentId1, @RequestParam("substance") String substance) {
			System.out.println("들어온 값" + refCommentId1);
			CommentQnaBoard commentQnaBoard = new CommentQnaBoard();
			commentQnaBoard.setBoardId(Integer.parseInt(boardId));
			commentQnaBoard.setMemberId(memberId);
			commentQnaBoard.setRefCommentId(refCommentId1);
			commentQnaBoard.setSubstance(substance);
			
			
			if (commentQnaBoardService.insertQnaCocomment(commentQnaBoard) > 0 ) {
				qnaBoardServie.countcoment(commentQnaBoard);
				model.addAttribute("message", "댓글이 등록되었습니다.");
				model.addAttribute("no", commentQnaBoard.getBoardId());
				model.addAttribute("page", page);
				return "redirect:qnadetail.do";
			} else {
				model.addAttribute("message", "error! 댓글이 등록에 실패하였습니다.");
				model.addAttribute("no", commentQnaBoard.getBoardId());
				model.addAttribute("page", page);
				return "redirect:qnadetail.do";
			}
		}//
		
		// 댓글 출력
		@RequestMapping(value = "selectqnacomment.do", method = RequestMethod.POST)
		@ResponseBody
		public String selectqnacomment(@RequestParam("boardId") String Id) {
			int boardId = Integer.parseInt(Id);
			ArrayList<CommentQnaBoard> list = commentQnaBoardService.selectQnaComment(boardId);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			JSONArray jarr = new JSONArray();
			for (CommentQnaBoard commentQnaBoard : list) {
				JSONObject job = new JSONObject();
				String lud = dateFormat.format(commentQnaBoard.getLastUpdateDate());
				job.put("memberId", commentQnaBoard.getMemberId());
				job.put("commentId", commentQnaBoard.getCommentId());
				job.put("substance", commentQnaBoard.getSubstance());
				job.put("countSubComment", commentQnaBoard.getCountSubComment());
				job.put("refCommentId", commentQnaBoard.getRefCommentId());
				job.put("lastUpdateDate", lud);

				jarr.add(job);
			}

			return jarr.toJSONString();
		}
	
		@RequestMapping(value="deleteqnacomment.do", method = RequestMethod.POST)
		@ResponseBody
		public String deleteQnaComment(CommentQnaBoard commentQnaBoard, @RequestParam("page") String page, Model model) {
			if(commentQnaBoardService.deleteQnaComment(commentQnaBoard) >0 && qnaBoardServie.countComment(commentQnaBoard.getBoardId()) > 0) {
				commentQnaBoardService.deleteQnaSubComment(commentQnaBoard);
				qnaBoardServie.countcoment(commentQnaBoard);
				model.addAttribute("message", "댓글이 삭제되었습니다.");
				model.addAttribute("boardId", commentQnaBoard.getBoardId());
				model.addAttribute("page", page);
				return "redirect:rbdetail.do";
			}else {
				model.addAttribute("message", "오류! 댓글 삭제에 실패하였습니다.");
				model.addAttribute("boardId", commentQnaBoard.getBoardId());
				model.addAttribute("page", page);
				return "redirect:rbdetail.do";
			}
			
		}

		
}///















