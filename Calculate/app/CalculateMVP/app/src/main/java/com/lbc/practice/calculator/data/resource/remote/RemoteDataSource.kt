package com.lbc.practice.calculator.data.resource.remote

import android.util.Log
import com.lbc.practice.calculator.data.LessonData
import com.lbc.practice.calculator.data.Problem
import com.lbc.practice.calculator.data.resource.DataSource
import com.lbc.practice.calculator.util.RetrofitManager
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class RemoteDataSource :DataSource {

    val Error = "통신 실패 했습니다."

    @Inject
    constructor()

    @Inject
    lateinit var retrofitManager: RetrofitManager

    @Inject
    lateinit var compositeDisposable: CompositeDisposable

    override fun getProblem(num: Int, loadDataCallBack: DataSource.LoadDataCallBack) {
        val disposable = retrofitManager.url!!.getProblem(Problem(num)).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    loadDataCallBack.onLoadData(data)
                }) { err ->
                    loadDataCallBack.onFailData(Error)
                }
        compositeDisposable.add(disposable);
    }

    override fun getLessonData(lessonType: Int, lessonNumber: Int, loadDataCallBack: DataSource.LoadDataCallBack2) {
        val disposable = retrofitManager.url!!.getLesson(LessonData(lessonType,lessonNumber)).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    loadDataCallBack.onLoadData(data)
                }) { err ->
                    loadDataCallBack.onFailData(Error)

                }
        compositeDisposable.add(disposable);

    }

    override fun getProblemCount(loadDataCallBack: DataSource.LoadDataCallBack3) {
        val disposable = retrofitManager.url!!.getCount().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    loadDataCallBack.onLoadData(data)
                }) { err ->
                    loadDataCallBack.onFailData(Error)

                }
        compositeDisposable.add(disposable);

    }




}
