package com.lbc.calculator.domain;

public class LessonData {

    public int lessonNum;
    public String lessonName;
    public int problemNumber;
    
    
	public int getLessonNumber() {
		return lessonNum;
	}
	public void setLessonNumber(int lessonNum) {
		this.lessonNum = lessonNum;
	}
	public String getLessonName() {
		return lessonName;
	}
	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}
	public int getProblemCount() {
		return problemNumber;
	}
	public void setProblemCount(int problemNumber) {
		this.problemNumber = problemNumber;
	}
	public LessonData(int lessonNum) {
		super();
		this.lessonNum = lessonNum;
	}
	public LessonData(String lessonName) {
		super();
		this.lessonName = lessonName;
	}
	public LessonData(int lessonNum, int problemNumber) {
		super();
		this.lessonNum = lessonNum;
		this.problemNumber = problemNumber;
	}

  

}
