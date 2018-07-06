package com.lbc.practice.calculator.util

import com.google.gson.Gson
import com.lbc.practice.calculator.data.LessonData
import com.lbc.practice.calculator.data.Problem
import io.reactivex.Observable
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.net.CookieManager
import java.net.CookiePolicy

public class RetrofitManager {

    var url : RetrofitUrl?=null

    constructor() {
        val cookieManager = CookieManager()
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)
        val client = OkHttpClient.Builder()
                .cookieJar(JavaNetCookieJar(cookieManager))
                .build()
        val retrofit = Retrofit.Builder()
                .baseUrl(RetrofitUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
        url = retrofit.create(RetrofitUrl::class.java)
    }


    interface RetrofitUrl {

        companion object {
            val BASE_URL = "http://"
        }

        @POST("/cal/calculator/getproblem")
        fun getProblem(@Body problem: Problem): Observable<Problem>

        @POST("/cal/calculator/getlesson")
        fun getLesson(@Body lessonData: LessonData): Observable<LessonData>

        @GET("/cal/calculator/getproblemcount")
        fun getCount(): Observable<Integer>



    }
}