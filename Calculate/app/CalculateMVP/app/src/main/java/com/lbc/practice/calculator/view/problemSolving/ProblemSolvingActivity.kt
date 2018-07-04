package com.lbc.practice.calculator.view.problemSolving

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.ScrollView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent
import com.lbc.practice.calculator.R
import com.lbc.practice.calculator.adapter.ResultListAdapter
import com.lbc.practice.calculator.data.LessonType
import com.lbc.practice.calculator.util.MusicManager
import com.lbc.practice.calculator.view.claculator.CalculatorActivity
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import kotlinx.android.synthetic.main.activity_problem_solving.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class ProblemSolvingActivity : DaggerAppCompatActivity(), ProblemSolvingContract.ProblemView {


    private var linearLayoutManager: LinearLayoutManager? = null
    private var ResultListAdapter: ResultListAdapter? = null
    var form: String? = null
    var event: Observable<TextViewTextChangeEvent>? = null
    private var resouceMain: Int = 0
    private var resouceCorrect: Int = 0
    private var resouceInCorrect: Int = 0


    @Inject
    lateinit var music: MusicManager

    @Inject
    lateinit var problemPresenter: ProblemSolvingPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_problem_solving)

        init()

        problemPresenter.setStart(LessonType.plus)

        btn_check_answer.setOnClickListener {
            problemPresenter.checkAnswer(et_answer_input.text.toString())
        }

        btn_proceed_next.setOnClickListener {
            problemPresenter.setProblem(LessonType.plus)
        }

        btn_move_calculator.setOnClickListener {
            problemPresenter.calculatorChance()
        }

        event = RxTextView.textChangeEvents(et_answer_input)
        problemPresenter.rxChange(event!!)
    }


    private fun init() {
        resouceMain = resources.getIdentifier("main", "raw", packageName)
        resouceCorrect = resources.getIdentifier("correct", "raw", packageName)
        resouceInCorrect = resources.getIdentifier("in_correct", "raw", packageName)

        music.mainsongStart(this,resouceMain)
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        ResultListAdapter = ResultListAdapter()
        rv_problem_answer_result_tag.setLayoutManager(linearLayoutManager)
        rv_problem_answer_result_tag.adapter = ResultListAdapter
        problemPresenter.setView(this)
        problemPresenter.setPlayingAdaterModel(ResultListAdapter!!)
        problemPresenter.setPlayingAdaterView(ResultListAdapter!!)
    }


    override fun setProblem1() {
        et_answer_input.setText("");
        btn_proceed_next.setVisibility(View.INVISIBLE)
        btn_check_answer.setVisibility(View.VISIBLE)
        et_answer_input.setEnabled(true)
        btn_check_answer.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.unable_state))
        iv_symbol_answer_result.setVisibility(View.INVISIBLE)

    }

    override fun setProblem2(id: Int) {
        btn_proceed_next.visibility = View.VISIBLE
        btn_proceed_next.setText(id)
        iv_symbol_answer_result.setVisibility(View.VISIBLE)
        et_answer_input.setEnabled(false)
        btn_check_answer.setVisibility(View.GONE)
    }

    override fun setFalse() {
        iv_symbol_answer_result.setBackgroundResource(R.drawable.problem_in_correct)
        music.answerSound(this,resouceInCorrect,false)
    }

    override fun setTrue() {
        iv_symbol_answer_result.setBackgroundResource(R.drawable.problem_correct_circle)
        music.answerSound(this,resouceCorrect,true)
    }

    override fun setPosition() {
        rv_problem_answer_result_tag.smoothScrollToPosition(rv_problem_answer_result_tag.getAdapter().getItemCount());
    }


    override fun setContent(result: String) {
        val res = resources
        form = String.format(res.getString(R.string.question_form), result)
        tv_problem_content.setText(form)
    }

    override fun setNext(result2: String, num: Int) {
        tv_lesson_title.setText(result2)
        iv_problem_number_box.setText(num.toString())
        pg_problem_solving_progress.progress = num
    }

    override fun setStart(num: Int) {
        iv_symbol_answer_result.setVisibility(View.INVISIBLE)
        btn_check_answer.setBackgroundColor(Color.GRAY)
        btn_check_answer.setText("");
        btn_check_answer.setHint(R.string.enter_answer_hint)
        btn_check_answer.setVisibility(View.INVISIBLE)
        pg_problem_solving_progress.max = num
    }

    override fun moveToCalculator() {
        val intent = Intent(this, CalculatorActivity::class.java)
        startActivity(intent)
    }

    override fun setCount(result: String) {
        tv_calculator_count.setText(result)
    }

    override fun unAbleCalculator() {
        btn_move_calculator.setEnabled(false)
        btn_move_calculator.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.unable_state))
        btn_move_calculator.text = ""
        btn_move_calculator.setHint(R.string.calculator_chance_hint)
    }

    override fun Toast(msg: String) {
        android.widget.Toast.makeText(applicationContext, msg, android.widget.Toast.LENGTH_SHORT).show()
    }

    override fun setRxChanged() {
        btn_check_answer.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.activate_answer))
        btn_check_answer.setText(R.string.check_answer)
    }

    override fun setRxNonChanged() {
        btn_check_answer.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.unable_state))
        btn_check_answer.setText("")
    }


    override fun onDestroy() {
        super.onDestroy()
        problemPresenter.unsubscribe()
        music.mainsongStop()
    }

    override fun onRestart() {
        super.onRestart()
        problemPresenter.rxChange(event!!)
        music.mainsongStart(this,resouceMain)
    }

    override fun onStop() {
        super.onStop()
        problemPresenter.unsubscribe()
        music.mainsongStop()
    }

    override fun end() {
        finish()
    }
}
