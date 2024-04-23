package com.develup.noramore.memadd.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develup.noramore.memadd.model.dao.MemAddDao;
import com.develup.noramore.memadd.model.vo.MemAdd;


@Service("memAddService")
public class MemAddServiceImpl implements MemAddService{
	
	@Autowired
	private MemAddDao memAddDao;
	
	@Override
	public MemAdd selectMemAdd(String memberid) {
		return memAddDao.selectMemAdd(memberid);
	}

	
	@Override
	public int selectArticleCount(String memberid) {

		return memAddDao.selectArticleCount(memberid);
	
	}
	
}
