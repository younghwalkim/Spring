package com.develup.noramore.commentrecrboard.controller;

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

import com.develup.noramore.commentrecrboard.model.vo.CommentRecrBoard;
import com.develup.noramore.commentrecrboard.service.CommentRecrBoardService;
import com.develup.noramore.recrboard.model.service.RecrBoardService;

@Controller
public class CommentRecrBoardController {
	@Autowired
	CommentRecrBoardService commentRecrBoardService;
	@Autowired
	private RecrBoardService recrBoardService;

	// 대댓글 달기
	@RequestMapping(value = "insertrecrcocomment.do", method = RequestMethod.POST)
	public String insertRecrComment(CommentRecrBoard commentRecrBoard, Model model, @RequestParam("page") String page, 
									@RequestParam("refCommentId1") String refCommentId1, 
									@RequestParam("categoryId") int categoryId) {
		System.out.println("들어온 값" + refCommentId1);
		int refCommentId = Integer.parseInt(refCommentId1);
		commentRecrBoard.setRefCommentId(refCommentId);
		if (commentRecrBoardService.insertRecrComment(commentRecrBoard) > 0
				&& recrBoardService.upCountComment(commentRecrBoard.getBoardId()) > 0) {
			model.addAttribute("message", "댓글이 등록되었습니다.");
			model.addAttribute("boardId", commentRecrBoard.getBoardId());
			model.addAttribute("categoryId", categoryId);
			model.addAttribute("page", page);
			return "redirect:rbdetail.do";
		} else {
			model.addAttribute("message", "error! 댓글이 등록에 실패하였습니다.");
			model.addAttribute("boardId", commentRecrBoard.getBoardId());
			model.addAttribute("page", page);
			model.addAttribute("categoryId", categoryId);
			return "redirect:rbdetail.do";
		}
	}//

	// 댓글 달기
	@RequestMapping(value = "insertrecrcomment.do", method = RequestMethod.POST)
	public String insertRecrCocoment(CommentRecrBoard commentRecrBoard, Model model,
			@RequestParam("page") String page, @RequestParam("categoryId") int categoryId ) {
		if (commentRecrBoardService.insertRecrComment(commentRecrBoard) > 0
				&& recrBoardService.upCountComment(commentRecrBoard.getBoardId()) > 0) {
			commentRecrBoardService.upcountcocoment(commentRecrBoard);
			model.addAttribute("message", "댓글이 등록되었습니다.");
			model.addAttribute("boardId", commentRecrBoard.getBoardId());
			model.addAttribute("page", page);
			model.addAttribute("categoryId", categoryId);
			return "redirect:rbdetail.do";
		} else {
			model.addAttribute("message", "error! 댓글이 등록에 실패하였습니다.");
			model.addAttribute("boardId", commentRecrBoard.getBoardId());
			model.addAttribute("page", page);
			model.addAttribute("categoryId", categoryId);
			return "redirect:rbdetail.do";
		}
	}//

	/*
	 * // 댓글 삭제 ----- 아래 수정중
	 * 
	 * @RequestMapping("deletecommentttt.do") public String
	 * deletecomment(@RequestParam("page") int page, CommentRecrBoard
	 * commentRecrBoard, Model model) { if
	 * (commentRecrBoardService.deletecomment(commentRecrBoard) > 0) {
	 * model.addAttribute("page", page); model.addAttribute("boardId",
	 * commentRecrBoard.getBoardId()); return "redirect:rbdetail.do"; } else {
	 * model.addAttribute("message", "error! 댓글 삭제에 실패하였습니다.");
	 * model.addAttribute("boardId", commentRecrBoard.getBoardId());
	 * model.addAttribute("page", page); return "redirect:rbdetail.do"; }
	 * 
	 * }//
	 */
	// 댓글 출력
	@RequestMapping(value = "selectrecrcomment.do", method = RequestMethod.POST)
	@ResponseBody
	public String selectRecrComment(@RequestParam("BoardId") String Id) {
		int boardId = Integer.parseInt(Id);
		ArrayList<CommentRecrBoard> list = commentRecrBoardService.selectRecrComment(boardId);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		JSONArray jarr = new JSONArray();
		for (CommentRecrBoard commentRecrBoard : list) {
			JSONObject job = new JSONObject();
			String lud = dateFormat.format(commentRecrBoard.getLastUpdateDate());
			job.put("memberId", commentRecrBoard.getMemberId());
			job.put("commentId", commentRecrBoard.getCommentId());
			job.put("context", commentRecrBoard.getContext());
			job.put("countSubComment", commentRecrBoard.getCountSubComment());
			job.put("refCommentId", commentRecrBoard.getRefCommentId());
			job.put("lastUpdateDate", lud);

			jarr.add(job);
		}

		return jarr.toJSONString();
	}//


	
	//댓글 삭제
	@RequestMapping(value="deletecomment.do", method = RequestMethod.POST)
	public String deleteComment(CommentRecrBoard commentRecrBoard, @RequestParam("page") String page, Model model, 
			@RequestParam("categoryId") int categoryId ) {
		if(commentRecrBoardService.deleteComment(commentRecrBoard) >0 && recrBoardService.upCountComment(commentRecrBoard.getBoardId()) > 0) {
			commentRecrBoardService.deleteSubComment(commentRecrBoard);
			int result = recrBoardService.upCountComment(commentRecrBoard.getBoardId());
			recrBoardService.countcoment(commentRecrBoard);
			model.addAttribute("message", "댓글이 삭제되었습니다.");
			model.addAttribute("categoryId", categoryId);
			model.addAttribute("boardId", commentRecrBoard.getBoardId());
			model.addAttribute("page", page);
			return "redirect:rbdetail.do";
		}else {
			model.addAttribute("message", "오류! 댓글 삭제에 실패하였습니다.");
			model.addAttribute("boardId", commentRecrBoard.getBoardId());
			model.addAttribute("categoryId", categoryId);
			model.addAttribute("page", page);
			return "redirect:rbdetail.do";
		}
		
	}
	
	//댓글 수정
	@RequestMapping(value="updatecomment.do", method = RequestMethod.POST)
	public String updateComment(CommentRecrBoard commentRecrBoard, @RequestParam("page") String page, Model model, 
			@RequestParam("categoryId") int categoryId ) {
		if( commentRecrBoardService.updateComment(commentRecrBoard) >0 ) {
			model.addAttribute("message", "댓글이 수정되었습니다.");
			model.addAttribute("boardId", commentRecrBoard.getBoardId());
			model.addAttribute("categoryId", categoryId);
			model.addAttribute("page", page);
			return "redirect:rbdetail.do";
		}else {
			model.addAttribute("message", "오류! 수정에 실패하였습니다.");
			model.addAttribute("boardId", commentRecrBoard.getBoardId());
			model.addAttribute("categoryId", categoryId);
			model.addAttribute("page", page);
			return "redirect:rbdetail.do";
		}
		
	}
	
}//






























