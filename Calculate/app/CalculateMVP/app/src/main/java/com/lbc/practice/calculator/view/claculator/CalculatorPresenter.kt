package com.lbc.practice.calculator.view.claculator

import android.widget.Toast
import com.lbc.practice.calculator.R
import com.lbc.practice.calculator.util.CalculateManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_calculator.*
import java.lang.StringBuilder
import java.util.*
import javax.inject.Inject

class CalculatorPresenter : CalculatorContractor.CalculatorPresenter {



    var statement = StringBuilder("0")
    var result: String? = null
    var symbol: Boolean = false
    var point: Boolean = false

    var calculatorView :CalculatorContractor.CalculatorView? = null

    @Inject
    constructor()

    @Inject
    lateinit var calc : CalculateManager

    @Inject
    lateinit var compositeDisposable: CompositeDisposable

    override fun setView(view: CalculatorContractor.CalculatorView) {
        calculatorView = view
    }

    override fun btnNum(position: Int) {

        val str = Integer.toString(position)
        if (statement.toString().equals("0")) {
            statement = StringBuilder(str)
        } else {
            statement = statement.append(str)
        }
        calculatorView?.setScore(statement.toString())
        calculatorView?.numMusicStart(position)
        symbol = true
        point = true
    }

    override fun btnSymbol(id: Int) {
        val num = StringTokenizer(statement.toString(), "+-/X")
        val oper = StringTokenizer(statement.toString(), "1234567890.")
        if (symbol == true && num.countTokens() >= oper.countTokens()) {
            when (id) {
                R.id.plus_button -> statement.append("+")
                R.id.minus_button -> statement.append("-")
                R.id.division_button -> statement.append("/")
                R.id.multiply_button -> statement.append("X")
            }
            calculatorView?.setScore(statement.toString())
            symbol = false
        }
    }

    override fun btnOption(id: Int) {
        when (id) {
            R.id.back_button -> if (statement.toString().length != 0) {
                statement.setLength(statement.length-1)
                calculatorView?.setScore(statement.toString())
                symbol = true
            }
            R.id.clear_button -> {
                calculatorView?.clearNum()
                statement= StringBuilder("0")
            }
            R.id.result_button -> try {
                result = calc.calculate(statement.toString())
                calculatorView?.setResult(result!!)
            } catch (e: NumberFormatException) {
                calculatorView?.Toast("입력값을 제대로 해주세요.")
            }
        }
    }

    override fun btnPoint() {
        val decimalPoint = StringTokenizer(statement.toString(), "1234567890+-/X")
        val oper = StringTokenizer(statement.toString(), "1234567890.")

        if (point == true && oper.countTokens() == decimalPoint.countTokens()) {
            statement.append(".")
            calculatorView?.setScore(statement.toString())
            point = false
        }
    }

    override fun rxCalculate(event: Observable<CharSequence>) {
        val disposable = event.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .filter { text->text.length>0 }.filter { text->
                    text.toString()[text.length-1] == '+' || text.toString()[text.length-1] == '-' || text.toString()[text.length-1] == 'X' ||
                            text.toString()[text.length-1] == '/'
                }
                .map<String> { text->  calc.calculate(text.toString().substring(0,text.length-1)) }
                .subscribe { data->calculatorView?.setRxResult(data)}
        compositeDisposable.add(disposable)
    }

    override fun unsubscribe() {
        compositeDisposable.clear()
    }


}