package com.lbc.practice.calculator.data.resource

import com.lbc.practice.calculator.data.LessonData
import com.lbc.practice.calculator.data.Problem

interface DataSource {

    interface LoadDataCallBack {
        fun onLoadData(info: Problem)
        fun onFailData(errorMsg: String)
    }

    interface LoadDataCallBack2 {
        fun onLoadData(info: LessonData)
        fun onFailData(errorMsg: String)
    }

    interface LoadDataCallBack3 {
        fun onLoadData(count : Integer)
        fun onFailData(errorMsg: String)
    }

    fun getProblem(num: Int, loadDataCallBack: LoadDataCallBack)

    fun getLessonData(lessonType: Int, lessonNumber: Int, loadDataCallBack: LoadDataCallBack2)

    fun getProblemCount(loadDataCallBack: LoadDataCallBack3)
}