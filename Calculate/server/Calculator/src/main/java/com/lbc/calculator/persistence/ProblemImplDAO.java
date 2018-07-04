package com.lbc.calculator.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.lbc.calculator.domain.LessonData;
import com.lbc.calculator.domain.Problem;

@Repository
public class ProblemImplDAO implements ProblemDAO{
	
	@Inject
	private SqlSession session;
	
	private static String namespace = "org.zerock.mapper.CMapper";

	@Override
	public Problem getProblem(int num) {
		return session.selectOne(namespace+".getProplem",num);
	}

	@Override
	public LessonData getLesson(LessonData lessonData ) {
		return session.selectOne(namespace+".getLessonInfo",lessonData);
	}

	@Override
	public int getProblemCount() {
		return session.selectOne(namespace+".getProblemCount");
	}


}
