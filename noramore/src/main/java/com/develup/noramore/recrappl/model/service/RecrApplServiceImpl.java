package com.develup.noramore.recrappl.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develup.noramore.recrappl.model.dao.RecrApplDao;
import com.develup.noramore.recrappl.model.vo.RecrAppl;

@Service
public class RecrApplServiceImpl implements RecrApplService{
	@Autowired
	private RecrApplDao recrApplDao; 
	
	@Override
	public int insertAppl(RecrAppl recrAppl) {
		return recrApplDao.insertAppl(recrAppl);
	}


	@Override
	public int searchAppl(RecrAppl recrAppl) {
		return recrApplDao.searchAppl(recrAppl);
	}


	@Override
	public ArrayList<RecrAppl> selectBoardId(int boardId) {
		return recrApplDao.selectBoardId(boardId);
	}


	@Override
	public int cancelAppl(RecrAppl recrAppl) {
		return recrApplDao.cancelAppl(recrAppl);
	}


	@Override
	public int applyAppl(RecrAppl recrAppl) {
		return recrApplDao.applyAppl(recrAppl);
	}




	
	
}//
