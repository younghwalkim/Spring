package com.develup.noramore.notice.model.service;

import java.util.ArrayList;

import com.develup.noramore.common.Paging;
import com.develup.noramore.common.Search;
import com.develup.noramore.common.SearchDate;
import com.develup.noramore.notice.model.vo.Notice;
import com.develup.noramore.qna.model.vo.Qna;

public interface NoticeBoardService {

	Notice selectOne(int noticeNo);

	ArrayList<Notice> selectList(Paging paging);

	void updateAddReadCount(int noticeNo);

	int insertNotice(Notice notice);

	int updateNotice(Notice notice);

	int deleteNotice(int noticeNo);

	ArrayList<Notice> selectSearchTitle(Search search);

	ArrayList<Notice> selectSearchTitle(String keyword);

	ArrayList<Notice> selectSearchContent(Search search);

	ArrayList<Notice> selectSearchDate(Search search);

	int selectListCount();

	int selectSearchTitleCount(String keyword);

	int selectSearchContentCount(String keyword);

	int selectSearchDateCount(SearchDate date);
	
	 ArrayList<Notice> selectTop5();
}
