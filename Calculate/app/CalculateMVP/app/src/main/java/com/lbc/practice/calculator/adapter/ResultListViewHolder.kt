package com.lbc.practice.calculator.adapter

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.lbc.practice.calculator.R
import com.lbc.practice.calculator.data.Result


class ResultListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val textView: TextView

    init {
        textView = itemView.findViewById<View>(R.id.problem_answer_result_number) as TextView
    }

    fun setData(result: Result) {

        textView.text = result.num.toString() + ""

        if (result.isResult == false) {
            textView.setBackground(ContextCompat.getDrawable(itemView.context, R.drawable.in_correct_circle))
            textView.setTextColor(ContextCompat.getColor(itemView.context, R.color.in_correct_color))
        }

    }
}
