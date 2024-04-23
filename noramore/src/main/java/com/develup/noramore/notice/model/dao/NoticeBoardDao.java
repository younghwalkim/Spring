package com.develup.noramore.notice.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.develup.noramore.common.Paging;
import com.develup.noramore.common.Search;
import com.develup.noramore.common.SearchDate;
import com.develup.noramore.notice.model.vo.Notice;
import com.develup.noramore.qna.model.vo.Qna;

@Repository("noticeBoardDao")
public class NoticeBoardDao {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public int selectListCount() {		
		return sqlSessionTemplate.selectOne("noticeMapper.selectListCount");
	}

	public ArrayList<Notice> selectList(Paging paging) {		
		List<Notice> list = sqlSessionTemplate.selectList("noticeMapper.selectList", paging);
		return (ArrayList<Notice>)list;
	}

	public Notice selectOne(int noticeNo) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("noticeMapper.selectOne",noticeNo);
	}
	
	public int addReadCount(int noticeNo) {
		return sqlSessionTemplate.update("noticeMapper.addReadCount", noticeNo);
		
	}
	
	//새 공지글 등록
	public int insertNotice(Notice notice) {
		return sqlSessionTemplate.insert("noticeMapper.insertNotice", notice);
	}
	
	//공지글 수정
	public int updateNotice(Notice notice) {
		return sqlSessionTemplate.update("noticeMapper.updateNotice", notice);
	}
	
	//공지글 삭제
	public int deleteNotice(int noticeNo) {
		return sqlSessionTemplate.delete("noticeMapper.deleteNotice", noticeNo);
	}

	public int selectSearchTitleCount(String keyword) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("noticeMapper.selectSearchTitleCount", keyword);
	}

	public int selectSearchContentCount(String keyword) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("noticeMapper.selectSearchContentCount", keyword);
	}

	public int selectSearchDateCount(SearchDate date) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("noticeMapper.selectSearchDateCount", date);
	}

	public ArrayList<Notice> selectSearchTitle(Search search) {
		List<Notice> list = sqlSessionTemplate.selectList("noticeMapper.selectSearchTitle", search);
		return (ArrayList<Notice>)list;
	}

	public ArrayList<Notice> selectSearchTitle(String keyword) {
		List<Notice> list = sqlSessionTemplate.selectList("noticeMapper.selectSearchTitle", keyword);
		return (ArrayList<Notice>)list;
	}

	public ArrayList<Notice> selectSearchContent(Search search) {
		List<Notice> list = sqlSessionTemplate.selectList("noticeMapper.selectSearchContent", search);
		return (ArrayList<Notice>)list;
	}

	public ArrayList<Notice> selectSearchDate(Search search) {
		List<Notice> list = sqlSessionTemplate.selectList("noticeMapper.selectSearchDate", search);
		return (ArrayList<Notice>)list;
	}

	public ArrayList<Notice> selectTop5() {
		List<Notice> list = sqlSessionTemplate.selectList("noticeMapper.selectTop5");
		return (ArrayList<Notice>)list;
	}

	
}



