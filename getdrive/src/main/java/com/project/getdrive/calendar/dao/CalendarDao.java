package com.project.getdrive.calendar.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.getdrive.calendar.vo.Calendar;
import com.project.getdrive.common.CommonCL;

@Repository("calendarDao")
public class CalendarDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	//달력 리스트
	public ArrayList<Calendar> CalendarList() {
		List<Calendar> calendar =  sqlSessionTemplate.selectList("calendarMapper.calendarList");
		return (ArrayList<Calendar>) calendar;
		
	}
		//일정 입력
		public int scheduleInsert(Calendar calendar) {
			return sqlSessionTemplate.insert("calendarMapper.scheduleInsert", calendar);
		}
		
		//일정 리스트
		public ArrayList<Calendar> scheduleList(CommonCL commonCL) {
			List<Calendar> list = sqlSessionTemplate.selectList("calendarMapper.scheduleList", commonCL);
			return (ArrayList<Calendar>)list;
		}
		
		//상세보기 조회처리
		public Calendar scheduleView(int calendarNo) {
			return sqlSessionTemplate.selectOne("calendarMapper.scheduleView", calendarNo);
		}
		
		//일정 삭제 처리
		public int scheduleDelete(int calendarNo) {
			return sqlSessionTemplate.delete("calendarMapper.scheduleDelete", calendarNo);
		}
		
		//일정 수정페이지 이동
		public Calendar scheduleSelect(int calendarNo) {
			return sqlSessionTemplate.selectOne("calendarMapper.scheduleSelect", calendarNo);
		}	
		
		
		//일정 수정 처리
		public int scheduleUpdate(Calendar calendar) {
			return sqlSessionTemplate.update("calendarMapper.scheduleUpdate", calendar);
		}
		

}
