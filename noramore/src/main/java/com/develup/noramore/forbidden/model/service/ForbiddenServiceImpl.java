package com.develup.noramore.forbidden.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develup.noramore.common.Paging;
import com.develup.noramore.common.Search;
import com.develup.noramore.forbidden.model.dao.ForbiddenDao;
import com.develup.noramore.forbidden.model.vo.Forbidden;

@Service("forbiddenService")
public class ForbiddenServiceImpl implements ForbiddenService{
	@Autowired
	public ForbiddenDao forbiddenDao;

	@Override
	public ArrayList<Forbidden> selectList(Paging paging) {
		return forbiddenDao.selectList(paging);
	}

	@Override
	public int selectListCount() {
		return forbiddenDao.selectListCount();
	}

	@Override
	public int insertForbidden(String fbWord) {
		return forbiddenDao.insertForbidden(fbWord);
	}

	@Override
	public int selectCheckFb(String fbWord) {
		return forbiddenDao.selectCheckFb(fbWord);
	}

	@Override
	public int deleteForbidden(String fbWord) {
		return forbiddenDao.deleteForbidden(fbWord);
	}

	@Override
	public int selectSearchForbiddenCount(String keyword) {
		return forbiddenDao.selectSearchForbiddenCount(keyword);
	}

	@Override
	public ArrayList<Forbidden> selectSearchForbidden(Search search) {
		return forbiddenDao.selectSearchForbidden(search);
	}
}
