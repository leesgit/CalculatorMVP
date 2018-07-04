package com.lbc.calculator.service;

import com.lbc.calculator.domain.LessonData;
import com.lbc.calculator.domain.Problem;

public interface ProblemService {
	public Problem getProblem(int num);
	
	public LessonData getLesson(LessonData lessonData);
	
	public int getProblemCount();

}
