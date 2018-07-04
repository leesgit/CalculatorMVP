package com.lbc.calculator.persistence;

import com.lbc.calculator.domain.LessonData;
import com.lbc.calculator.domain.Problem;

public interface ProblemDAO {
	
	public Problem getProblem(int num);
	
	public LessonData getLesson(LessonData data);
	
	public int getProblemCount();
	

}
