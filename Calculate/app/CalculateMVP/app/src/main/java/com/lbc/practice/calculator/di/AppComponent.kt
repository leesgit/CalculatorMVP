package com.lbc.practice.calculator.di

import android.app.Application
import com.lbc.practice.calculator.CalculatorApplication
import com.lbc.practice.calculator.data.resource.RepositoryModule
import com.lbc.practice.calculator.data.resource.remote.RemoteModule
import com.lbc.practice.calculator.util.ManagerModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(RemoteModule::class, RepositoryModule::class,ManagerModule::class, ApplicationModule::class,
        ActivityBindingModule::class, AndroidSupportInjectionModule::class))
interface AppComponent : AndroidInjector<CalculatorApplication> {


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): AppComponent.Builder
        fun build(): AppComponent
    }
}