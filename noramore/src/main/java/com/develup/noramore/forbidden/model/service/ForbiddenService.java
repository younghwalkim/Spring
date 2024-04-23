package com.develup.noramore.forbidden.model.service;

import java.util.ArrayList;

import com.develup.noramore.common.Paging;
import com.develup.noramore.common.Search;
import com.develup.noramore.forbidden.model.vo.Forbidden;

public interface ForbiddenService {

	ArrayList<Forbidden> selectList(Paging paging);

	int selectListCount();

	int insertForbidden(String fbWord);

	int selectCheckFb(String fbWord);

	int deleteForbidden(String fbWord);

	int selectSearchForbiddenCount(String keyword);

	ArrayList<Forbidden> selectSearchForbidden(Search search);
	
	
}
