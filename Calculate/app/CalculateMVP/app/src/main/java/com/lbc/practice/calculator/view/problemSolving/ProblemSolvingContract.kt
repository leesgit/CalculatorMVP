package com.lbc.practice.calculator.view.problemSolving

import android.widget.Toast
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent
import com.lbc.practice.calculator.adapter.ResultListContract
import io.reactivex.Observable


interface ProblemSolvingContract {

    interface ProblemView {
        fun setStart(num: Int)
        fun setProblem1()
        fun setProblem2(id:Int)
        fun setContent(result: String)
        fun setNext(result2: String, num: Int)
        fun setTrue()
        fun setFalse()
        fun setPosition()
        fun end()
        fun moveToCalculator()
        fun setCount(result: String)
        fun unAbleCalculator()
        fun Toast(msg: String)
        fun setRxChanged()
        fun setRxNonChanged()

    }

    interface ProblemPresenter {
        fun setStart(lessonType: Int)
        fun setProblem(lessonType: Int)
        fun checkAnswer(answer: String)
        fun setPlayingAdaterModel(model: ResultListContract.Model)
        fun setPlayingAdaterView(view: ResultListContract.View)
        fun setView(problemView: ProblemSolvingContract.ProblemView)
        fun rxChange(event : Observable<TextViewTextChangeEvent>)
        fun calculatorChance()
        fun unsubscribe()
    }
}