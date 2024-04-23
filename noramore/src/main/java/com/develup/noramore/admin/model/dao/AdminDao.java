package com.develup.noramore.admin.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.develup.noramore.admin.model.vo.BoardManage;
import com.develup.noramore.admin.model.vo.Flow;
import com.develup.noramore.admin.model.vo.ReportMember;
import com.develup.noramore.common.Paging;
import com.develup.noramore.common.Search;
import com.develup.noramore.common.SearchDate;
import com.develup.noramore.member.model.vo.Member;

@Repository("AdminDao")
public class AdminDao {

	@Autowired 
	private SqlSessionTemplate sqlSessionTemplate;
	
	public int selectReportedListCount() {
		return sqlSessionTemplate.selectOne("admin.selectReportedListCount");
	}

	public List<BoardManage> selectReportedList(Paging paging) {
		return sqlSessionTemplate.selectList("admin.selectReportedList", paging);
	}

	public Flow selectEWFlowCount() {
		return sqlSessionTemplate.selectOne("admin.selectEWFlowCount");
	}

	public int selectListCount() {
		return sqlSessionTemplate.selectOne("admin.selectListCount");
	}

	public List<ReportMember> selectMemList(Paging paging) {
		return sqlSessionTemplate.selectList("admin.selectMemList", paging);
	}

	public int updateRestrict(Member member) {
		return sqlSessionTemplate.update("admin.updateRestrict", member);
	}

	public int updateReport(String memberID) {
		return sqlSessionTemplate.update("admin.updateReport", memberID);
	}

	public int selectSearchMemberIdCount(String keyword) {
		return sqlSessionTemplate.selectOne("admin.selectSearchMemberIdCount", keyword);
	}

	public int selectSearchGenderCount(String keyword) {
		return sqlSessionTemplate.selectOne("admin.selectSearchGenderCount", keyword);
	}

	public int selectSearchAgeCount(int age) {
		return sqlSessionTemplate.selectOne("admin.selectSearchAgeCount", age);
	}

	public int selectSearchEnrollDateCount(SearchDate searchDate) {
		return sqlSessionTemplate.selectOne("admin.selectSearchEnrollDateCount", searchDate);
	}

	public List<ReportMember> selectSearchMemberId(Search search) {
		return sqlSessionTemplate.selectList("admin.selectSearchMemberId", search);
	}

	public List<ReportMember> selectSearchGender(Search search) {
		return sqlSessionTemplate.selectList("admin.selectSearchGender", search);
	}

	public List<ReportMember> selectSearchAge(Search search) {
		return sqlSessionTemplate.selectList("admin.selectSearchAge", search);
	}

	public List<ReportMember> selectSearchEnrollDate(Search search) {
		return sqlSessionTemplate.selectList("admin.selectSearchEnrollDate", search);
	}

	public int selectReportedMemListCount() {
		return sqlSessionTemplate.selectOne("admin.selectReportedMemListCount");
	}

	public List<ReportMember> selectReportedMemList(Paging paging) {
		return sqlSessionTemplate.selectList("admin.selectReportedMemList", paging);
	}

	public int updateRestrictRollback(Member member) {
		return sqlSessionTemplate.update("admin.updateRestrictRollback", member);
	}

}
