package com.lbc.practice.calculator.view.problemSolving

import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent
import com.lbc.practice.calculator.R
import com.lbc.practice.calculator.adapter.ResultListContract
import com.lbc.practice.calculator.data.LessonData
import com.lbc.practice.calculator.data.Problem
import com.lbc.practice.calculator.data.Result
import com.lbc.practice.calculator.data.resource.DataSource
import com.lbc.practice.calculator.data.resource.Repository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import java.util.concurrent.TimeUnit
import javax.inject.Inject


public class ProblemSolvingPresenter : ProblemSolvingContract.ProblemPresenter {



    var dataSource: DataSource
    var problemView: ProblemSolvingContract.ProblemView?=null
    var adapterView: ResultListContract.View? = null
    var adapterModel: ResultListContract.Model? = null
    var num =0
    var problemCount : Int?=null
    var calculatorCount =2


    @Inject
    constructor(repository: Repository) {
        this.dataSource = repository
    }

    @Inject
    lateinit var compositeDisposable : CompositeDisposable

    override fun setView(problemView: ProblemSolvingContract.ProblemView) {
        this.problemView = problemView
    }
    override fun setPlayingAdaterModel(model: ResultListContract.Model) {
        this.adapterModel = model
    }

    override fun setPlayingAdaterView(view: ResultListContract.View) {
        this.adapterView = view
    }

    override fun setStart(lessonType: Int) {
        dataSource.getProblemCount(object: DataSource.LoadDataCallBack3 {
            override fun onLoadData(count: Integer) {
                problemCount = count.toInt()
                problemView?.setStart(problemCount!!)
                setProblem(lessonType)
            }

            override fun onFailData(errorMsg: String) {
                problemView?.Toast(errorMsg)
            }
        } )
        problemView?.setCount("찬스 : "+calculatorCount)
    }

    override fun setProblem(lessonType: Int) {
        num++
        if (num > problemCount!!) {
            problemView?.end()
        } else {
            dataSource.getProblem(num, object : DataSource.LoadDataCallBack {
                override fun onLoadData(info: Problem) {
                    problemView?.setContent(info.problemContent!!)
                }

                override fun onFailData(errorMsg: String) {
                    problemView?.Toast(errorMsg)
                }
            })
            dataSource.getLessonData(lessonType, num, object : DataSource.LoadDataCallBack2 {
                override fun onLoadData(info: LessonData) {
                    problemView?.setNext( info.lessonName + " 기본학습 중입니다.",num)
                }

                override fun onFailData(errorMsg: String) {
                    problemView?.Toast(errorMsg)
                }
            })
            problemView!!.setProblem1()
        }
    }


    override fun checkAnswer(answer: String) {

        dataSource.getProblem(num, object : DataSource.LoadDataCallBack {
            override fun onLoadData(info: Problem) {
                if (info.correctAnswer.equals(answer)) {
                    adapterModel!!.addInfo(Result(num, true))
                    problemView?.setTrue()
                } else {
                    adapterModel!!.addInfo(Result(num, false))
                    problemView?.setFalse()
                }
                problemView?.setPosition()
                adapterView!!.notifyAdapter()
            }

            override fun onFailData(errorMsg: String) {

            }
        })
        if (num == problemCount) {
            problemView?.setProblem2(R.string.finish_lesson)
        } else {
            problemView?.setProblem2(R.string.next_problem)
        }
    }

    override fun calculatorChance() {
        if(calculatorCount>0) {
            calculatorCount--
            problemView?.moveToCalculator()

            problemView?.setCount("찬스 : "+calculatorCount)
            if (calculatorCount==0) {
                problemView?.unAbleCalculator()
            }
        }
    }

    override fun rxChange(event: Observable<TextViewTextChangeEvent>) {
        val disposable = event
                .debounce(400, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<TextViewTextChangeEvent>() {
                    override fun onNext(textViewTextChangeEvent: TextViewTextChangeEvent) {
                        if (textViewTextChangeEvent.count() > 0) {
                            problemView?.setRxChanged()
                        } else {
                            problemView?.setRxNonChanged()
                        }
                    }
                    override fun onError(e: Throwable) {
                    }
                    override fun onComplete() {

                    }
                })
        compositeDisposable.add(disposable)
    }

    override fun unsubscribe() {
        compositeDisposable.clear()
    }


}
