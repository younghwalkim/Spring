package com.project.getdrive.calendar.service;

import java.util.ArrayList;

import com.project.getdrive.calendar.vo.Calendar;
import com.project.getdrive.common.CommonCL;


public interface CalendarService {
	
	//달력 리스트
	public ArrayList<Calendar> CalendarList();
	
	//일정 입력
	public int scheduleInsert(Calendar calendar);
	
	
	//일정 리스트 출력
	public ArrayList<Calendar> scheduleList(CommonCL commonCL);
	
	//상세보기 조회
	public Calendar scheduleView(int calendarNo);
	
	//삭제
	public int scheduleDelete(int calendarNo);
	
	//수정페이지 이동
	public Calendar scheduleSelect(int calendarNo);
	
	//수정
	public int scheduleUpdate(Calendar calendar);

	




	}
