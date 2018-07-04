package com.lbc.calculator.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.lbc.calculator.domain.LessonData;
import com.lbc.calculator.domain.Problem;
import com.lbc.calculator.persistence.ProblemDAO;

@Service
public class ProblemServiceImpl implements ProblemService{

	@Inject
	private ProblemDAO problemDAO;
	
	@Override
	public Problem getProblem(int num) {
		return problemDAO.getProblem(num);
	}

	@Override
	public LessonData getLesson(LessonData lessonData) {
		return problemDAO.getLesson(lessonData);
	}

	@Override
	public int getProblemCount() {
		return problemDAO.getProblemCount();
	}


}
