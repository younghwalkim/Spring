package com.project.getdrive.drive.controller;


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

import com.project.getdrive.drive.handler.Aws;
import com.project.getdrive.drive.model.service.DriveService;
import com.project.getdrive.drive.model.vo.Drive;
import com.project.getdrive.folder.model.service.FolderService;
import com.project.getdrive.folder.model.vo.Folder;
import com.project.getdrive.member.model.vo.Member;

@Controller
public class DriveController {
	private static final Logger logger = LoggerFactory.getLogger(DriveController.class);
	
	@Autowired
	private DriveService driveService;
	@Autowired
	private FolderService folderService;
	
	// 드라이브 메인으로 이동하자마자 휴지통 생성
	@RequestMapping(value="dmain.do", method={RequestMethod.POST, RequestMethod.GET})
	public String moveDriveMainPage(HttpServletRequest request, Model model) {
		
		Member member = (Member) request.getSession().getAttribute("loginMember");
		int tUID = member.getAccountNo();
		int tNo = (int) request.getSession().getAttribute("tNo");
		
		// insert를 위한 객체
		Drive drive = new Drive();
		drive.setdTID(tNo);
		drive.setdCRUID(tUID);
		
		// 휴지통 (index: 0, 드라이브 고유 번호: 1) 먼저 생성
		Aws aws = new Aws();
		
		// 휴지통이 생성되지 않았다면 생성
		if(driveService.checkTrash(drive) <= 0) {
			if(driveService.insertTrashCan(drive) > 0) {
				aws.createBucket("team"+tNo + "-trash");
			}		
		}
		
		// 존재하는 드라이브 리스트 조회
		ArrayList<Drive> list = driveService.selectDriveList(drive);
		model.addAttribute("list", list);
		 
		return "drive/driveMain";			
	}
	
	// 드라이브 생성
	@RequestMapping(value="idrive.do", method={RequestMethod.POST, RequestMethod.GET})
	public String createDrive(Model model, HttpServletRequest request,
			@RequestParam("tNo") int tNo,
			@RequestParam("dName") String dName) {
		
		Member member = (Member) request.getSession().getAttribute("loginMember");
		int tUID = member.getAccountNo();
		
		// 드라이브 생성을 위한 객체
		Drive drive = new Drive();
		drive.setdTID(tNo);
		drive.setdName(dName);
		drive.setdCRUID(tUID);
		
		int result = driveService.createDrive(drive);
		int count = driveService.selectDriveCount(tNo) - 1;
		
		Aws aws = new Aws();
		
		if(result > 0) {
			// 네이버 버킷에 담을 고유 이름
			aws.createBucket("team"+tNo+"drive-"+count);
			aws.makeBucketPublic("team"+tNo+"drive-"+count);
			return "redirect:dmain.do";
		} else {
			model.addAttribute("message", "드라이브 생성 실패");
			return "common/error";
		}
	}
	
	// 드라이브 내부 이동
	@RequestMapping(value="dpage.do", method={RequestMethod.POST, RequestMethod.GET})
	public String moveDrivePage(Model model,
			@RequestParam("tNo") int tNo,
			@RequestParam("dNo") int dNo,
			@RequestParam("tUID") int tUID) {
		
		Drive drive = driveService.selectDrive(dNo);
		model.addAttribute("drive", drive);
		
		ArrayList<Folder> list = folderService.selectFolderList(dNo);
		if(list != null && list.size() > 0) {			
			model.addAttribute("list", list);
		}
		 	
		if(drive != null) {
			return "drive/driveDetail";
		} else {
			model.addAttribute("message", "드라이브 진입 실패");
			return "common/error";
		}
	}
	
	
	
	
	
	
	
	
}
