package com.develup.noramore.alarm.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develup.noramore.alarm.model.dao.AlarmDao;
import com.develup.noramore.alarm.model.vo.Alarm;
import com.develup.noramore.common.Paging;

@Service
public class AlarmServiceImpl implements AlarmService{
	@Autowired
	private AlarmDao alarmDao;

	@Override
	public ArrayList<Alarm> selectAlarmList(Alarm alarm) {
		return alarmDao.selectAlarmList(alarm);
	}


	@Override
	public int updateAlarm(int alarmId) {
		return alarmDao.updateAlarm(alarmId);
	}

	@Override
	public int selectListCount(String memberId) {
		return alarmDao.selectListCount(memberId);
	}

	@Override
	public ArrayList<Alarm> selectList(Alarm alarm) {
		return alarmDao.selectList(alarm);
	}

	@Override
	public int insertCommAlarm(Alarm alarm) {
		return alarmDao.insertCommAlarm(alarm);
	}
	
	@Override
	public int insertCommRefAlarm(Alarm alarm) {
		return alarmDao.insertCommRefAlarm(alarm);
	}
	
	@Override
	public int insertApplAlarm(Alarm alarm) {

		return alarmDao.insertApplAlarm(alarm);
	}



	@Override
	public int selectNewCount(String memberId) {
		return alarmDao.selecdtNewCount(memberId);
	}



}
