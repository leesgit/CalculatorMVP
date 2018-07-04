package com.lbc.practice.calculator.data

class Problem {
    var problemNumber: Int?= null
    var problemContent: String? =null
    var correctAnswer: String? =null

    constructor(problemNumber: Int?) {
        this.problemNumber = problemNumber
    }

    constructor(problemContent: String?, correctAnswer: String?) {
        this.problemContent = problemContent
        this.correctAnswer = correctAnswer
    }

}