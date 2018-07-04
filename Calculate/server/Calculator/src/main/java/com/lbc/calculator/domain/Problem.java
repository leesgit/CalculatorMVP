package com.lbc.calculator.domain;

public class Problem {
    public int problemNumber;
    public String problemContent;
    public String correctAnswer;


	public Problem(String problemContent, String correctAnswer) {
		super();
		this.problemContent = problemContent;
		this.correctAnswer = correctAnswer;
	}

	public int getProblemNumber() {
		return problemNumber;
	}

	public String getProblemContent() {
		return problemContent;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public Problem(int problemNumber) {
		super();
		this.problemNumber = problemNumber;
	}

	public Problem(String problemContent) {
		super();
		this.problemContent = problemContent;
	}
    
    
}
