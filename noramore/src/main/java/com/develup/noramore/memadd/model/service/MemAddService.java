package com.develup.noramore.memadd.model.service;

import com.develup.noramore.memadd.model.vo.MemAdd;


public interface MemAddService {

	MemAdd selectMemAdd(String memberid);

	
	int selectArticleCount(String memberid);

}