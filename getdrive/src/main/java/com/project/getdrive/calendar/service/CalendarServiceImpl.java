package com.project.getdrive.calendar.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.getdrive.calendar.dao.CalendarDao;
import com.project.getdrive.calendar.vo.Calendar;
import com.project.getdrive.common.CommonCL;

@Service("CalendarService")
public class CalendarServiceImpl implements CalendarService {
	
	@Autowired
	private CalendarDao calendarDao;
	

	@Override //일정 입력
	public int scheduleInsert(Calendar calendar) {
		return calendarDao.scheduleInsert(calendar);
	}


	@Override //달력 리스트
	public ArrayList<Calendar> scheduleList(CommonCL commonCL) {
		return calendarDao.scheduleList(commonCL);
	}

	@Override //일정 리스트 출력
	public ArrayList<Calendar> CalendarList() {
		return calendarDao.CalendarList();
	}


	@Override //상세보기 조회
	public Calendar scheduleView(int calendarNo) {
		return calendarDao.scheduleView(calendarNo);
	}


	@Override //삭제
	public int scheduleDelete(int calendarNo) {
		return calendarDao.scheduleDelete(calendarNo);
	}
	
	@Override //수정페이지 이동
	public Calendar scheduleSelect(int calendarNo) {
		return calendarDao.scheduleSelect(calendarNo);
	}


	@Override //수정
	public int scheduleUpdate(Calendar calendar) {
		return calendarDao.scheduleUpdate(calendar);
	}


  }