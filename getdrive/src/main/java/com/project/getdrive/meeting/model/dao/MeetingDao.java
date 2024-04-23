package com.project.getdrive.meeting.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.getdrive.common.Paging;
import com.project.getdrive.common.SearchPaging;
import com.project.getdrive.meeting.model.vo.Meeting;

@Repository("meetingDao")
public class MeetingDao {
	//마이바티스 매퍼 파일에 쿼리문 별도록 작성함
	//root-context.xml 에 지정된 마이바티스 연결 객체를 사용함
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	//회의글 총갯수
	public int getListCount(int tNo) {
		return sqlSessionTemplate.selectOne("meetingMapper.getListCount", tNo);
	}
	
	//회의글 페이지 단위로 목록 조회 : 회의 목록 보기용 
	public ArrayList<Meeting> selectList(Paging paging) {
		List<Meeting> list = sqlSessionTemplate.selectList("meetingMapper.selectList", paging);
		return (ArrayList<Meeting>)list;
	}

	//새 회의글 등록
	public int insertMeeing(Meeting meeting) {
		return sqlSessionTemplate.insert("meetingMapper.insertMeeing", meeting);
	}

	//회의글번호로 한 개 조회 :회의글 상세보기용
	public Meeting selectOne(int mtId) {
		return sqlSessionTemplate.selectOne("meetingMapper.selectOne", mtId);
	}

	public int addReadCount(int mtId) {
		return sqlSessionTemplate.update("meetingMapper.addReadCount", mtId);
	}

	public int updateMeeting(Meeting meeting) {
		return sqlSessionTemplate.update("meetingMapper.updateMeeting", meeting);
	}

	public int deleteMeeting(int mtId) {
		return sqlSessionTemplate.delete("meetingMapper.deleteMeeting", mtId);
	}

	public int getSearchTitleCount(String keyword) {
		return sqlSessionTemplate.selectOne("meetingMapper.getSearchTitleCount", keyword);
	}

	public ArrayList<Meeting> selectSearchTitle(SearchPaging search) {
		List<Meeting> list = sqlSessionTemplate.selectList("meetingMapper.selectSearchTitle", search);
		return (ArrayList<Meeting>)list;
	}

	public int getSearchContentCount(String keyword) {
		return sqlSessionTemplate.selectOne("meetingMapper.getSearchContentCount", keyword);
	}

	public ArrayList<Meeting> selectSearchContent(SearchPaging search) {
		List<Meeting> list = sqlSessionTemplate.selectList("meetingMapper.selectSearchContent", search);
		return (ArrayList<Meeting>)list;
	}

	public int getSearchDateCount(SearchPaging date) {
		return sqlSessionTemplate.selectOne("meetingMapper.getSearchDateCount", date);
	}

	public ArrayList<Meeting> selectSearchDate(SearchPaging search) {
		List<Meeting> list = sqlSessionTemplate.selectList("meetingMapper.selectSearchDate", search);
		return (ArrayList<Meeting>)list;
	}
	
	
	
	
}
