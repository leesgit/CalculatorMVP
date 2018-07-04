package com.lbc.practice.calculator.view.claculator

import io.reactivex.Observable

interface CalculatorContractor {

    interface CalculatorView {
        fun setScore(result :String)
        fun numMusicStart(position: Int)
        fun clearNum()
        fun setResult(result: String)
        fun Toast(comment:String)
        fun setRxResult(result: String)
    }

    interface CalculatorPresenter {
        fun setView(view: CalculatorContractor.CalculatorView)
        fun btnNum(position: Int)
        fun btnSymbol(id: Int)
        fun btnOption(id :Int)
        fun btnPoint()
        fun rxCalculate(event :Observable<CharSequence> )
        fun unsubscribe()
    }
}