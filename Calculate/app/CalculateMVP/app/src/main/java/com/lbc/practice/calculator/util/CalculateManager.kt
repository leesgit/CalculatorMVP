package com.lbc.practice.calculator.util

import android.util.Log
import java.util.*


class CalculateManager {
    fun calculate(str: String): String {
        var cnt = 0.0
        var c: String
        val st = Stack<String>()
        val nums = StringTokenizer(str, "+-/X")
        val opers = StringTokenizer(str, "1234567890.")

        st.push(nums.nextToken())

        while (nums.hasMoreTokens()) {
            val oper = opers.nextToken()[0]
            val num = nums.nextToken()
//            Log.e("num값", num + "")
            val first: String
            var t: Double

            if (oper == 'X') {
                first = st.pop()
                t = java.lang.Double.valueOf(first)!!.toDouble()
                t *= java.lang.Double.valueOf(num)!!.toDouble()
                st.push(java.lang.Double.toString(t))
            } else if (oper == '/') {
                first = st.pop()
                t = java.lang.Double.valueOf(first)!!.toDouble()
                t /= java.lang.Double.valueOf(num)!!.toDouble()
                st.push(java.lang.Double.toString(t))
            } else if (oper == '+') {
                st.push(num)
            } else if (oper == '-') {
                st.push(java.lang.Double.toString(-1 * java.lang.Double.valueOf(num)!!.toDouble()))
            }
        }

        while (!st.isEmpty()) {
            c = st.pop()
            cnt += java.lang.Double.valueOf(c)!!.toDouble()
        }
        cnt = Math.round(cnt * 10000) / 10000.0 //소수점 자릿수 반올림 할 만큼
        var result = java.lang.Double.toString(cnt)
        val length = result.length
        val indexOf = result.indexOf(".0")
        if (indexOf == length - 2) {
            result = result.substring(0, length - 2)
        }
//        Log.e("렝스, 인덱스", length.toString() + ", " + indexOf)
        return result
    }
}
