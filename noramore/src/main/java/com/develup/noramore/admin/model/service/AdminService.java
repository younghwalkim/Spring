package com.develup.noramore.admin.model.service;

import java.util.List;

import com.develup.noramore.admin.model.vo.BoardManage;
import com.develup.noramore.admin.model.vo.Flow;
import com.develup.noramore.admin.model.vo.ReportMember;
import com.develup.noramore.common.Paging;
import com.develup.noramore.common.Search;
import com.develup.noramore.common.SearchDate;
import com.develup.noramore.member.model.vo.Member;

public interface AdminService {

	int selectReportedListCount();

	List<BoardManage> selectReportedList(Paging paging);

	Flow selectEWFlowCount();

	int selectListCount();

	List<ReportMember> selectMemList(Paging paging);

	int updateRestrict(Member member);

	int updateReport(String memberID);

	int selectSearchMemberIdCount(String keyword);

	int selectSearchGenderCount(String keyword);

	int selectSearchAgeCount(int age);

	int selectSearchEnrollDateCount(SearchDate searchDate);

	List<ReportMember> selectSearchMemberId(Search search);

	List<ReportMember> selectSearchGender(Search search);

	List<ReportMember> selectSearchAge(Search search);

	List<ReportMember> selectSearchEnrollDate(Search search);

	int selectReportedMemListCount();

	List<ReportMember> selectReportedMemList(Paging paging);

	int updateRestrictRollback(Member member);

}
