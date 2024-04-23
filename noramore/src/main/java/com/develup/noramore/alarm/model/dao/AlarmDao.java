package com.develup.noramore.alarm.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.develup.noramore.alarm.model.vo.Alarm;
import com.develup.noramore.common.Paging;

@Repository("alarmDao")
public class AlarmDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public ArrayList<Alarm> selectAlarmList(Alarm alarm) {
		List list = sqlSessionTemplate.selectList("alarm.selectAlarmList", alarm);
		return (ArrayList<Alarm>)list;
	}



	public int updateAlarm(int alarmId) {
		return sqlSessionTemplate.update("alarm.updateAlarm", alarmId);
	}

	public int selectListCount(String memberId) {
		return sqlSessionTemplate.selectOne("alarm.selectListCount", memberId);
	}

	public ArrayList<Alarm> selectList(Alarm alarm) {
		List list = sqlSessionTemplate.selectList("alarm.selectList", alarm);
		return (ArrayList)list;
	}

	public int insertCommAlarm(Alarm alarm) {
		return sqlSessionTemplate.insert("alarm.insertCommAlarm", alarm);
	}
	
	public int insertCommRefAlarm(Alarm alarm) {
		return sqlSessionTemplate.insert("alarm.insertCommRefAlarm", alarm);
	}
	
	public int insertApplAlarm(Alarm alarm) {
		return sqlSessionTemplate.insert("alarm.insertApplAlarm", alarm);
	}

	public int selecdtNewCount(String memberId) {
		return sqlSessionTemplate.selectOne("alarm.selectNewCount", memberId);
	}




}
