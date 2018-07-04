package com.lbc.practice.calculator.adapter

import com.lbc.practice.calculator.data.Result


interface ResultListContract {


    interface View {
        fun notifyAdapter()
    }

    interface Model {
        fun addInfo(result :Result)
        fun clearItem()
    }
}