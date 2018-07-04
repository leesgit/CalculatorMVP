package com.lbc.practice.calculator.view.claculator;

import android.os.Bundle;
import android.view.WindowManager
import android.view.View
import android.widget.Toast
import butterknife.ButterKnife
import butterknife.OnClick
import com.jakewharton.rxbinding2.widget.RxTextView
import com.lbc.practice.calculator.R
import com.lbc.practice.calculator.util.MusicManager
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_calculator.*
import javax.inject.Inject

class CalculatorActivity : DaggerAppCompatActivity(),CalculatorContractor.CalculatorView {


    private var resourceids: IntArray? = null
    private var resouceCal: Int = 0

    var event : Observable<CharSequence>?=null

    @Inject
    lateinit var music : MusicManager

    @Inject
    lateinit var calculatorPresenter: CalculatorPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        ButterKnife.bind(this)
        init()
    }

    fun init() {

        calculatorPresenter.setView(this)
        var resourceId: Int
        resourceids = IntArray(10)
        for (i in 0..9) {
            resourceId = resources.getIdentifier("num_$i", "id", packageName)
            findViewById<View>(resourceId).tag = i
            resourceId = resources.getIdentifier("num$i", "raw", packageName)
            resourceids!![i] = resourceId
        }
        resouceCal = resources.getIdentifier("cal", "raw", packageName)
        music.calSongStart(application, resouceCal)

        clearNum()

        event = RxTextView.textChanges(main_score)
        calculatorPresenter.rxCalculate(event!!)

    }


    @OnClick(R.id.num_0, R.id.num_1, R.id.num_2, R.id.num_3, R.id.num_4, R.id.num_5, R.id.num_6, R.id.num_7, R.id.num_8, R.id.num_9)
    fun btnNum(v: View) {
        calculatorPresenter.btnNum(v.tag as Int)
    }

    @OnClick(R.id.plus_button, R.id.minus_button, R.id.multiply_button, R.id.division_button)
    fun btnSymbol(v: View) {
        calculatorPresenter.btnSymbol(v.id)
    }

    @OnClick(R.id.clear_button, R.id.back_button, R.id.result_button)
    fun btnOption(v: View) {
        calculatorPresenter.btnOption(v.id)
    }

    @OnClick(R.id.point_button)
    fun btnPoint() {
        calculatorPresenter.btnPoint()
    }


    override fun setScore(result: String) {
        main_score.text = result
    }

    override fun numMusicStart(position: Int) {
        music.numberSound(application, resourceids!![position], position)
    }

    override fun clearNum() {
        rx_text.text="0"
        main_score.text = "0"
        result_statement!!.text = ""
    }

    override fun setResult(result: String) {
        result_statement!!.text = result
    }

    override fun Toast(comment: String) {
        Toast.makeText(applicationContext, comment, Toast.LENGTH_SHORT).show()
    }

    override fun setRxResult(result: String) {
        rx_text.setText(result)
    }

    override fun onStop() {
        super.onStop()
        music.calSongStop()
        calculatorPresenter.unsubscribe()
    }

    override fun onRestart() {
        super.onRestart()
        music.calSongStart(application, resouceCal)
        calculatorPresenter.rxCalculate(event!!)
    }
}