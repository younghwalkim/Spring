package com.develup.noramore.recrappl.model.service;

import java.util.ArrayList;

import com.develup.noramore.recrappl.model.vo.RecrAppl;

public interface RecrApplService {

	int insertAppl(RecrAppl recrAppl);


	int searchAppl(RecrAppl recrAppl);


	ArrayList<RecrAppl> selectBoardId(int boardId);


	int cancelAppl(RecrAppl recrAppl);


	int applyAppl(RecrAppl recrAppl);

}
