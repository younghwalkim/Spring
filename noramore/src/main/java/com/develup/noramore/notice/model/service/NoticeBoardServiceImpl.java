package com.develup.noramore.notice.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develup.noramore.common.Paging;
import com.develup.noramore.common.Search;
import com.develup.noramore.common.SearchDate;
import com.develup.noramore.notice.model.dao.NoticeBoardDao;
import com.develup.noramore.notice.model.vo.Notice;
import com.develup.noramore.qna.model.vo.Qna;

@Service("noticeBoardService")
public class NoticeBoardServiceImpl implements NoticeBoardService{
	@Autowired
	public NoticeBoardDao noticeBoardDao;
	
	@Override
	public int selectListCount() {
		return noticeBoardDao.selectListCount();
	}

	@Override
	public ArrayList<Notice> selectList(Paging paging) {
		return noticeBoardDao.selectList(paging);
	}

	@Override
	public Notice selectOne(int noticeNo) {
		// TODO Auto-generated method stub
		return noticeBoardDao.selectOne(noticeNo);
	}

	@Override
	public void updateAddReadCount(int noticeNo) {		
		noticeBoardDao.addReadCount(noticeNo);		
	}

	@Override
	public int insertNotice(Notice notice) {
		
		return noticeBoardDao.insertNotice(notice);
	}

	@Override
	public int updateNotice(Notice notice) {
		// TODO Auto-generated method stub
		return noticeBoardDao.updateNotice(notice);
	}

	@Override
	public int deleteNotice(int noticeNo) {
		// TODO Auto-generated method stub
		return noticeBoardDao.deleteNotice(noticeNo);
	}

	@Override
	public int selectSearchTitleCount(String keyword) {
		// TODO Auto-generated method stub
		return noticeBoardDao.selectSearchTitleCount(keyword);
	}

	@Override
	public int selectSearchContentCount(String keyword) {
		// TODO Auto-generated method stub
		return noticeBoardDao.selectSearchContentCount(keyword);
	}

	@Override
	public int selectSearchDateCount(SearchDate date) {
		// TODO Auto-generated method stub
		return noticeBoardDao.selectSearchDateCount(date);
	}

	@Override
	public ArrayList<Notice> selectSearchTitle(Search search) {
		// TODO Auto-generated method stub
		return noticeBoardDao.selectSearchTitle(search);
	}

	@Override
	public ArrayList<Notice> selectSearchTitle(String keyword) {
		// TODO Auto-generated method stub
		return noticeBoardDao.selectSearchTitle(keyword);
	}

	@Override
	public ArrayList<Notice> selectSearchContent(Search search) {
		// TODO Auto-generated method stub
		return noticeBoardDao.selectSearchContent(search);
	}

	@Override
	public ArrayList<Notice> selectSearchDate(Search search) {
		// TODO Auto-generated method stub
		return noticeBoardDao.selectSearchDate(search);
	}

	@Override
	public ArrayList<Notice> selectTop5() {
		// TODO Auto-generated method stub
		return noticeBoardDao.selectTop5();
	}
}
