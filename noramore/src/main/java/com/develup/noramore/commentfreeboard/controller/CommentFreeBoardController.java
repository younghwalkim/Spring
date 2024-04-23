package com.develup.noramore.commentfreeboard.controller;

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

import com.develup.noramore.commentfreeboard.model.service.CommentFreeBoardService;
import com.develup.noramore.commentfreeboard.model.vo.CommentFreeBoard;
import com.develup.noramore.freeboard.model.service.FreeBoardService;

@Controller
public class CommentFreeBoardController {
	
	@Autowired
	CommentFreeBoardService commentFreeBoardService;
	@Autowired
	private FreeBoardService freeBoardService;
	
	// 대댓글 달기
	@RequestMapping(value = "insertfreecocomment.do", method = RequestMethod.POST)
	public String insertFreeComment(CommentFreeBoard commentFreeBoard, Model model, @RequestParam("page") String page, 
									@RequestParam("refCommentId1") String refCommentId1) {
		System.out.println("들어온 값" + refCommentId1);
		int refCommentId = Integer.parseInt(refCommentId1);
		commentFreeBoard.setRefCommentId(refCommentId);
		if (commentFreeBoardService.insertFreeComment(commentFreeBoard) > 0
				&& freeBoardService.upCountComment(commentFreeBoard.getBoardId()) > 0) {
			model.addAttribute("message", "댓글이 등록되었습니다.");
			model.addAttribute("boardId", commentFreeBoard.getBoardId());
			model.addAttribute("page", page);
			return "redirect:fbdetail.do";
		} else {
			model.addAttribute("message", "error! 댓글이 등록에 실패하였습니다.");
			model.addAttribute("boardId", commentFreeBoard.getBoardId());
			model.addAttribute("page", page);
			return "redirect:fbdetail.do";
		}
	}//
	
	// 댓글 달기
	@RequestMapping(value = "insertfreecomment.do", method = RequestMethod.POST)
	public String insertFreeCocoment(CommentFreeBoard commentFreeBoard, Model model,
			@RequestParam("page") String page) {
		if (commentFreeBoardService.insertFreeComment(commentFreeBoard) > 0
				&& freeBoardService.upCountComment(commentFreeBoard.getBoardId()) > 0) {
			commentFreeBoardService.upcountcocoment(commentFreeBoard);
			model.addAttribute("message", "대댓글이 등록되었습니다.");
			model.addAttribute("boardId", commentFreeBoard.getBoardId());
			model.addAttribute("page", page);
			return "redirect:fbdetail.do";
		} else {
			model.addAttribute("message", "error! 대댓글이 등록에 실패하였습니다.");
			model.addAttribute("boardId", commentFreeBoard.getBoardId());
			model.addAttribute("page", page);
			return "redirect:fbdetail.do";
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
	@RequestMapping(value = "selectfreecomment.do", method = RequestMethod.POST)
	@ResponseBody
	public String selectFreeComment(@RequestParam("BoardId") String Id) {
		int boardId = Integer.parseInt(Id);
		ArrayList<CommentFreeBoard> list = commentFreeBoardService.selectFreeComment(boardId);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
		JSONArray jarr = new JSONArray();
		for (CommentFreeBoard commentFreeBoard : list) {
			JSONObject job = new JSONObject();
			String lud = dateFormat.format(commentFreeBoard.getLastUpdateDate());
			job.put("memberId", commentFreeBoard.getMemberId());
			job.put("commentId", commentFreeBoard.getCommentId());
			job.put("context", commentFreeBoard.getContext());
			job.put("countSubComment", commentFreeBoard.getCountSubComment());
			job.put("refCommentId", commentFreeBoard.getRefCommentId());
			job.put("lastUpdateDate", lud);
	
			jarr.add(job);
		}
	
		return jarr.toJSONString();
	}//
	
	// 대댓글 출력
	@RequestMapping(value = "selectfreecocomment.do", method = RequestMethod.POST)
	@ResponseBody
	public String selectFreeCocomment(CommentFreeBoard commentFreeBoard) {
		ArrayList<CommentFreeBoard> list = commentFreeBoardService.selectFreeCocomment(commentFreeBoard);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
		JSONArray jarr = new JSONArray();
		for (CommentFreeBoard commentFree : list) {
			JSONObject job = new JSONObject();
			String lud = dateFormat.format(commentFree.getLastUpdateDate());
			job.put("rci", commentFree.getRefCommentId());
			job.put("memberId", commentFree.getMemberId());
			job.put("context", commentFree.getContext());
			job.put("lastUpdateDate", lud);
	
			jarr.add(job);
		}
	
		return jarr.toJSONString();
	}//
	
	//댓글 삭제
	@RequestMapping(value="deletefreecomment.do", method = RequestMethod.POST)
	public String deleteComment(CommentFreeBoard commentFreeBoard, @RequestParam("page") String page, Model model) {
		if(commentFreeBoardService.deleteComment(commentFreeBoard) >0 && freeBoardService.downCount(commentFreeBoard.getBoardId()) > 0) {
			commentFreeBoardService.deleteSubComment(commentFreeBoard);
			freeBoardService.countcoment(commentFreeBoard);
			model.addAttribute("boardId", commentFreeBoard.getBoardId());
			model.addAttribute("page", page);
			return "redirect:fbdetail.do";
		}else {
			model.addAttribute("boardId", commentFreeBoard.getBoardId());
			model.addAttribute("page", page);
			return "redirect:fbdetail.do";
		}
		
	}
	
	//댓글 수정
	@RequestMapping(value="updatefreecomment.do", method = RequestMethod.POST)
	public String updateComment(CommentFreeBoard commentFreeBoard, @RequestParam("page") String page, Model model) {
		if( commentFreeBoardService.updateComment(commentFreeBoard) >0 ) {
			model.addAttribute("boardId", commentFreeBoard.getBoardId());
			model.addAttribute("page", page);
			return "redirect:fbdetail.do";
		}else {
			model.addAttribute("boardId", commentFreeBoard.getBoardId());
			model.addAttribute("page", page);
			return "redirect:fbdetail.do";
		}
		
	}



}
