package com.lbc.practice.calculator.data.resource.remote

import android.content.Context
import com.lbc.practice.calculator.util.RetrofitManager
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class RemoteModule {

    @Provides
    fun serverProvider() : RetrofitManager {
        return RetrofitManager()
    }

    @Provides
    fun disposeRetro(): CompositeDisposable {
        return CompositeDisposable()
    }

}