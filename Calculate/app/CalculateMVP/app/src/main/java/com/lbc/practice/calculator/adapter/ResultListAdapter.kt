package com.lbc.practice.calculator.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.lbc.practice.calculator.R
import com.lbc.practice.calculator.data.Result
import java.util.ArrayList

class ResultListAdapter : RecyclerView.Adapter<ResultListViewHolder>(), ResultListContract.View, ResultListContract.Model {


    var list: MutableList<Result> = ArrayList()

    override fun notifyAdapter() {
        notifyDataSetChanged()
    }

    override fun addInfo(result: Result) {
        list.add(result)

    }

    override fun clearItem() {
        list.clear()
    }

    fun addItem(result: Result) {
        list.add(result)
    }

    fun addItem(results: kotlin.collections.List<Result>) {
        list.addAll(results)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultListViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_problem_solving_result_tag, parent, false)

        return ResultListViewHolder(v)
    }

    override fun onBindViewHolder(holder: ResultListViewHolder, position: Int) {
        holder.setData(list[position])
    }
    override fun getItemCount(): Int {
        return list.size
    }


}
