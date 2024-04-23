package com.develup.noramore.admin.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develup.noramore.admin.model.dao.AdminDao;
import com.develup.noramore.admin.model.vo.BoardManage;
import com.develup.noramore.admin.model.vo.Flow;
import com.develup.noramore.admin.model.vo.ReportMember;
import com.develup.noramore.common.Paging;
import com.develup.noramore.common.Search;
import com.develup.noramore.common.SearchDate;
import com.develup.noramore.member.model.vo.Member;

@Service("AdminService")
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminDao adminDao;
		
	@Override
	public int selectReportedListCount() {
		return adminDao.selectReportedListCount();
	}

	@Override
	public List<BoardManage> selectReportedList(Paging paging) {
		return adminDao.selectReportedList(paging);
	}

	@Override
	public Flow selectEWFlowCount() {
		return adminDao.selectEWFlowCount();
	}

	@Override
	public int selectListCount() {
		return adminDao.selectListCount();
	}

	@Override
	public List<ReportMember> selectMemList(Paging paging) {
		return adminDao.selectMemList(paging);
	}

	@Override
	public int updateRestrict(Member member) {
		return adminDao.updateRestrict(member);
	}

	@Override
	public int updateReport(String memberID) {
		return adminDao.updateReport(memberID);
	}

	@Override
	public int selectSearchMemberIdCount(String keyword) {
		return adminDao.selectSearchMemberIdCount(keyword);
	}

	@Override
	public int selectSearchGenderCount(String keyword) {
		return adminDao.selectSearchGenderCount(keyword);
	}

	@Override
	public int selectSearchAgeCount(int age) {
		return adminDao.selectSearchAgeCount(age);
	}

	@Override
	public int selectSearchEnrollDateCount(SearchDate searchDate) {
		return adminDao.selectSearchEnrollDateCount(searchDate);
	}

	@Override
	public List<ReportMember> selectSearchMemberId(Search search) {
		return adminDao.selectSearchMemberId(search);
	}

	@Override
	public List<ReportMember> selectSearchGender(Search search) {
		return adminDao.selectSearchGender(search);
	}

	@Override
	public List<ReportMember> selectSearchAge(Search search) {
		return adminDao.selectSearchAge(search);
	}

	@Override
	public List<ReportMember> selectSearchEnrollDate(Search search) {
		return adminDao.selectSearchEnrollDate(search);
	}

	@Override
	public int selectReportedMemListCount() {
		return adminDao.selectReportedMemListCount();
	}

	@Override
	public List<ReportMember> selectReportedMemList(Paging paging) {
		return adminDao.selectReportedMemList(paging);
	}

	@Override
	public int updateRestrictRollback(Member member) {
		return adminDao.updateRestrictRollback(member);
	}

}
