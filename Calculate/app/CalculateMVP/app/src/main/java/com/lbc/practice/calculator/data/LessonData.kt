package com.lbc.practice.calculator.data

class LessonData {
    var lessonNum: Int?=null
    var lessonName: String?=null
    var problemNumber: Int?=null

    constructor(lessonNum: Int?, problemNumber: Int?) {
        this.lessonNum = lessonNum
        this.problemNumber = problemNumber
    }

    constructor(lessonName: String?) {
        this.lessonName = lessonName
    }


}
