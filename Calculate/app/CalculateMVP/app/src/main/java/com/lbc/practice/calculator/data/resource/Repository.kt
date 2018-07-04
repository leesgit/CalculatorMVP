package com.lbc.practice.calculator.data.resource

import android.util.Log
import com.lbc.practice.calculator.data.resource.remote.RemoteDataSource
import javax.inject.Inject


class Repository : DataSource {


    private var remoteDataSource: RemoteDataSource? = null


    @Inject
    constructor(remoteDataSource: RemoteDataSource?) {
        this.remoteDataSource = remoteDataSource
    }


    override fun getProblem( num: Int, loadDataCallBack: DataSource.LoadDataCallBack) {
        remoteDataSource?.getProblem(num, loadDataCallBack)
    }

    override fun getLessonData(lessonType: Int, lessonNumber: Int, loadDataCallBack: DataSource.LoadDataCallBack2) {
        remoteDataSource?.getLessonData(lessonType, lessonNumber, loadDataCallBack)
    }


    override fun getProblemCount(loadDataCallBack: DataSource.LoadDataCallBack3) {
        remoteDataSource?.getProblemCount(loadDataCallBack)
    }

}
